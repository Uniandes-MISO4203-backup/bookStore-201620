/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.bookstore.dtos.minimum;

import co.edu.uniandes.csw.bookstore.entities.ReviewEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @generated
 */
@XmlRootElement
public class ReviewDTO implements Serializable{

    private Long id;
    private String name;
    private String source;
    private String description;

    /**
     * @generated
     */
    public ReviewDTO() {
    }

    /**
     * Crea un objeto ReviewDTO a partir de un objeto ReviewEntity.
     *
     * @param entity Entidad ReviewEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public ReviewDTO(ReviewEntity entity) {
	   if (entity!=null){
        this.id=entity.getId();
        this.name=entity.getName();
        this.source=entity.getSource();
        this.description=entity.getDescription();
       }
    }

    /**
     * Convierte un objeto ReviewDTO a ReviewEntity.
     *
     * @return Nueva objeto ReviewEntity.
     * @generated
     */
    public ReviewEntity toEntity() {
        ReviewEntity entity = new ReviewEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setSource(this.getSource());
        entity.setDescription(this.getDescription());
    return entity;
    }

    /**
     * Obtiene el atributo id.
     *
     * @return atributo id.
     * @generated
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     * @generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el atributo name.
     *
     * @return atributo name.
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el valor del atributo name.
     *
     * @param name nuevo valor del atributo
     * @generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el atributo source.
     *
     * @return atributo source.
     * @generated
     */
    public String getSource() {
        return source;
    }

    /**
     * Establece el valor del atributo source.
     *
     * @param source nuevo valor del atributo
     * @generated
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Obtiene el atributo description.
     *
     * @return atributo description.
     * @generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece el valor del atributo description.
     *
     * @param description nuevo valor del atributo
     * @generated
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
