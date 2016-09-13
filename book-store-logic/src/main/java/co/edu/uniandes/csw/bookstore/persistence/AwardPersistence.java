/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.persistence;

import co.edu.uniandes.csw.bookstore.entities.AwardEntity;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ejb.Stateless;


/**
 *
 * @author monitor
 */
@Stateless
public class AwardPersistence extends CrudPersistence<AwardEntity> {

    @PersistenceContext(unitName="BookStorePU")
    protected EntityManager em;

    /**
     * @generated
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * @generated
     */
    @Override
    protected Class<AwardEntity> getEntityClass() {
        return AwardEntity.class;
    }

}

