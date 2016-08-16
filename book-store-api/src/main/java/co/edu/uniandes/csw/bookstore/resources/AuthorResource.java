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
import co.edu.uniandes.csw.bookstore.api.IAuthorLogic;
import co.edu.uniandes.csw.bookstore.dtos.detail.AuthorDetailDTO;
import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 * URI: authors/
 * @generated
 */
@Path("/authors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthorResource {

    @Inject private IAuthorLogic authorLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;

   
    /**
     * Convierte una lista de AuthorEntity a una lista de AuthorDetailDTO.
     *
     * @param entityList Lista de AuthorEntity a convertir.
     * @return Lista de AuthorDetailDTO convertida.
     * @generated
     */
    private List<AuthorDetailDTO> listEntity2DTO(List<AuthorEntity> entityList){
        List<AuthorDetailDTO> list = new ArrayList<>();
        for (AuthorEntity entity : entityList) {
            list.add(new AuthorDetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de Author
     *
     * @return Colección de objetos de AuthorDetailDTO
     * @generated
     */
    @GET
    public List<AuthorDetailDTO> getAuthors() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", authorLogic.countAuthors());
            return listEntity2DTO(authorLogic.getAuthors(page, maxRecords));
        }
        return listEntity2DTO(authorLogic.getAuthors());
    }

    /**
     * Obtiene los datos de una instancia de Author a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de AuthorDetailDTO con los datos del Author consultado
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public AuthorDetailDTO getAuthor(@PathParam("id") Long id) {
        return new AuthorDetailDTO(authorLogic.getAuthor(id));
    }

    /**
     * Se encarga de crear un Author en la base de datos
     *
     * @param dto Objeto de AuthorDetailDTO con los datos nuevos
     * @return Objeto de AuthorDetailDTOcon los datos nuevos y su ID
     * @generated
     */
    @POST
    @StatusCreated
    public AuthorDetailDTO createAuthor(AuthorDetailDTO dto) {
        return new AuthorDetailDTO(authorLogic.createAuthor(dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Author
     *
     * @param id Identificador de la instancia de Author a modificar
     * @param dto Instancia de AuthorDetailDTO con los nuevos datos
     * @return Instancia de AuthorDetailDTO con los datos actualizados
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public AuthorDetailDTO updateAuthor(@PathParam("id") Long id, AuthorDetailDTO dto) {
        AuthorEntity entity = dto.toEntity();
        entity.setId(id);
        AuthorEntity oldEntity = authorLogic.getAuthor(id);
        entity.setBooks(oldEntity.getBooks());
        return new AuthorDetailDTO(authorLogic.updateAuthor(entity));
    }

    /**
     * Elimina una instancia de Author de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteAuthor(@PathParam("id") Long id) {
        authorLogic.deleteAuthor(id);
    }
    public void existsAuthor(Long authorsId){
        AuthorDetailDTO author = getAuthor(authorsId);
        if (author== null) {
            throw new WebApplicationException(404);
        }
    }
    
    
    @Path("{authorsId: \\d+}/books")
    public Class<AuthorBooksResource> getAuthorBooksResource(@PathParam("authorsId") Long authorsId){
        existsAuthor(authorsId);
        return AuthorBooksResource.class;
    }
    
}
