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

import co.edu.uniandes.csw.bookstore.ejbs.ReviewLogic;
import co.edu.uniandes.csw.bookstore.api.IReviewLogic;
import co.edu.uniandes.csw.bookstore.entities.ReviewEntity;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import co.edu.uniandes.csw.bookstore.persistence.ReviewPersistence;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import co.edu.uniandes.csw.bookstore.entities.ScoreEntity;
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
public class ReviewLogicTest {

    /**
     * @generated
     */
    BookEntity fatherEntity;

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IReviewLogic reviewLogic;

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
    private List<ReviewEntity> data = new ArrayList<ReviewEntity>();

    /**
     * @generated
     */
    private List<BookEntity> bookData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReviewEntity.class.getPackage())
                .addPackage(ReviewLogic.class.getPackage())
                .addPackage(IReviewLogic.class.getPackage())
                .addPackage(ReviewPersistence.class.getPackage())
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
        em.createQuery("delete from ScoreEntity").executeUpdate();
        em.createQuery("delete from ReviewEntity").executeUpdate();
        em.createQuery("delete from BookEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {

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
     * Prueba para crear un Review
     *
     * @generated
     */
    @Test
    public void createReviewTest() {
        ReviewEntity newEntity = factory.manufacturePojo(ReviewEntity.class);
        ReviewEntity result = reviewLogic.createReview(fatherEntity.getId(), newEntity);
        Assert.assertNotNull(result);
        ReviewEntity entity = em.find(ReviewEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getSource(), entity.getSource());
        Assert.assertEquals(newEntity.getDescription(), entity.getDescription());
    }

    /**
     * Prueba para consultar la lista de Reviews
     *
     * @generated
     */
    @Test
    public void getReviewsTest() {
        List<ReviewEntity> list = reviewLogic.getReviews(fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (ReviewEntity entity : list) {
            boolean found = false;
            for (ReviewEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un Review
     *
     * @generated
     */
    @Test
    public void getReviewTest() {
        ReviewEntity entity = data.get(0);
        ReviewEntity resultEntity = reviewLogic.getReview(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getSource(), resultEntity.getSource());
        Assert.assertEquals(entity.getDescription(), resultEntity.getDescription());
    }

    /**
     * Prueba para eliminar un Review
     *
     * @generated
     */
    @Test
    public void deleteReviewTest() {
        ReviewEntity entity = data.get(1);
        reviewLogic.deleteReview(entity.getId());
        ReviewEntity deleted = em.find(ReviewEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Review
     *
     * @generated
     */
    @Test
    public void updateReviewTest() {
        ReviewEntity entity = data.get(0);
        ReviewEntity pojoEntity = factory.manufacturePojo(ReviewEntity.class);

        pojoEntity.setId(entity.getId());

        reviewLogic.updateReview(fatherEntity.getId(), pojoEntity);

        ReviewEntity resp = em.find(ReviewEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getSource(), resp.getSource());
        Assert.assertEquals(pojoEntity.getDescription(), resp.getDescription());
    }
}

