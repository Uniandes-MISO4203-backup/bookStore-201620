package co.edu.uniandes.csw.bookstore.resources;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.bookstore.api.INationalityLogic;
import co.edu.uniandes.csw.bookstore.dtos.detail.NationalityDetailDTO;
import co.edu.uniandes.csw.bookstore.entities.NationalityEntity;
import java.util.ArrayList;
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

/**
 *
 * @author asistente
 */
@Path("/nationalitys")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NationalityResource {
    
    @Inject private INationalityLogic nationalityLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    private List<NationalityDetailDTO> listEntity2DTO(List<NationalityEntity> entityList){
        List<NationalityDetailDTO> list = new ArrayList<>();
        for (NationalityEntity entity : entityList) {
            list.add(new NationalityDetailDTO(entity));
        }
        return list;
    }
    
    @GET
    public List<NationalityDetailDTO> getNationalitys() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", nationalityLogic.countNationalitys());
            return listEntity2DTO(nationalityLogic.getNationalitys(page, maxRecords));
        }
        return listEntity2DTO(nationalityLogic.getNationalitys());
    }
    
    @GET
    @Path("{id: \\d+}")
    public NationalityDetailDTO getNationality(@PathParam("id") Long id) {
        return new NationalityDetailDTO(nationalityLogic.getNationality(id));
    }
    
    @POST
    @StatusCreated
    public NationalityDetailDTO createNationality(NationalityDetailDTO dto) {
        return new NationalityDetailDTO(nationalityLogic.createNationality(dto.toEntity()));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public NationalityDetailDTO  updateNationality(@PathParam("id") Long id, NationalityDetailDTO dto) {
        NationalityEntity entity = dto.toEntity();
        entity.setId(id);
        return new NationalityDetailDTO(nationalityLogic.updateNationality(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteNationality(@PathParam("id") Long id) {
        nationalityLogic.deleteNationality(id);
    }
    
}