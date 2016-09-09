package co.edu.uniandes.csw.bookstore.api;

import co.edu.uniandes.csw.bookstore.entities.NationalityEntity;
import java.util.List;

/**
 *
 * @author asistente
 */
public interface INationalityLogic {
    public int countNationalitys();
    public List<NationalityEntity> getNationalitys();
    public List<NationalityEntity> getNationalitys(Integer page, Integer maxRecords);
    public NationalityEntity getNationality(Long id);
    public NationalityEntity createNationality(NationalityEntity entity);
    public NationalityEntity updateNationality(NationalityEntity entity);
    public void deleteNationality(Long id);
}