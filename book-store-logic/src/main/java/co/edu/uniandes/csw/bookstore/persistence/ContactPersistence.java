package co.edu.uniandes.csw.bookstore.persistence;

import co.edu.uniandes.csw.bookstore.entities.ContactEntity;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author asistente
 */
@Stateless
public class ContactPersistence extends CrudPersistence<ContactEntity>{
    @PersistenceContext(unitName="BookStorePU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected Class<ContactEntity> getEntityClass() {
        return ContactEntity.class;
    }
    
    public ContactEntity find(Long authorid, Long contactid) {
        TypedQuery<ContactEntity> q = em.createQuery("select p from ContactEntity p where (p.author.id = :authorid) and (p.id = :contactid)", ContactEntity.class);
        q.setParameter("authorid", authorid);
        q.setParameter("contactid", contactid);
        return q.getSingleResult();
    }
    
    public List<ContactEntity> findAll(Integer page, Integer maxRecords, Long authorid) {
        TypedQuery<ContactEntity> q = em.createQuery("select p from ContactEntity p where (p.author.id = :authorid)", ContactEntity.class);
        q.setParameter("authorid", authorid);
        if (page != null && maxRecords != null) {
            q.setFirstResult((page - 1) * maxRecords);
            q.setMaxResults(maxRecords);
        }
        return q.getResultList();
    }
}