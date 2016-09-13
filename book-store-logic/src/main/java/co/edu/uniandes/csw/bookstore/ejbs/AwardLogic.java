/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.ejbs;

import co.edu.uniandes.csw.bookstore.api.IAwardLogic;
import co.edu.uniandes.csw.bookstore.entities.AwardEntity;
import co.edu.uniandes.csw.bookstore.persistence.AwardPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author monitor
 */
@Stateless

public class AwardLogic implements IAwardLogic {

    @Inject
    private AwardPersistence persistence;

    @Override
    public int countAwards() {
        return persistence.count();
    }

    @Override
    public List<AwardEntity> getAwards() {
        return persistence.findAll();
    }

    @Override
    public List<AwardEntity> getAwards(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    @Override
    public AwardEntity getAward(Long id) {
        return persistence.find(id);
    }

    @Override
    public AwardEntity createAward(AwardEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public AwardEntity updateAward(AwardEntity entity) {
        return persistence.update(entity);
    }

    @Override
    public void deleteAward(Long id) {
        persistence.delete(id);
    }

}
