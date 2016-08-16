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
package co.edu.uniandes.csw.bookstore.resources;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.bookstore.api.IScoreLogic;
import co.edu.uniandes.csw.bookstore.dtos.detail.ScoreDetailDTO;
import co.edu.uniandes.csw.bookstore.entities.ScoreEntity;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 * URI: books/{scoresId: \\d+}/reviews/{scoresId: \\d+}/scores/
 * @generated
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ScoreResource {

    @Inject private IScoreLogic scoreLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    @PathParam("reviewsId") private Long reviewsId;

   
    /**
     * Convierte una lista de ScoreEntity a una lista de ScoreDetailDTO
     *
     * @param entityList Lista de ScoreEntity a convertir
     * @return Lista de ScoreDetailDTO convertida
     * @generated
     */
    private List<ScoreDetailDTO> listEntity2DTO(List<ScoreEntity> entityList){
        List<ScoreDetailDTO> list = new ArrayList<>();
        for (ScoreEntity entity : entityList) {
            list.add(new ScoreDetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de Score asociados a un Review
     *
     * @return Colección de objetos de ScoreDetailDTO
     * @generated
     */
    @GET
    public List<ScoreDetailDTO> getScores() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", scoreLogic.countScores());
            return listEntity2DTO(scoreLogic.getScores(page, maxRecords, reviewsId));
        }
        return listEntity2DTO(scoreLogic.getScores(reviewsId));
    }

    /**
     * Obtiene los datos de una instancia de Score a partir de su ID asociado a un Review
     *
     * @param scoreId Identificador de la instancia a consultar
     * @return Instancia de ScoreDetailDTO con los datos del Score consultado
     * @generated
     */
    @GET
    @Path("{scoreId: \\d+}")
    public ScoreDetailDTO getScore(@PathParam("scoreId") Long scoreId) {
        ScoreEntity entity = scoreLogic.getScore(scoreId);
        if (entity.getReview() != null && !reviewsId.equals(entity.getReview().getId())) {
            throw new WebApplicationException(404);
        }
        return new ScoreDetailDTO(entity);
    }

    /**
     * Asocia un Score existente a un Review
     *
     * @param dto Objeto de ScoreDetailDTO con los datos nuevos
     * @return Objeto de ScoreDetailDTOcon los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public ScoreDetailDTO createScore(ScoreDetailDTO dto) {
        return new ScoreDetailDTO(scoreLogic.createScore(reviewsId, dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Score.
     *
     * @param scoreId Identificador de la instancia de Score a modificar
     * @param dto Instancia de ScoreDetailDTO con los nuevos datos.
     * @return Instancia de ScoreDetailDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{scoreId: \\d+}")
    public ScoreDetailDTO updateScore(@PathParam("scoreId") Long scoreId, ScoreDetailDTO dto) {
        ScoreEntity entity = dto.toEntity();
        entity.setId(scoreId);
        return new ScoreDetailDTO(scoreLogic.updateScore(reviewsId, entity));
    }

    /**
     * Elimina una instancia de Score de la base de datos.
     *
     * @param scoreId Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{scoreId: \\d+}")
    public void deleteScore(@PathParam("scoreId") Long scoreId) {
        scoreLogic.deleteScore(scoreId);
    }
    
}
