package java15.dao;

import java15.entity.Book;

import java.util.List;

public interface BookDao {
    boolean saveBook(Book book);

    void deleteById(Long id);

    List<Book> findAll();
    boolean updateBook(Long id, Book book);

    Book findById(long id);

    List<Book> sortByPrice(String ascOrDesc);

    boolean clear();

    List<Object[]> groupByAuthorFullName();
    List<Book> sortUniversal(String column);
}
