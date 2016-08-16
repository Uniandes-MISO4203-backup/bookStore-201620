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

import co.edu.uniandes.csw.bookstore.entities.ScoreEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @generated
 */
@XmlRootElement
public class ScoreDTO implements Serializable{

    private Long id;
    private Integer score;
    private String description;

    /**
     * @generated
     */
    public ScoreDTO() {
    }

    /**
     * Crea un objeto ScoreDTO a partir de un objeto ScoreEntity.
     *
     * @param entity Entidad ScoreEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public ScoreDTO(ScoreEntity entity) {
	   if (entity!=null){
        this.id=entity.getId();
        this.score=entity.getScore();
        this.description=entity.getDescription();
       }
    }

    /**
     * Convierte un objeto ScoreDTO a ScoreEntity.
     *
     * @return Nueva objeto ScoreEntity.
     * @generated
     */
    public ScoreEntity toEntity() {
        ScoreEntity entity = new ScoreEntity();
        entity.setId(this.getId());
        entity.setScore(this.getScore());
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
     * Obtiene el atributo score.
     *
     * @return atributo score.
     * @generated
     */
    public Integer getScore() {
        return score;
    }

    /**
     * Establece el valor del atributo score.
     *
     * @param score nuevo valor del atributo
     * @generated
     */
    public void setScore(Integer score) {
        this.score = score;
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
