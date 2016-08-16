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
package co.edu.uniandes.csw.bookstore.ejbs;

import co.edu.uniandes.csw.bookstore.api.IReviewLogic;
import co.edu.uniandes.csw.bookstore.entities.ReviewEntity;
import co.edu.uniandes.csw.bookstore.persistence.ReviewPersistence;
import co.edu.uniandes.csw.bookstore.api.IBookLogic;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * @generated
 */
@Stateless
public class ReviewLogic implements IReviewLogic {

    @Inject private ReviewPersistence persistence;

    @Inject
    private IBookLogic bookLogic;

    /**
     * Obtiene el número de registros de Review.
     *
     * @return Número de registros de Review.
     * @generated
     */
    public int countReviews() {
        return persistence.count();
    }

    /**
     * Obtiene la lista de los registros de Review que pertenecen a un Book.
     *
     * @param bookid id del Book el cual es padre de los Reviews.
     * @return Colección de objetos de ReviewEntity.
     * @generated
     */
    @Override
    public List<ReviewEntity> getReviews(Long bookid) {
        BookEntity book = bookLogic.getBook(bookid);
        return book.getReviews();
    }

    /**
     * Obtiene la lista de los registros de Review que pertenecen a un Book indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @param bookid id del Book el cual es padre de los Reviews.
     * @return Colección de objetos de ReviewEntity.
     * @generated
     */
    @Override
    public List<ReviewEntity> getReviews(Integer page, Integer maxRecords, Long bookid) {
        return persistence.findAll(page, maxRecords, bookid);
    }

    /**
     * Obtiene los datos de una instancia de Review a partir de su ID.
     *
     * @pre La existencia del elemento padre Book se debe garantizar.
     * @param reviewid) Identificador del Review a consultar
     * @return Instancia de ReviewEntity con los datos del Review consultado.
     * @generated
     */
    @Override
    public ReviewEntity getReview(Long reviewid) {
        try {
            return persistence.find(reviewid);
        }catch(NoResultException e){
            throw new IllegalArgumentException("El Review no existe");
        }
    }

    /**
     * Se encarga de crear un Review en la base de datos.
     *
     * @param entity Objeto de ReviewEntity con los datos nuevos
     * @param bookid id del Book el cual sera padre del nuevo Review.
     * @return Objeto de ReviewEntity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public ReviewEntity createReview(Long bookid, ReviewEntity entity) {
        BookEntity book = bookLogic.getBook(bookid);
        entity.setBook(book);
        entity = persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Review.
     *
     * @param entity Instancia de ReviewEntity con los nuevos datos.
     * @param bookid id del Book el cual sera padre del Review actualizado.
     * @return Instancia de ReviewEntity con los datos actualizados.
     * @generated
     */
    @Override
    public ReviewEntity updateReview(Long bookid, ReviewEntity entity) {
        BookEntity book = bookLogic.getBook(bookid);
        entity.setBook(book);
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Review de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param bookid id del Book el cual es padre del Review.
     * @generated
     */
    @Override
    public void deleteReview(Long id) {
        ReviewEntity old = getReview(id);
        persistence.delete(old.getId());
    }
  
}
