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
package co.edu.uniandes.csw.bookstore.test.persistence;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import co.edu.uniandes.csw.bookstore.entities.ReviewEntity;
import co.edu.uniandes.csw.bookstore.persistence.BookPersistence;
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
public class BookPersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BookEntity.class.getPackage())
                .addPackage(BookPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */

    /**
     * @generated
     */
    @Inject
    private BookPersistence bookPersistence;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    UserTransaction utx;

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
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
    }

    /**
     * @generated
     */
    private List<BookEntity> data = new ArrayList<BookEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            BookEntity entity = factory.manufacturePojo(BookEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un Book.
     *
     * @generated
     */
    @Test
    public void createBookTest() {
        PodamFactory factory = new PodamFactoryImpl();
        BookEntity newEntity = factory.manufacturePojo(BookEntity.class);
        BookEntity result = bookPersistence.create(newEntity);

        Assert.assertNotNull(result);

        BookEntity entity = em.find(BookEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getDescription(), entity.getDescription());
        Assert.assertEquals(newEntity.getIsbn(), entity.getIsbn());
        Assert.assertEquals(newEntity.getImage(), entity.getImage());
    }

    /**
     * Prueba para consultar la lista de Books.
     *
     * @generated
     */
    @Test
    public void getBooksTest() {
        List<BookEntity> list = bookPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (BookEntity ent : list) {
            boolean found = false;
            for (BookEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Book.
     *
     * @generated
     */
    @Test
    public void getBookTest() {
        BookEntity entity = data.get(0);
        BookEntity newEntity = bookPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getDescription(), newEntity.getDescription());
        Assert.assertEquals(entity.getIsbn(), newEntity.getIsbn());
        Assert.assertEquals(entity.getImage(), newEntity.getImage());
        Assert.assertEquals(entity.getPublicationDate(), newEntity.getPublicationDate());
    }

    /**
     * Prueba para eliminar un Book.
     *
     * @generated
     */
    @Test
    public void deleteBookTest() {
        BookEntity entity = data.get(0);
        bookPersistence.delete(entity.getId());
        BookEntity deleted = em.find(BookEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Book.
     *
     * @generated
     */
    @Test
    public void updateBookTest() {
        BookEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        BookEntity newEntity = factory.manufacturePojo(BookEntity.class);

        newEntity.setId(entity.getId());

        bookPersistence.update(newEntity);

        BookEntity resp = em.find(BookEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getDescription(), resp.getDescription());
        Assert.assertEquals(newEntity.getIsbn(), resp.getIsbn());
        Assert.assertEquals(newEntity.getImage(), resp.getImage());
    }
}
