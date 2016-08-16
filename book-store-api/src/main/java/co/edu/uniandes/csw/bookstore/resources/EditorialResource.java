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
import co.edu.uniandes.csw.bookstore.api.IEditorialLogic;
import co.edu.uniandes.csw.bookstore.dtos.detail.EditorialDetailDTO;
import co.edu.uniandes.csw.bookstore.entities.EditorialEntity;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 * URI: editorials/
 * @generated
 */
@Path("/editorials")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EditorialResource {

    @Inject private IEditorialLogic editorialLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;

   
    /**
     * Convierte una lista de EditorialEntity a una lista de EditorialDetailDTO.
     *
     * @param entityList Lista de EditorialEntity a convertir.
     * @return Lista de EditorialDetailDTO convertida.
     * @generated
     */
    private List<EditorialDetailDTO> listEntity2DTO(List<EditorialEntity> entityList){
        List<EditorialDetailDTO> list = new ArrayList<>();
        for (EditorialEntity entity : entityList) {
            list.add(new EditorialDetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de Editorial
     *
     * @return Colección de objetos de EditorialDetailDTO
     * @generated
     */
    @GET
    public List<EditorialDetailDTO> getEditorials() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", editorialLogic.countEditorials());
            return listEntity2DTO(editorialLogic.getEditorials(page, maxRecords));
        }
        return listEntity2DTO(editorialLogic.getEditorials());
    }

    /**
     * Obtiene los datos de una instancia de Editorial a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de EditorialDetailDTO con los datos del Editorial consultado
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public EditorialDetailDTO getEditorial(@PathParam("id") Long id) {
        return new EditorialDetailDTO(editorialLogic.getEditorial(id));
    }

    /**
     * Se encarga de crear un Editorial en la base de datos
     *
     * @param dto Objeto de EditorialDetailDTO con los datos nuevos
     * @return Objeto de EditorialDetailDTOcon los datos nuevos y su ID
     * @generated
     */
    @POST
    @StatusCreated
    public EditorialDetailDTO createEditorial(EditorialDetailDTO dto) {
        return new EditorialDetailDTO(editorialLogic.createEditorial(dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Editorial
     *
     * @param id Identificador de la instancia de Editorial a modificar
     * @param dto Instancia de EditorialDetailDTO con los nuevos datos
     * @return Instancia de EditorialDetailDTO con los datos actualizados
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public EditorialDetailDTO updateEditorial(@PathParam("id") Long id, EditorialDetailDTO dto) {
        EditorialEntity entity = dto.toEntity();
        entity.setId(id);
        EditorialEntity oldEntity = editorialLogic.getEditorial(id);
        entity.setBooks(oldEntity.getBooks());
        return new EditorialDetailDTO(editorialLogic.updateEditorial(entity));
    }

    /**
     * Elimina una instancia de Editorial de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEditorial(@PathParam("id") Long id) {
        editorialLogic.deleteEditorial(id);
    }
    public void existsEditorial(Long editorialsId){
        EditorialDetailDTO editorial = getEditorial(editorialsId);
        if (editorial== null) {
            throw new WebApplicationException(404);
        }
    }
    
    
    @Path("{editorialsId: \\d+}/books")
    public Class<EditorialBooksResource> getEditorialBooksResource(@PathParam("editorialsId") Long editorialsId){
        existsEditorial(editorialsId);
        return EditorialBooksResource.class;
    }
    
}
