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
package co.edu.uniandes.csw.bookstore.api;

import co.edu.uniandes.csw.bookstore.entities.EditorialEntity;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import java.util.List;

public interface IEditorialLogic {
    public int countEditorials();
    public List<EditorialEntity> getEditorials();
    public List<EditorialEntity> getEditorials(Integer page, Integer maxRecords);
    public EditorialEntity getEditorial(Long id);
    public EditorialEntity createEditorial(EditorialEntity entity); 
    public EditorialEntity updateEditorial(EditorialEntity entity);
    public void deleteEditorial(Long id);
    public List<BookEntity> listBooks(Long editorialId);
    public BookEntity getBooks(Long editorialId, Long booksId);
    public BookEntity addBooks(Long editorialId, Long booksId);
    public List<BookEntity> replaceBooks(Long editorialId, List<BookEntity> list);
    public void removeBooks(Long editorialId, Long booksId);
}
