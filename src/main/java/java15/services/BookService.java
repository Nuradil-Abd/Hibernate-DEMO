package java15.services;

import java15.entity.Book;

import java.util.List;

public interface BookService {

    String saveBook(Book book);
    String deleteById (Long id);
    List<Book> findAll();
    String update (Long id, Book book);

    Book findById(long id);

    List<Book> sortByPrice(String ascOrDesc);

    boolean clear();

    List<Object[]> groupByAuthorFullName();
}
