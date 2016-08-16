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
import co.edu.uniandes.csw.bookstore.dtos.detail.BookDetailDTO;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import java.util.ArrayList;
/**
 * URI: authors/{authorsId: \\d+}/books
 * @generated
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthorBooksResource {

    @Inject private IAuthorLogic authorLogic;
    @Context private HttpServletResponse response;

    /**
     * Convierte una lista de BookEntity a una lista de BookDetailDTO.
     *
     * @param entityList Lista de BookEntity a convertir.
     * @return Lista de BookDetailDTO convertida.
     * @generated
     */
    private List<BookDetailDTO> booksListEntity2DTO(List<BookEntity> entityList){
        List<BookDetailDTO> list = new ArrayList<>();
        for (BookEntity entity : entityList) {
            list.add(new BookDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de BookDetailDTO a una lista de BookEntity.
     *
     * @param dtos Lista de BookDetailDTO a convertir.
     * @return Lista de BookEntity convertida.
     * @generated
     */
    private List<BookEntity> booksListDTO2Entity(List<BookDetailDTO> dtos){
        List<BookEntity> list = new ArrayList<>();
        for (BookDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

    /**
     * Obtiene una colección de instancias de BookDetailDTO asociadas a una
     * instancia de Author
     *
     * @param authorsId Identificador de la instancia de Author
     * @return Colección de instancias de BookDetailDTO asociadas a la instancia de Author
     * @generated
     */
    @GET
    public List<BookDetailDTO> listBooks(@PathParam("authorsId") Long authorsId) {
        return booksListEntity2DTO(authorLogic.listBooks(authorsId));
    }

    /**
     * Obtiene una instancia de Book asociada a una instancia de Author
     *
     * @param authorsId Identificador de la instancia de Author
     * @param booksId Identificador de la instancia de Book
     * @generated
     */
    @GET
    @Path("{booksId: \\d+}")
    public BookDetailDTO getBooks(@PathParam("authorsId") Long authorsId, @PathParam("booksId") Long booksId) {
        return new BookDetailDTO(authorLogic.getBooks(authorsId, booksId));
    }

    /**
     * Asocia un Book existente a un Author
     *
     * @param authorsId Identificador de la instancia de Author
     * @param booksId Identificador de la instancia de Book
     * @return Instancia de BookDetailDTO que fue asociada a Author
     * @generated
     */
    @POST
    @Path("{booksId: \\d+}")
    public BookDetailDTO addBooks(@PathParam("authorsId") Long authorsId, @PathParam("booksId") Long booksId) {
        return new BookDetailDTO(authorLogic.addBooks(authorsId, booksId));
    }

    /**
     * Remplaza las instancias de Book asociadas a una instancia de Author
     *
     * @param authorsId Identificador de la instancia de Author
     * @param books Colección de instancias de BookDTO a asociar a instancia de Author
     * @return Nueva colección de BookDTO asociada a la instancia de Author
     * @generated
     */
    @PUT
    public List<BookDetailDTO> replaceBooks(@PathParam("authorsId") Long authorsId, List<BookDetailDTO> books) {
        return booksListEntity2DTO(authorLogic.replaceBooks(authorsId, booksListDTO2Entity(books)));
    }

    /**
     * Desasocia un Book existente de un Author existente
     *
     * @param authorsId Identificador de la instancia de Author
     * @param booksId Identificador de la instancia de Book
     * @generated
     */
    @DELETE
    @Path("{booksId: \\d+}")
    public void removeBooks(@PathParam("authorsId") Long authorsId, @PathParam("booksId") Long booksId) {
        authorLogic.removeBooks(authorsId, booksId);
    }
}
