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
import co.edu.uniandes.csw.bookstore.entities.EditorialEntity;
import co.edu.uniandes.csw.bookstore.dtos.minimum.EditorialDTO;
import co.edu.uniandes.csw.bookstore.resources.EditorialResource;
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
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/*
 * Testing URI: editorials/
 */
@RunWith(Arquillian.class)
public class EditorialTest {

    private WebTarget target;
    private final String apiPath = Utils.apiPath;
    private final String username = Utils.username;
    private final String password = Utils.password;
    PodamFactory factory = new PodamFactoryImpl();

    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();

    private final static List<EditorialEntity> oraculo = new ArrayList<>();

    private final String editorialPath = "editorials";


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
                .addPackage(EditorialResource.class.getPackage())
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
        em.createQuery("delete from EditorialEntity").executeUpdate();
        oraculo.clear();
    }

   /**
     * Datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    public void insertData() {
        for (int i = 0; i < 3; i++) {            
            EditorialEntity editorial = factory.manufacturePojo(EditorialEntity.class);
            editorial.setId(i + 1L);
            em.persist(editorial);
            oraculo.add(editorial);
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
                .path(editorialPath);
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
        Response response = createWebTarget().path("users").path("login").request()
                .post(Entity.entity(user, MediaType.APPLICATION_JSON));
        if (response.getStatus() == Ok) {
            return response.getCookies().get(JWT.cookieName);
        } else {
            return null;
        }
    }

    /**
     * Prueba para crear un Editorial
     *
     * @generated
     */
    @Test
    public void createEditorialTest() throws IOException {
        EditorialDTO editorial = factory.manufacturePojo(EditorialDTO.class);
        Cookie cookieSessionId = login(username, password);

        Response response = target
            .request().cookie(cookieSessionId)
            .post(Entity.entity(editorial, MediaType.APPLICATION_JSON));

        EditorialDTO  editorialTest = (EditorialDTO) response.readEntity(EditorialDTO.class);

        Assert.assertEquals(Created, response.getStatus());

        Assert.assertEquals(editorial.getName(), editorialTest.getName());

        EditorialEntity entity = em.find(EditorialEntity.class, editorialTest.getId());
        Assert.assertNotNull(entity);
    }

    /**
     * Prueba para consultar un Editorial
     *
     * @generated
     */
    @Test
    public void getEditorialByIdTest() {
        Cookie cookieSessionId = login(username, password);

        EditorialDTO editorialTest = target
            .path(oraculo.get(0).getId().toString())
            .request().cookie(cookieSessionId).get(EditorialDTO.class);
        
        Assert.assertEquals(editorialTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(editorialTest.getName(), oraculo.get(0).getName());
    }

    /**
     * Prueba para consultar la lista de Editorials
     *
     * @generated
     */
    @Test
    public void listEditorialTest() throws IOException {
        Cookie cookieSessionId = login(username, password);

        Response response = target
            .request().cookie(cookieSessionId).get();

        String listEditorial = response.readEntity(String.class);
        List<EditorialDTO> listEditorialTest = new ObjectMapper().readValue(listEditorial, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(3, listEditorialTest.size());
    }

    /**
     * Prueba para actualizar un Editorial
     *
     * @generated
     */
    @Test
    public void updateEditorialTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        EditorialDTO editorial = new EditorialDTO(oraculo.get(0));

        EditorialDTO editorialChanged = factory.manufacturePojo(EditorialDTO.class);

        editorial.setName(editorialChanged.getName());

        Response response = target
            .path(editorial.getId().toString())
            .request().cookie(cookieSessionId)
            .put(Entity.entity(editorial, MediaType.APPLICATION_JSON));

        EditorialDTO editorialTest = (EditorialDTO) response.readEntity(EditorialDTO.class);

        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(editorial.getName(), editorialTest.getName());
    }

    /**
     * Prueba para eliminar un Editorial
     *
     * @generated
     */
    @Test
    public void deleteEditorialTest() {
        Cookie cookieSessionId = login(username, password);
        EditorialDTO editorial = new EditorialDTO(oraculo.get(0));
        Response response = target
            .path(editorial.getId().toString())
            .request().cookie(cookieSessionId).delete();

        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}
