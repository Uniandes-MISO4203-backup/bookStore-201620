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

import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import java.util.List;

public interface IBookLogic {
    public int countBooks();
    public List<BookEntity> getBooks();
    public List<BookEntity> getBooks(Integer page, Integer maxRecords);
    public BookEntity getBook(Long id);
    public BookEntity createBook(BookEntity entity); 
    public BookEntity updateBook(BookEntity entity);
    public void deleteBook(Long id);
    public List<AuthorEntity> listAuthors(Long bookId);
    public AuthorEntity getAuthors(Long bookId, Long authorsId);
    public AuthorEntity addAuthors(Long bookId, Long authorsId);
    public List<AuthorEntity> replaceAuthors(Long bookId, List<AuthorEntity> list);
    public void removeAuthors(Long bookId, Long authorsId);
}
