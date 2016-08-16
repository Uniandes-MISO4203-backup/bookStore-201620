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

import co.edu.uniandes.csw.bookstore.api.IEditorialLogic;
import co.edu.uniandes.csw.bookstore.entities.EditorialEntity;
import co.edu.uniandes.csw.bookstore.persistence.EditorialPersistence;
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
public class EditorialLogic implements IEditorialLogic {

    @Inject private EditorialPersistence persistence;


    @Inject private IBookLogic bookLogic;

    /**
     * Obtiene el número de registros de Editorial.
     *
     * @return Número de registros de Editorial.
     * @generated
     */
    public int countEditorials() {
        return persistence.count();
    }

    /**
     * Obtiene la lista de los registros de Editorial.
     *
     * @return Colección de objetos de EditorialEntity.
     * @generated
     */
    @Override
    public List<EditorialEntity> getEditorials() {
        return persistence.findAll();
    }

    /**
     * Obtiene la lista de los registros de Editorial indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @return Colección de objetos de EditorialEntity.
     * @generated
     */
    @Override
    public List<EditorialEntity> getEditorials(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    /**
     * Obtiene los datos de una instancia de Editorial a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de EditorialEntity con los datos del Editorial consultado.
     * @generated
     */
    public EditorialEntity getEditorial(Long id) {
        return persistence.find(id);
    }

    /**
     * Se encarga de crear un Editorial en la base de datos.
     *
     * @param entity Objeto de EditorialEntity con los datos nuevos
     * @return Objeto de EditorialEntity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public EditorialEntity createEditorial(EditorialEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Editorial.
     *
     * @param entity Instancia de EditorialEntity con los nuevos datos.
     * @return Instancia de EditorialEntity con los datos actualizados.
     * @generated
     */
    @Override
    public EditorialEntity updateEditorial(EditorialEntity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Editorial de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @Override
    public void deleteEditorial(Long id) {
        persistence.delete(id);
    }
  

    /**
     * Obtiene una colección de instancias de BookEntity asociadas a una
     * instancia de Editorial
     *
     * @param editorialId Identificador de la instancia de Editorial
     * @return Colección de instancias de BookEntity asociadas a la instancia de Editorial
     * @generated
     */
    @Override
    public List<BookEntity> listBooks(Long editorialId) {
        return getEditorial(editorialId).getBooks();
    }

    /**
     * Obtiene una instancia de BookEntity asociada a una instancia de Editorial
     *
     * @param editorialId Identificador de la instancia de Editorial
     * @param booksId Identificador de la instancia de Book
     * @generated
     */
    @Override
    public BookEntity getBooks(Long editorialId, Long booksId) {
        List<BookEntity> list = getEditorial(editorialId).getBooks();
        BookEntity booksEntity = new BookEntity();
        booksEntity.setId(booksId);
        int index = list.indexOf(booksEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia un Book existente a un Editorial
     *
     * @param editorialId Identificador de la instancia de Editorial
     * @param booksId Identificador de la instancia de Book
     * @return Instancia de BookEntity que fue asociada a Editorial
     * @generated
     */
    @Override
    public BookEntity addBooks(Long editorialId, Long booksId) {
        EditorialEntity editorialEntity = getEditorial(editorialId);
        BookEntity booksEntity = bookLogic.getBook(booksId);
        booksEntity.setEditorial(editorialEntity);
        return booksEntity;
    }

    /**
     * Remplaza las instancias de Book asociadas a una instancia de Editorial
     *
     * @param editorialId Identificador de la instancia de Editorial
     * @param list Colección de instancias de BookEntity a asociar a instancia de Editorial
     * @return Nueva colección de BookEntity asociada a la instancia de Editorial
     * @generated
     */
    @Override
    public List<BookEntity> replaceBooks(Long editorialId, List<BookEntity> list) {
        EditorialEntity editorialEntity = getEditorial(editorialId);
        List<BookEntity> bookList = bookLogic.getBooks();
        for (BookEntity book : bookList) {
            if (list.contains(book)) {
                book.setEditorial(editorialEntity);
            } else {
                if (book.getEditorial() != null && book.getEditorial().equals(editorialEntity)) {
                    book.setEditorial(null);
                }
            }
        }
        editorialEntity.setBooks(list);
        return editorialEntity.getBooks();
    }

    /**
     * Desasocia un Book existente de un Editorial existente
     *
     * @param editorialId Identificador de la instancia de Editorial
     * @param booksId Identificador de la instancia de Book
     * @generated
     */
    @Override
    public void removeBooks(Long editorialId, Long booksId) {
        BookEntity entity = bookLogic.getBook(booksId);
        entity.setEditorial(null);
    }
}
