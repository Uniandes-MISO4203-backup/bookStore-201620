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
package co.edu.uniandes.csw.bookstore.test.logic;

import co.edu.uniandes.csw.bookstore.ejbs.AuthorLogic;
import co.edu.uniandes.csw.bookstore.api.IAuthorLogic;
import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import co.edu.uniandes.csw.bookstore.persistence.AuthorPersistence;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class AuthorLogicTest {

    /**
     * @generated
     */

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IAuthorLogic authorLogic;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    private UserTransaction utx;

    /**
     * @generated
     */
    private List<AuthorEntity> data = new ArrayList<AuthorEntity>();

    /**
     * @generated
     */
    private List<BookEntity> booksData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AuthorEntity.class.getPackage())
                .addPackage(AuthorLogic.class.getPackage())
                .addPackage(IAuthorLogic.class.getPackage())
                .addPackage(AuthorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void configTest() {
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
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from BookEntity").executeUpdate();
        em.createQuery("delete from AuthorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            BookEntity books = factory.manufacturePojo(BookEntity.class);
            em.persist(books);
            booksData.add(books);
        }
        for (int i = 0; i < 3; i++) {
            AuthorEntity entity = factory.manufacturePojo(AuthorEntity.class);

            em.persist(entity);
            data.add(entity);

            booksData.get(0).getAuthors().add(entity);
        }
    }
    /**
     * Prueba para crear un Author
     *
     * @generated
     */
    @Test
    public void createAuthorTest() {
        AuthorEntity newEntity = factory.manufacturePojo(AuthorEntity.class);
        AuthorEntity result = authorLogic.createAuthor(newEntity);
        Assert.assertNotNull(result);
        AuthorEntity entity = em.find(AuthorEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Authors
     *
     * @generated
     */
    @Test
    public void getAuthorsTest() {
        List<AuthorEntity> list = authorLogic.getAuthors();
        Assert.assertEquals(data.size(), list.size());
        for (AuthorEntity entity : list) {
            boolean found = false;
            for (AuthorEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un Author
     *
     * @generated
     */
    @Test
    public void getAuthorTest() {
        AuthorEntity entity = data.get(0);
        AuthorEntity resultEntity = authorLogic.getAuthor(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * Prueba para eliminar un Author
     *
     * @generated
     */
    @Test
    public void deleteAuthorTest() {
        AuthorEntity entity = data.get(1);
        authorLogic.deleteAuthor(entity.getId());
        AuthorEntity deleted = em.find(AuthorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Author
     *
     * @generated
     */
    @Test
    public void updateAuthorTest() {
        AuthorEntity entity = data.get(0);
        AuthorEntity pojoEntity = factory.manufacturePojo(AuthorEntity.class);

        pojoEntity.setId(entity.getId());

        authorLogic.updateAuthor(pojoEntity);

        AuthorEntity resp = em.find(AuthorEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }

    /**
     * Prueba para obtener una instancia de Books asociada a una instancia Author
     *
     * @generated
     */
    @Test
    public void getBooksTest() {
        AuthorEntity entity = data.get(0);
        BookEntity bookEntity = booksData.get(0);
        BookEntity response = authorLogic.getBooks(entity.getId(), bookEntity.getId());

        Assert.assertEquals(bookEntity.getId(), response.getId());
        Assert.assertEquals(bookEntity.getName(), response.getName());
        Assert.assertEquals(bookEntity.getDescription(), response.getDescription());
        Assert.assertEquals(bookEntity.getIsbn(), response.getIsbn());
        Assert.assertEquals(bookEntity.getImage(), response.getImage());
        Assert.assertEquals(bookEntity.getPublicationDate(), response.getPublicationDate());
    }

    /**
     * Prueba para obtener una colección de instancias de Books asociadas a una instancia Author
     *
     * @generated
     */
    @Test
    public void listBooksTest() {
        List<BookEntity> list = authorLogic.listBooks(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     *Prueba para asociar un Books existente a un Author
     *
     * @generated
     */
    @Test
    public void addBooksTest() {
        AuthorEntity entity = data.get(0);
        BookEntity bookEntity = booksData.get(1);
        BookEntity response = authorLogic.addBooks(entity.getId(), bookEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(bookEntity.getId(), response.getId());
    }

    /**
     * Prueba para remplazar las instancias de Books asociadas a una instancia de Author
     *
     * @generated
     */
    @Test
    public void replaceBooksTest() {
        AuthorEntity entity = data.get(0);
        List<BookEntity> list = booksData.subList(1, 3);
        authorLogic.replaceBooks(entity.getId(), list);

        entity = authorLogic.getAuthor(entity.getId());
        Assert.assertFalse(entity.getBooks().contains(booksData.get(0)));
        Assert.assertTrue(entity.getBooks().contains(booksData.get(1)));
        Assert.assertTrue(entity.getBooks().contains(booksData.get(2)));
    }

    /**
     * Prueba para desasociar un Books existente de un Author existente
     *
     * @generated
     */
    @Test
    public void removeBooksTest() {
        authorLogic.removeBooks(data.get(0).getId(), booksData.get(0).getId());
        BookEntity response = authorLogic.getBooks(data.get(0).getId(), booksData.get(0).getId());
        Assert.assertNull(response);
    }
}

