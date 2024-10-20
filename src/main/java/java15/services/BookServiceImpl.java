package java15.services;

import java15.dao.BookDao;
import java15.dao.BookDaoImpl;
import java15.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();


    @Override
    public String saveBook(Book book) {
        return bookDao.saveBook(book) ? "Saved" : "Failed";
    }

    @Override
    public String deleteById(Long id) {
        String result = "Deleted";
        try {
            bookDao.deleteById(id);
        } catch (Exception e) {
            result = "Not Deleted";
        }
        return result;
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public String update(Long id,Book book) {
        try{
            boolean isUpdated = bookDao.updateBook(id, book);
            if(isUpdated){
                return "Updated";
            }else {
                return "Book with id " + id + " not found";
            }
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
            return "Failed";
        }
    }

    @Override
    public Book findById(long id) {
        return bookDao.findById(id);
    }

    @Override
    public List<Book> sortByPrice(String ascOrDesc) {
        return bookDao.sortByPrice(ascOrDesc);
    }

    @Override
    public boolean clear() {
        return bookDao.clear();
    }

    @Override
    public List<Object[]> groupByAuthorFullName() {
        List<Object[]> results = bookDao.groupByAuthorFullName();
        for (Object[] result : results) {
            String authorFullName = (String) result[0];
            Long bookCount = (Long) result[1];
            System.out.println("Author :" + authorFullName + " bookCount " + bookCount);
        }
        return results;
    }
}
