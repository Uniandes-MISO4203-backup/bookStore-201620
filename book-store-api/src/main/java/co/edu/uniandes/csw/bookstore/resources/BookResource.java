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
import co.edu.uniandes.csw.bookstore.api.IBookLogic;
import co.edu.uniandes.csw.bookstore.dtos.detail.BookDetailDTO;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 * URI: books/
 * @generated
 */
@Path("/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject private IBookLogic bookLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;

   
    /**
     * Convierte una lista de BookEntity a una lista de BookDetailDTO.
     *
     * @param entityList Lista de BookEntity a convertir.
     * @return Lista de BookDetailDTO convertida.
     * @generated
     */
    private List<BookDetailDTO> listEntity2DTO(List<BookEntity> entityList){
        List<BookDetailDTO> list = new ArrayList<>();
        for (BookEntity entity : entityList) {
            list.add(new BookDetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de Book
     *
     * @return Colección de objetos de BookDetailDTO
     * @generated
     */
    @GET
    public List<BookDetailDTO> getBooks() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", bookLogic.countBooks());
            return listEntity2DTO(bookLogic.getBooks(page, maxRecords));
        }
        return listEntity2DTO(bookLogic.getBooks());
    }

    /**
     * Obtiene los datos de una instancia de Book a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de BookDetailDTO con los datos del Book consultado
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public BookDetailDTO getBook(@PathParam("id") Long id) {
        return new BookDetailDTO(bookLogic.getBook(id));
    }

    /**
     * Se encarga de crear un Book en la base de datos
     *
     * @param dto Objeto de BookDetailDTO con los datos nuevos
     * @return Objeto de BookDetailDTOcon los datos nuevos y su ID
     * @generated
     */
    @POST
    @StatusCreated
    public BookDetailDTO createBook(BookDetailDTO dto) {
        return new BookDetailDTO(bookLogic.createBook(dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Book
     *
     * @param id Identificador de la instancia de Book a modificar
     * @param dto Instancia de BookDetailDTO con los nuevos datos
     * @return Instancia de BookDetailDTO con los datos actualizados
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public BookDetailDTO updateBook(@PathParam("id") Long id, BookDetailDTO dto) {
        BookEntity entity = dto.toEntity();
        entity.setId(id);
        BookEntity oldEntity = bookLogic.getBook(id);
        entity.setAuthors(oldEntity.getAuthors());
        return new BookDetailDTO(bookLogic.updateBook(entity));
    }

    /**
     * Elimina una instancia de Book de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteBook(@PathParam("id") Long id) {
        bookLogic.deleteBook(id);
    }
    public void existsBook(Long booksId){
        BookDetailDTO book = getBook(booksId);
        if (book== null) {
            throw new WebApplicationException(404);
        }
    }
    
    
    @Path("{booksId: \\d+}/reviews")
    public Class<ReviewResource> getReviewResource(@PathParam("booksId") Long booksId){
        existsBook(booksId);
        return ReviewResource.class;
    }
    
    @Path("{booksId: \\d+}/authors")
    public Class<BookAuthorsResource> getBookAuthorsResource(@PathParam("booksId") Long booksId){
        existsBook(booksId);
        return BookAuthorsResource.class;
    }
    
}
