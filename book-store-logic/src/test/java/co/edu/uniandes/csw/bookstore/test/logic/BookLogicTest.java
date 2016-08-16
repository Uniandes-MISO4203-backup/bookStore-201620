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

import co.edu.uniandes.csw.bookstore.ejbs.BookLogic;
import co.edu.uniandes.csw.bookstore.api.IBookLogic;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import co.edu.uniandes.csw.bookstore.persistence.BookPersistence;
import co.edu.uniandes.csw.bookstore.entities.EditorialEntity;
import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import co.edu.uniandes.csw.bookstore.entities.ReviewEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
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
public class BookLogicTest {

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
    private IBookLogic bookLogic;

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
    private List<BookEntity> data = new ArrayList<BookEntity>();

    /**
     * @generated
     */
    private List<AuthorEntity> authorsData = new ArrayList<>();

    /**
     * @generated
     */
    private List<EditorialEntity> editorialData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BookEntity.class.getPackage())
                .addPackage(BookLogic.class.getPackage())
                .addPackage(IBookLogic.class.getPackage())
                .addPackage(BookPersistence.class.getPackage())
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
        em.createQuery("delete from ReviewEntity").executeUpdate();
        em.createQuery("delete from BookEntity").executeUpdate();
        em.createQuery("delete from AuthorEntity").executeUpdate();
        em.createQuery("delete from EditorialEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            AuthorEntity authors = factory.manufacturePojo(AuthorEntity.class);
            em.persist(authors);
            authorsData.add(authors);
        }
        for (int i = 0; i < 3; i++) {
            EditorialEntity editorial = factory.manufacturePojo(EditorialEntity.class);
            em.persist(editorial);
            editorialData.add(editorial);
        }
        for (int i = 0; i < 3; i++) {
            BookEntity entity = factory.manufacturePojo(BookEntity.class);
            entity.getAuthors().add(authorsData.get(0));
            entity.setEditorial(editorialData.get(0));

            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un Book
     *
     * @generated
     */
    @Test
    public void createBookTest() {
        BookEntity newEntity = factory.manufacturePojo(BookEntity.class);
        BookEntity result = bookLogic.createBook(newEntity);
        Assert.assertNotNull(result);
        BookEntity entity = em.find(BookEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getDescription(), entity.getDescription());
        Assert.assertEquals(newEntity.getIsbn(), entity.getIsbn());
        Assert.assertEquals(newEntity.getImage(), entity.getImage());
        Assert.assertEquals(newEntity.getPublicationDate(), entity.getPublicationDate());
    }

    /**
     * Prueba para consultar la lista de Books
     *
     * @generated
     */
    @Test
    public void getBooksTest() {
        List<BookEntity> list = bookLogic.getBooks();
        Assert.assertEquals(data.size(), list.size());
        for (BookEntity entity : list) {
            boolean found = false;
            for (BookEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un Book
     *
     * @generated
     */
    @Test
    public void getBookTest() {
        BookEntity entity = data.get(0);
        BookEntity resultEntity = bookLogic.getBook(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getDescription(), resultEntity.getDescription());
        Assert.assertEquals(entity.getIsbn(), resultEntity.getIsbn());
        Assert.assertEquals(entity.getImage(), resultEntity.getImage());
        Assert.assertEquals(entity.getPublicationDate(), resultEntity.getPublicationDate());
    }

    /**
     * Prueba para eliminar un Book
     *
     * @generated
     */
    @Test
    public void deleteBookTest() {
        BookEntity entity = data.get(1);
        bookLogic.deleteBook(entity.getId());
        BookEntity deleted = em.find(BookEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Book
     *
     * @generated
     */
    @Test
    public void updateBookTest() {
        BookEntity entity = data.get(0);
        BookEntity pojoEntity = factory.manufacturePojo(BookEntity.class);

        pojoEntity.setId(entity.getId());

        bookLogic.updateBook(pojoEntity);

        BookEntity resp = em.find(BookEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getDescription(), resp.getDescription());
        Assert.assertEquals(pojoEntity.getIsbn(), resp.getIsbn());
        Assert.assertEquals(pojoEntity.getImage(), resp.getImage());
        Assert.assertEquals(pojoEntity.getPublicationDate(), resp.getPublicationDate());
    }

    /**
     * Prueba para obtener una instancia de Authors asociada a una instancia Book
     *
     * @generated
     */
    @Test
    public void getAuthorsTest() {
        BookEntity entity = data.get(0);
        AuthorEntity authorEntity = authorsData.get(0);
        AuthorEntity response = bookLogic.getAuthors(entity.getId(), authorEntity.getId());

        Assert.assertEquals(authorEntity.getId(), response.getId());
        Assert.assertEquals(authorEntity.getName(), response.getName());
    }

    /**
     * Prueba para obtener una colección de instancias de Authors asociadas a una instancia Book
     *
     * @generated
     */
    @Test
    public void listAuthorsTest() {
        List<AuthorEntity> list = bookLogic.listAuthors(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     *Prueba para asociar un Authors existente a un Book
     *
     * @generated
     */
    @Test
    public void addAuthorsTest() {
        BookEntity entity = data.get(0);
        AuthorEntity authorEntity = authorsData.get(1);
        AuthorEntity response = bookLogic.addAuthors(entity.getId(), authorEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(authorEntity.getId(), response.getId());
    }

    /**
     * Prueba para remplazar las instancias de Authors asociadas a una instancia de Book
     *
     * @generated
     */
    @Test
    public void replaceAuthorsTest() {
        BookEntity entity = data.get(0);
        List<AuthorEntity> list = authorsData.subList(1, 3);
        bookLogic.replaceAuthors(entity.getId(), list);

        entity = bookLogic.getBook(entity.getId());
        Assert.assertFalse(entity.getAuthors().contains(authorsData.get(0)));
        Assert.assertTrue(entity.getAuthors().contains(authorsData.get(1)));
        Assert.assertTrue(entity.getAuthors().contains(authorsData.get(2)));
    }

    /**
     * Prueba para desasociar un Authors existente de un Book existente
     *
     * @generated
     */
    @Test
    public void removeAuthorsTest() {
        bookLogic.removeAuthors(data.get(0).getId(), authorsData.get(0).getId());
        AuthorEntity response = bookLogic.getAuthors(data.get(0).getId(), authorsData.get(0).getId());
        Assert.assertNull(response);
    }
}

