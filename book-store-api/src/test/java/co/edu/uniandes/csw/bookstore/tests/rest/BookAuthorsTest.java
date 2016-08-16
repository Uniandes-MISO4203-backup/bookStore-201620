/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.bookstore.tests.rest;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import co.edu.uniandes.csw.bookstore.dtos.minimum.BookDTO;
import co.edu.uniandes.csw.bookstore.dtos.minimum.AuthorDTO;
import co.edu.uniandes.csw.bookstore.resources.BookResource;
import co.edu.uniandes.csw.bookstore.tests.Utils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.UserTransaction;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/*
 * Testing URI: books/
 */
@RunWith(Arquillian.class)
public class BookAuthorsTest {

    private WebTarget target;
    private PodamFactory factory = new PodamFactoryImpl();
    private final String apiPath = Utils.apiPath;
    private final String username = Utils.username;
    private final String password = Utils.password;

    private final int Ok = Status.OK.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();

    private final static List<AuthorEntity> oraculo = new ArrayList<>();

    private final String bookPath = "books";
    private final String authorsPath = "authors";

    private BookEntity fatherBookEntity;

    @ArquillianResource
    private URL deploymentURL;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                // Se agrega las dependencias
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
                        .importRuntimeDependencies().resolve()
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(BookResource.class.getPackage())
                // El archivo que contiene la configuracion a la base de datos.
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias.
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo shiro.ini es necesario para injeccion de dependencias
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/shiro.ini"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
    }

    private WebTarget createWebTarget() {
        return ClientBuilder.newClient().target(deploymentURL.toString()).path(apiPath);
    }

    @PersistenceContext(unitName = "BookStorePU")
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private void clearData() {
        List<BookEntity> records = em.createQuery("SELECT u FROM BookEntity u").getResultList();
        for (BookEntity record : records) {
            em.remove(record);
        }
        em.createQuery("delete from AuthorEntity").executeUpdate();
        oraculo.clear();
    }

   /**
     * Datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
            fatherBookEntity = factory.manufacturePojo(BookEntity.class);
            em.persist(fatherBookEntity);

            for (int i = 0; i < 3; i++) {
                AuthorEntity authors = factory.manufacturePojo(AuthorEntity.class);
                em.persist(authors);
                if(i<2){                
                    fatherBookEntity.getAuthors().add(authors);
                }
                oraculo.add(authors);
            }
    }

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void setUpTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();            
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        target = createWebTarget()
                .path(bookPath)
                .path(fatherBookEntity.getId().toString())
                .path(authorsPath);
    }

    /**
     * Login para poder consultar los diferentes servicios
     *
     * @param username Nombre de usuario
     * @param password Clave del usuario
     * @return Cookie con información de la sesión del usuario
     * @generated
     */
    public Cookie login(String username, String password) {
        UserDTO user = new UserDTO();
        user.setUserName(username);
        user.setPassword(password);
        user.setRememberMe(true);
        Response response = createWebTarget()
                .path("users")
                .path("login")
                .request()
                .post(Entity.entity(user, MediaType.APPLICATION_JSON));
        if (response.getStatus() == Ok) {
            return response.getCookies().get(JWT.cookieName);
        } else {
            return null;
        }
    }

    /**
     *Prueba para asociar un Authors existente a un Book
     *
     * @generated
     */
    @Test
    public void addAuthorsTest() {
        Cookie cookieSessionId = login(username, password);

        AuthorDTO authors = new AuthorDTO(oraculo.get(2));

        Response response = target.path(authors.getId().toString())
                .request().cookie(cookieSessionId)
                .post(Entity.entity(authors, MediaType.APPLICATION_JSON));

        AuthorDTO authorsTest = (AuthorDTO) response.readEntity(AuthorDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(authors.getId(), authorsTest.getId());
    }

    /**
     * Prueba para obtener una colección de instancias de Authors asociadas a una instancia Book
     *
     * @generated
     */
    @Test
    public void listAuthorsTest() throws IOException {
        Cookie cookieSessionId = login(username, password);

        Response response = target
                .request().cookie(cookieSessionId).get();

        String authorsList = response.readEntity(String.class);
        List<AuthorDTO> authorsListTest = new ObjectMapper().readValue(authorsList, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(2, authorsListTest.size());
    }

    /**
     * Prueba para obtener una instancia de Authors asociada a una instancia Book
     *
     * @generated
     */
    @Test
    public void getAuthorsTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        AuthorDTO authors = new AuthorDTO(oraculo.get(0));

        AuthorDTO authorsTest = target.path(authors.getId().toString())
                .request().cookie(cookieSessionId).get(AuthorDTO.class);

        Assert.assertEquals(authors.getId(), authorsTest.getId());
        Assert.assertEquals(authors.getName(), authorsTest.getName());
    }

    /**
     * Prueba para desasociar un Authors existente de un Book existente
     *
     * @generated
     */
    @Test
    public void removeAuthorsTest() {
        Cookie cookieSessionId = login(username, password);

        AuthorDTO authors = new AuthorDTO(oraculo.get(0));

        Response response = target.path(authors.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}
