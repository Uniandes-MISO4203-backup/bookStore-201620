/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import co.edu.uniandes.csw.bookstore.api.IAwardLogic;
import co.edu.uniandes.csw.bookstore.dtos.detail.AwardDetailDTO;
import co.edu.uniandes.csw.bookstore.entities.AwardEntity;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 * URI: awards/
 * @generated
 */
@Path("/awards")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AwardResource {

    @Inject private IAwardLogic awardLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;

   
    /**
     * Convierte una lista de AwardEntity a una lista de AwardDetailDTO.
     *
     * @param entityList Lista de AwardEntity a convertir.
     * @return Lista de AwardDetailDTO convertida.
     * @generated
     */
    private List<AwardDetailDTO> listEntity2DTO(List<AwardEntity> entityList){
        List<AwardDetailDTO> list = new ArrayList<>();
        for (AwardEntity entity : entityList) {
            list.add(new AwardDetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de Award
     *
     * @return Colección de objetos de AwardDetailDTO
     * @generated
     */
    @GET
    public List<AwardDetailDTO> getAwards() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", awardLogic.countAwards());
            return listEntity2DTO(awardLogic.getAwards(page, maxRecords));
        }
        return listEntity2DTO(awardLogic.getAwards());
    }

    /**
     * Obtiene los datos de una instancia de Award a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de AwardDetailDTO con los datos del Award consultado
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public AwardDetailDTO getAward(@PathParam("id") Long id) {
        return new AwardDetailDTO(awardLogic.getAward(id));
    }

    /**
     * Se encarga de crear un Award en la base de datos
     *
     * @param dto Objeto de AwardDetailDTO con los datos nuevos
     * @return Objeto de AwardDetailDTOcon los datos nuevos y su ID
     * @generated
     */
    @POST
    @StatusCreated
    public AwardDetailDTO createAward(AwardDetailDTO dto) {
        return new AwardDetailDTO(awardLogic.createAward(dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Award
     *
     * @param id Identificador de la instancia de Award a modificar
     * @param dto Instancia de AwardDetailDTO con los nuevos datos
     * @return Instancia de AwardDetailDTO con los datos actualizados
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public AwardDetailDTO updateAward(@PathParam("id") Long id, AwardDetailDTO dto) {
        AwardEntity entity = dto.toEntity();
        entity.setId(id);
        AwardEntity oldEntity = awardLogic.getAward(id);
        entity.setAuthor(oldEntity.getAuthor());
        return new AwardDetailDTO(awardLogic.updateAward(entity));
    }

    /**
     * Elimina una instancia de Award de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteAward(@PathParam("id") Long id) {
        awardLogic.deleteAward(id);
    }
    public void existsAward(Long awardsId){
        AwardDetailDTO award = getAward(awardsId);
        if (award== null) {
            throw new WebApplicationException(404);
        }
    }
  
}
