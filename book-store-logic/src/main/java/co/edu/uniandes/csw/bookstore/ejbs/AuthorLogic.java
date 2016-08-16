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

import co.edu.uniandes.csw.bookstore.api.IAuthorLogic;
import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import co.edu.uniandes.csw.bookstore.persistence.AuthorPersistence;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import co.edu.uniandes.csw.bookstore.api.IBookLogic;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * @generated
 */
@Stateless
public class AuthorLogic implements IAuthorLogic {

    @Inject private AuthorPersistence persistence;


    @Inject private IBookLogic bookLogic;

    /**
     * Obtiene el número de registros de Author.
     *
     * @return Número de registros de Author.
     * @generated
     */
    public int countAuthors() {
        return persistence.count();
    }

    /**
     * Obtiene la lista de los registros de Author.
     *
     * @return Colección de objetos de AuthorEntity.
     * @generated
     */
    @Override
    public List<AuthorEntity> getAuthors() {
        return persistence.findAll();
    }

    /**
     * Obtiene la lista de los registros de Author indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @return Colección de objetos de AuthorEntity.
     * @generated
     */
    @Override
    public List<AuthorEntity> getAuthors(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    /**
     * Obtiene los datos de una instancia de Author a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de AuthorEntity con los datos del Author consultado.
     * @generated
     */
    public AuthorEntity getAuthor(Long id) {
        return persistence.find(id);
    }

    /**
     * Se encarga de crear un Author en la base de datos.
     *
     * @param entity Objeto de AuthorEntity con los datos nuevos
     * @return Objeto de AuthorEntity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public AuthorEntity createAuthor(AuthorEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Author.
     *
     * @param entity Instancia de AuthorEntity con los nuevos datos.
     * @return Instancia de AuthorEntity con los datos actualizados.
     * @generated
     */
    @Override
    public AuthorEntity updateAuthor(AuthorEntity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Author de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @Override
    public void deleteAuthor(Long id) {
        persistence.delete(id);
    }
  

    /**
     * Obtiene una colección de instancias de BookEntity asociadas a una
     * instancia de Author
     *
     * @param authorId Identificador de la instancia de Author
     * @return Colección de instancias de BookEntity asociadas a la instancia de Author
     * @generated
     */
    @Override
    public List<BookEntity> listBooks(Long authorId) {
        return getAuthor(authorId).getBooks();
    }

    /**
     * Obtiene una instancia de BookEntity asociada a una instancia de Author
     *
     * @param authorId Identificador de la instancia de Author
     * @param booksId Identificador de la instancia de Book
     * @generated
     */
    @Override
    public BookEntity getBooks(Long authorId, Long booksId) {
        List<BookEntity> list = getAuthor(authorId).getBooks();
        BookEntity booksEntity = new BookEntity();
        booksEntity.setId(booksId);
        int index = list.indexOf(booksEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia un Book existente a un Author
     *
     * @param authorId Identificador de la instancia de Author
     * @param booksId Identificador de la instancia de Book
     * @return Instancia de BookEntity que fue asociada a Author
     * @generated
     */
    @Override
    public BookEntity addBooks(Long authorId, Long booksId) {
        bookLogic.addAuthors(booksId, authorId);
        return bookLogic.getBook(booksId);
    }

    /**
     * Remplaza las instancias de Book asociadas a una instancia de Author
     *
     * @param authorId Identificador de la instancia de Author
     * @param list Colección de instancias de BookEntity a asociar a instancia de Author
     * @return Nueva colección de BookEntity asociada a la instancia de Author
     * @generated
     */
    @Override
    public List<BookEntity> replaceBooks(Long authorId, List<BookEntity> list) {
        AuthorEntity authorEntity = getAuthor(authorId);
        List<BookEntity> bookList = bookLogic.getBooks();
        for (BookEntity book : bookList) {
            if (list.contains(book)) {
                if (!book.getAuthors().contains(authorEntity)) {
                    bookLogic.addAuthors(book.getId(), authorId);
                }
            } else {
                bookLogic.removeAuthors(book.getId(), authorId);
            }
        }
        authorEntity.setBooks(list);
        return authorEntity.getBooks();
    }

    /**
     * Desasocia un Book existente de un Author existente
     *
     * @param authorId Identificador de la instancia de Author
     * @param booksId Identificador de la instancia de Book
     * @generated
     */
    @Override
    public void removeBooks(Long authorId, Long booksId) {
        bookLogic.removeAuthors(booksId, authorId);
    }
}
