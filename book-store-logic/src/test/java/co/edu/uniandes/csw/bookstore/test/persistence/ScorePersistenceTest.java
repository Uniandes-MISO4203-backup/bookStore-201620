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
import co.edu.uniandes.csw.bookstore.entities.ReviewEntity;
import co.edu.uniandes.csw.bookstore.entities.ScoreEntity;
import co.edu.uniandes.csw.bookstore.persistence.ScorePersistence;
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
public class ScorePersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ScoreEntity.class.getPackage())
                .addPackage(ScorePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    ReviewEntity fatherEntity;

    /**
     * @generated
     */
    @Inject
    private ScorePersistence scorePersistence;

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
    }

    /**
     * @generated
     */
    private List<ScoreEntity> data = new ArrayList<ScoreEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
            fatherEntity = factory.manufacturePojo(ReviewEntity.class);
            fatherEntity.setId(1L);
            em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            ScoreEntity entity = factory.manufacturePojo(ScoreEntity.class);
            
            entity.setReview(fatherEntity);
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un Score.
     *
     * @generated
     */
    @Test
    public void createScoreTest() {
		PodamFactory factory = new PodamFactoryImpl();
        ScoreEntity newEntity = factory.manufacturePojo(ScoreEntity.class);
        newEntity.setReview(fatherEntity);
        ScoreEntity result = scorePersistence.create(newEntity);

        Assert.assertNotNull(result);

        ScoreEntity entity = em.find(ScoreEntity.class, result.getId());

        Assert.assertEquals(newEntity.getScore(), entity.getScore());
        Assert.assertEquals(newEntity.getDescription(), entity.getDescription());
    }

    /**
     * Prueba para consultar la lista de Scores.
     *
     * @generated
     */
    @Test
    public void getScoresTest() {
        List<ScoreEntity> list = scorePersistence.findAll(null, null, fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (ScoreEntity ent : list) {
            boolean found = false;
            for (ScoreEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Score.
     *
     * @generated
     */
    @Test
    public void getScoreTest() {
        ScoreEntity entity = data.get(0);
        ScoreEntity newEntity = scorePersistence.find(entity.getReview().getId(), entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getScore(), newEntity.getScore());
        Assert.assertEquals(entity.getDescription(), newEntity.getDescription());
    }

    /**
     * Prueba para eliminar un Score.
     *
     * @generated
     */
    @Test
    public void deleteScoreTest() {
        ScoreEntity entity = data.get(0);
        scorePersistence.delete(entity.getId());
        ScoreEntity deleted = em.find(ScoreEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Score.
     *
     * @generated
     */
    @Test
    public void updateScoreTest() {
        ScoreEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ScoreEntity newEntity = factory.manufacturePojo(ScoreEntity.class);

        newEntity.setId(entity.getId());

        scorePersistence.update(newEntity);

        ScoreEntity resp = em.find(ScoreEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getScore(), resp.getScore());
        Assert.assertEquals(newEntity.getDescription(), resp.getDescription());
    }
}
