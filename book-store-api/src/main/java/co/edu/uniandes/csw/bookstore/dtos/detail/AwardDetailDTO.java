/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.dtos.detail;

import co.edu.uniandes.csw.bookstore.dtos.minimum.*;
import co.edu.uniandes.csw.bookstore.entities.AwardEntity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author monitor
 */
@XmlRootElement
public class AwardDetailDTO extends AwardDTO{


    @PodamExclude
    private AuthorDTO author;

    /**
     * @generated
     */
    public AwardDetailDTO() {
        super();
    }

    /**
     * Crea un objeto AwardDetailDTO a partir de un objeto AwardEntity incluyendo los atributos de BookDTO.
     *
     * @param entity Entidad AwardEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public AwardDetailDTO(AwardEntity entity) {
        super(entity);
        if (entity.getAuthor()!=null){
        this.author = new AuthorDTO(entity.getAuthor());
        }
        
    }

    /**
     * Convierte un objeto AwardDetailDTO a AwardEntity incluyendo los atributos de BookDTO.
     *
     * @return Nueva objeto AwardEntity.
     * @generated
     */
    @Override
    public AwardEntity toEntity() {
        AwardEntity entity = super.toEntity();
        if (this.getAuthor()!=null){
        entity.setAuthor(this.getAuthor().toEntity());
        }
        return entity;
    }

    /**
     * Obtiene el atributo author.
     *
     * @return atributo author.
     * @generated
     */
    public AuthorDTO getAuthor() {
        return author;
    }

    /**
     * Establece el valor del atributo author.
     *
     * @param author nuevo valor del atributo
     * @generated
     */
    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

}
