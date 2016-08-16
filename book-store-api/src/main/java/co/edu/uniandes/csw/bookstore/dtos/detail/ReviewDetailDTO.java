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
package co.edu.uniandes.csw.bookstore.dtos.detail;

import co.edu.uniandes.csw.bookstore.dtos.minimum.*;
import co.edu.uniandes.csw.bookstore.entities.ReviewEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@XmlRootElement
public class ReviewDetailDTO extends ReviewDTO{


    @PodamExclude
    private BookDTO book;

    /**
     * @generated
     */
    public ReviewDetailDTO() {
        super();
    }

    /**
     * Crea un objeto ReviewDetailDTO a partir de un objeto ReviewEntity incluyendo los atributos de ReviewDTO.
     *
     * @param entity Entidad ReviewEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public ReviewDetailDTO(ReviewEntity entity) {
        super(entity);
        if (entity.getBook()!=null){
        this.book = new BookDTO(entity.getBook());
        }
        
    }

    /**
     * Convierte un objeto ReviewDetailDTO a ReviewEntity incluyendo los atributos de ReviewDTO.
     *
     * @return Nueva objeto ReviewEntity.
     * @generated
     */
    @Override
    public ReviewEntity toEntity() {
        ReviewEntity entity = super.toEntity();
        if (this.getBook()!=null){
        entity.setBook(this.getBook().toEntity());
        }
        return entity;
    }

    /**
     * Obtiene el atributo book.
     *
     * @return atributo book.
     * @generated
     */
    public BookDTO getBook() {
        return book;
    }

    /**
     * Establece el valor del atributo book.
     *
     * @param book nuevo valor del atributo
     * @generated
     */
    public void setBook(BookDTO book) {
        this.book = book;
    }

}
