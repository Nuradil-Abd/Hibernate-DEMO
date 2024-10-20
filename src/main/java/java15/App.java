package java15;

import java15.config.DatabaseConnection;
import java15.entity.Book;
import java15.services.BookService;
import java15.services.BookServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

//        DatabaseConnection.getEntityManagerFactory();

        BookService bookService = new BookServiceImpl();
//        System.out.println(bookService.saveBook(
//                new Book(
//                "Gumplen",
//                "Victor Hugo",
//                BigDecimal.valueOf(1350))
//        ));

//        System.out.println(bookService.deleteById(2L));

        List<Book>  bookList = bookService.findAll();
        System.out.println(bookList);

        //update book

//        System.out.println(bookService.update(1L,new Book(
//                "Отверженные", "Виктор Гюго", BigDecimal.valueOf(1300)
//
//        )));

        // find by id

//        System.out.println(bookService.findById(5L));

        // sort by price
//        System.out.println(bookService.sortByPrice("desc"));

        // clear
//        System.out.println(bookService.clear());

        //group by author full name
        System.out.println(bookService.groupByAuthorFullName());






    }
}
