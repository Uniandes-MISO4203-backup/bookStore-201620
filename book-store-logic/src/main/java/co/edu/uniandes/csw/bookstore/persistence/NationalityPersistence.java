package co.edu.uniandes.csw.bookstore.persistence;

import co.edu.uniandes.csw.bookstore.entities.NationalityEntity;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author asistente
 */
@Stateless
public class NationalityPersistence extends CrudPersistence<NationalityEntity> {
    @PersistenceContext(unitName="BookStorePU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    @Override
    protected Class<NationalityEntity> getEntityClass() {
        return NationalityEntity.class;
    }
}