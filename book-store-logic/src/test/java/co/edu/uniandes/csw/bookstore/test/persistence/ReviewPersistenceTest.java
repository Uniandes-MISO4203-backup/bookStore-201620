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
import co.edu.uniandes.csw.bookstore.entities.ScoreEntity;
import co.edu.uniandes.csw.bookstore.persistence.ReviewPersistence;
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
public class ReviewPersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReviewEntity.class.getPackage())
                .addPackage(ReviewPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    BookEntity fatherEntity;

    /**
     * @generated
     */
    @Inject
    private ReviewPersistence reviewPersistence;

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
        em.createQuery("delete from ScoreEntity").executeUpdate();
        em.createQuery("delete from ReviewEntity").executeUpdate();
        em.createQuery("delete from BookEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<ReviewEntity> data = new ArrayList<ReviewEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
            fatherEntity = factory.manufacturePojo(BookEntity.class);
            fatherEntity.setId(1L);
            em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            ReviewEntity entity = factory.manufacturePojo(ReviewEntity.class);
            
            entity.setBook(fatherEntity);
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un Review.
     *
     * @generated
     */
    @Test
    public void createReviewTest() {
		PodamFactory factory = new PodamFactoryImpl();
        ReviewEntity newEntity = factory.manufacturePojo(ReviewEntity.class);
        newEntity.setBook(fatherEntity);
        ReviewEntity result = reviewPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ReviewEntity entity = em.find(ReviewEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getSource(), entity.getSource());
        Assert.assertEquals(newEntity.getDescription(), entity.getDescription());
    }

    /**
     * Prueba para consultar la lista de Reviews.
     *
     * @generated
     */
    @Test
    public void getReviewsTest() {
        List<ReviewEntity> list = reviewPersistence.findAll(null, null, fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (ReviewEntity ent : list) {
            boolean found = false;
            for (ReviewEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Review.
     *
     * @generated
     */
    @Test
    public void getReviewTest() {
        ReviewEntity entity = data.get(0);
        ReviewEntity newEntity = reviewPersistence.find(entity.getBook().getId(), entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getSource(), newEntity.getSource());
        Assert.assertEquals(entity.getDescription(), newEntity.getDescription());
    }

    /**
     * Prueba para eliminar un Review.
     *
     * @generated
     */
    @Test
    public void deleteReviewTest() {
        ReviewEntity entity = data.get(0);
        reviewPersistence.delete(entity.getId());
        ReviewEntity deleted = em.find(ReviewEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Review.
     *
     * @generated
     */
    @Test
    public void updateReviewTest() {
        ReviewEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ReviewEntity newEntity = factory.manufacturePojo(ReviewEntity.class);

        newEntity.setId(entity.getId());

        reviewPersistence.update(newEntity);

        ReviewEntity resp = em.find(ReviewEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getSource(), resp.getSource());
        Assert.assertEquals(newEntity.getDescription(), resp.getDescription());
    }
}
