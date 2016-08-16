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

import co.edu.uniandes.csw.bookstore.api.IScoreLogic;
import co.edu.uniandes.csw.bookstore.entities.ScoreEntity;
import co.edu.uniandes.csw.bookstore.persistence.ScorePersistence;
import co.edu.uniandes.csw.bookstore.api.IReviewLogic;
import co.edu.uniandes.csw.bookstore.entities.ReviewEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * @generated
 */
@Stateless
public class ScoreLogic implements IScoreLogic {

    @Inject private ScorePersistence persistence;

    @Inject
    private IReviewLogic reviewLogic;

    /**
     * Obtiene el número de registros de Score.
     *
     * @return Número de registros de Score.
     * @generated
     */
    public int countScores() {
        return persistence.count();
    }

    /**
     * Obtiene la lista de los registros de Score que pertenecen a un Review.
     *
     * @param reviewid id del Review el cual es padre de los Scores.
     * @return Colección de objetos de ScoreEntity.
     * @generated
     */
    @Override
    public List<ScoreEntity> getScores(Long reviewid) {
        ReviewEntity review = reviewLogic.getReview(reviewid);
        return review.getScores();
    }

    /**
     * Obtiene la lista de los registros de Score que pertenecen a un Review indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @param reviewid id del Review el cual es padre de los Scores.
     * @return Colección de objetos de ScoreEntity.
     * @generated
     */
    @Override
    public List<ScoreEntity> getScores(Integer page, Integer maxRecords, Long reviewid) {
        return persistence.findAll(page, maxRecords, reviewid);
    }

    /**
     * Obtiene los datos de una instancia de Score a partir de su ID.
     *
     * @pre La existencia del elemento padre Review se debe garantizar.
     * @param scoreid) Identificador del Score a consultar
     * @return Instancia de ScoreEntity con los datos del Score consultado.
     * @generated
     */
    @Override
    public ScoreEntity getScore(Long scoreid) {
        try {
            return persistence.find(scoreid);
        }catch(NoResultException e){
            throw new IllegalArgumentException("El Score no existe");
        }
    }

    /**
     * Se encarga de crear un Score en la base de datos.
     *
     * @param entity Objeto de ScoreEntity con los datos nuevos
     * @param reviewid id del Review el cual sera padre del nuevo Score.
     * @return Objeto de ScoreEntity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public ScoreEntity createScore(Long reviewid, ScoreEntity entity) {
        ReviewEntity review = reviewLogic.getReview(reviewid);
        entity.setReview(review);
        entity = persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Score.
     *
     * @param entity Instancia de ScoreEntity con los nuevos datos.
     * @param reviewid id del Review el cual sera padre del Score actualizado.
     * @return Instancia de ScoreEntity con los datos actualizados.
     * @generated
     */
    @Override
    public ScoreEntity updateScore(Long reviewid, ScoreEntity entity) {
        ReviewEntity review = reviewLogic.getReview(reviewid);
        entity.setReview(review);
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Score de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param reviewid id del Review el cual es padre del Score.
     * @generated
     */
    @Override
    public void deleteScore(Long id) {
        ScoreEntity old = getScore(id);
        persistence.delete(old.getId());
    }
  
}
