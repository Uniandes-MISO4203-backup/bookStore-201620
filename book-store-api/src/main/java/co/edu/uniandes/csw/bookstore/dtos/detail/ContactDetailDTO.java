package co.edu.uniandes.csw.bookstore.dtos.detail;

import co.edu.uniandes.csw.bookstore.dtos.minimum.*;
import co.edu.uniandes.csw.bookstore.entities.ContactEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author asistente
 */
@XmlRootElement
public class ContactDetailDTO extends ContactDTO {
        
    @PodamExclude
    private AuthorDTO author;

    public ContactDetailDTO() {
        super();
    }  

    public ContactDetailDTO(ContactEntity entity) {
        super(entity);
        if (entity.getAuthor()!=null){
        this.author= new AuthorDTO(entity.getAuthor());
        }
        
    }

    @Override
    public ContactEntity toEntity() {
        ContactEntity entity = super.toEntity();
        if (this.getAuthor()!=null){
        entity.setAuthor(this.getAuthor().toEntity());
        }
        return entity;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }
}
    