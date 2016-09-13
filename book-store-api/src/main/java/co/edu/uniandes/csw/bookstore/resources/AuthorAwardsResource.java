/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.resources;

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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.bookstore.api.IAuthorLogic;
import co.edu.uniandes.csw.bookstore.dtos.detail.AwardDetailDTO;
import co.edu.uniandes.csw.bookstore.entities.AwardEntity;
import java.util.ArrayList;
/**
 * URI: authors/{authorsId: \\d+}/awards
 * @generated
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthorAwardsResource {

    @Inject private IAuthorLogic authorLogic;
    @Context private HttpServletResponse response;

    /**
     * Convierte una lista de AwardEntity a una lista de AwardDetailDTO.
     *
     * @param entityList Lista de AwardEntity a convertir.
     * @return Lista de AwardDetailDTO convertida.
     * @generated
     */
    private List<AwardDetailDTO> awardsListEntity2DTO(List<AwardEntity> entityList){
        List<AwardDetailDTO> list = new ArrayList<>();
        for (AwardEntity entity : entityList) {
            list.add(new AwardDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de AwardDetailDTO a una lista de AwardEntity.
     *
     * @param dtos Lista de AwardDetailDTO a convertir.
     * @return Lista de AwardEntity convertida.
     * @generated
     */
    private List<AwardEntity> awardsListDTO2Entity(List<AwardDetailDTO> dtos){
        List<AwardEntity> list = new ArrayList<>();
        for (AwardDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

    /**
     * Obtiene una colección de instancias de AwardDetailDTO asociadas a una
     * instancia de Author
     *
     * @param authorsId Identificador de la instancia de Author
     * @return Colección de instancias de AwardDetailDTO asociadas a la instancia de Author
     * @generated
     */
    @GET
    public List<AwardDetailDTO> listAwards(@PathParam("authorsId") Long authorsId) {
        return awardsListEntity2DTO(authorLogic.listAwards(authorsId));
    }

    /**
     * Obtiene una instancia de Award asociada a una instancia de Author
     *
     * @param authorsId Identificador de la instancia de Author
     * @param awardsId Identificador de la instancia de Award
     * @generated
     */
    @GET
    @Path("{awardsId: \\d+}")
    public AwardDetailDTO getAwards(@PathParam("authorsId") Long authorsId, @PathParam("awardsId") Long awardsId) {
        return new AwardDetailDTO(authorLogic.getAwards(authorsId, awardsId));
    }

    /**
     * Asocia un Award existente a un Author
     *
     * @param authorsId Identificador de la instancia de Author
     * @param awardsId Identificador de la instancia de Award
     * @return Instancia de AwardDetailDTO que fue asociada a Author
     * @generated
     */
    @POST
    @Path("{awardsId: \\d+}")
    public AwardDetailDTO addAwards(@PathParam("authorsId") Long authorsId, @PathParam("awardsId") Long awardsId) {
        return new AwardDetailDTO(authorLogic.addAwards(authorsId, awardsId));
    }

    /**
     * Remplaza las instancias de Award asociadas a una instancia de Author
     *
     * @param authorsId Identificador de la instancia de Author
     * @param awards Colección de instancias de AwardDTO a asociar a instancia de Author
     * @return Nueva colección de AwardDTO asociada a la instancia de Author
     * @generated
     */
    @PUT
    public List<AwardDetailDTO> replaceAwards(@PathParam("authorsId") Long authorsId, List<AwardDetailDTO> awards) {
        return awardsListEntity2DTO(authorLogic.replaceAwards(authorsId, awardsListDTO2Entity(awards)));
    }

    /**
     * Desasocia un Award existente de un Author existente
     *
     * @param authorsId Identificador de la instancia de Author
     * @param awardsId Identificador de la instancia de Award
     * @generated
     */
    @DELETE
    @Path("{awardsId: \\d+}")
    public void removeAwards(@PathParam("authorsId") Long authorsId, @PathParam("awardsId") Long awardsId) {
        authorLogic.removeAwards(authorsId, awardsId);
    }
}
