/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.api;

import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import co.edu.uniandes.csw.bookstore.entities.AwardEntity;
import java.util.List;

/**
 *
 * @author monitor
 */

public interface IAwardLogic {

    public int countAwards();

    public List<AwardEntity> getAwards();

    public List<AwardEntity> getAwards(Integer page, Integer maxRecords);

    public AwardEntity getAward(Long id);

    public AwardEntity createAward(AwardEntity entity);

    public AwardEntity updateAward(AwardEntity entity);

    public void deleteAward(Long id);

}
