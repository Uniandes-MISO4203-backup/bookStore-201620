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
import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.CascadeType;

/**
 * @generated
 */
@Entity
public class ReviewEntity extends BaseEntity implements Serializable {

    private String source;

    private String description;

    @PodamExclude
    @ManyToOne
    private BookEntity book;

    @PodamExclude
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScoreEntity> scores = new ArrayList<>();

    /**
     * Obtiene el atributo source.
     *
     * @return atributo source.
     * @generated
     */
    public String getSource(){
        return source;
    }

    /**
     * Establece el valor del atributo source.
     *
     * @param source nuevo valor del atributo
     * @generated
     */
    public void setSource(String source){
        this.source = source;
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
     * Obtiene el atributo book.
     *
     * @return atributo book.
     * @generated
     */
    public BookEntity getBook() {
        return book;
    }

    /**
     * Establece el valor del atributo book.
     *
     * @param book nuevo valor del atributo
     * @generated
     */
    public void setBook(BookEntity book) {
        this.book = book;
    }

    /**
     * Obtiene la colección de scores.
     *
     * @return colección scores.
     * @generated
     */
    public List<ScoreEntity> getScores() {
        return scores;
    }

    /**
     * Establece el valor de la colección de scores.
     *
     * @param scores nuevo valor de la colección.
     * @generated
     */
    public void setScores(List<ScoreEntity> scores) {
        this.scores = scores;
    }
}
