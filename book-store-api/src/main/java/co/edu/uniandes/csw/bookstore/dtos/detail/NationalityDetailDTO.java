/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.dtos.detail;

import co.edu.uniandes.csw.bookstore.dtos.minimum.NationalityDTO;
import co.edu.uniandes.csw.bookstore.entities.NationalityEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asistente
 */
@XmlRootElement
public class NationalityDetailDTO extends NationalityDTO{
    public NationalityDetailDTO() {
        super();
    }
    
    public NationalityDetailDTO(NationalityEntity entity) {
        super(entity);
        
    }
    
    @Override
    public NationalityEntity toEntity() {
        NationalityEntity entity = super.toEntity();
        return entity;
    }
}
