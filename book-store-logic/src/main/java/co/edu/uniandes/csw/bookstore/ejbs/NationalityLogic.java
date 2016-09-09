package co.edu.uniandes.csw.bookstore.ejbs;

import co.edu.uniandes.csw.bookstore.api.INationalityLogic;
import co.edu.uniandes.csw.bookstore.entities.NationalityEntity;
import co.edu.uniandes.csw.bookstore.persistence.NationalityPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author asistente
 */
public class NationalityLogic implements INationalityLogic{
    @Inject private NationalityPersistence persistence;
    @Override
    public int countNationalitys() {
        return persistence.count();
    }

    @Override
    public List<NationalityEntity> getNationalitys() {
        return persistence.findAll();
    }

    @Override
    public List<NationalityEntity> getNationalitys(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    @Override
    public NationalityEntity getNationality(Long id) {
        return persistence.find(id);
    }

    @Override
    public NationalityEntity createNationality(NationalityEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public NationalityEntity updateNationality(NationalityEntity entity) {
        return persistence.update(entity);
    }

    @Override
    public void deleteNationality(Long id) {
        persistence.delete(id);
    }

}