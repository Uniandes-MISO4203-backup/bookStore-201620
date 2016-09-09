package co.edu.uniandes.csw.bookstore.dtos.minimum;

import co.edu.uniandes.csw.bookstore.entities.NationalityEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asistente
 */
@XmlRootElement
public class NationalityDTO implements Serializable{
    private Long id;
    private String name;
    private String description;
    
    public NationalityDTO() {
    }
    
     public NationalityDTO(NationalityEntity entity) {
	   if (entity!=null){
        this.id=entity.getId();
        this.name=entity.getName();
        this.description=entity.getDescription();
       }
    }
     
     public NationalityEntity toEntity() {
        NationalityEntity entity = new NationalityEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setDescription(this.getDescription());
    return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}