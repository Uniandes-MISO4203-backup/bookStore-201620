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
package co.edu.uniandes.csw.bookstore.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToOne;

/**
 * @generated
 */
@Entity
public class ScoreEntity extends BaseEntity implements Serializable {

    private Integer score;

    private String description;

    @PodamExclude
    @ManyToOne
    private ReviewEntity review;

    /**
     * Obtiene el atributo score.
     *
     * @return atributo score.
     * @generated
     */
    public Integer getScore(){
        return score;
    }

    /**
     * Establece el valor del atributo score.
     *
     * @param score nuevo valor del atributo
     * @generated
     */
    public void setScore(Integer score){
        this.score = score;
    }

    /**
     * Obtiene el atributo description.
     *
     * @return atributo description.
     * @generated
     */
    public String getDescription(){
        return description;
    }

    /**
     * Establece el valor del atributo description.
     *
     * @param description nuevo valor del atributo
     * @generated
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * Obtiene el atributo review.
     *
     * @return atributo review.
     * @generated
     */
    public ReviewEntity getReview() {
        return review;
    }

    /**
     * Establece el valor del atributo review.
     *
     * @param review nuevo valor del atributo
     * @generated
     */
    public void setReview(ReviewEntity review) {
        this.review = review;
    }
}
