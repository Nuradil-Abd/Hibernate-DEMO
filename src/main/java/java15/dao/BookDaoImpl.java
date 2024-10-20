package java15.dao;

import jakarta.persistence.EntityManager;
import java15.config.DatabaseConnection;
import java15.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao, AutoCloseable {


    private final EntityManager em = DatabaseConnection.getEntityManagerFactory().createEntityManager();

    @Override
    public boolean saveBook(Book book) {
        try {
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
            return true;
        }catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        em.close();
        return false;
    }

    @Override
    public void deleteById(Long id) {
    try{
        em.getTransaction().begin();
        Book book = em.find(Book.class, id);
        em.remove(book);
        em.getTransaction().commit();
    }catch (Exception e) {
        em.getTransaction().rollback();
        throw new RuntimeException();
    }
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try {
            em.getTransaction().begin();
           books = em.createQuery("select b from Book b",Book.class).getResultList();

            em.getTransaction().commit();
        }catch (Exception e) {
            em.getTransaction().rollback();
        }
        return books;
    }

    @Override
    public boolean updateBook(Long id,Book book) {

       try{
        em.getTransaction().begin();
        Book existingBook = em.find(Book.class, id);

        if (existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthorFullName(book.getAuthorFullName());
            existingBook.setPrice(book.getPrice());
            em.getTransaction().commit();
            return true;
        }else{
            System.out.println("book with id " + book.getId() + " not found");
            return false;
        }
       }catch (Exception e) {
           if (em.getTransaction().isActive()) {
               em.getTransaction().rollback();
           }
           System.out.println(e.getMessage());
           return false;
       }

    }

    @Override
    public Book findById(long id) {
        Book book = null;
       try{
            book = em.find(Book.class, id);
       }catch (Exception e) {
           System.out.println("book with id " + id + " not found");
       }
        return book;
    }

    @Override
    public List<Book> sortByPrice(String ascOrDesc) {
        List<Book> books = new ArrayList<>();
        String order = ascOrDesc.equalsIgnoreCase("asc") ? "asc" : "desc";

        try {
            em.getTransaction().begin();
            books = em.createQuery("select b from Book b order by b.price " + order, Book.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        }
        return books;
    }

    @Override
    public boolean clear() {
        String sql = "delete from books";

        try {
            em.getTransaction().begin();
            em.createNativeQuery(sql).executeUpdate();
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("failed to clear" + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Object[]> groupByAuthorFullName() {
        List<Object[]> result = new ArrayList<>();

        try {
            em.getTransaction().begin();
            result = em.createQuery(
                    "select b.authorFullName, count(b) from Book b group by b.authorFullName", Object[].class
            ).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("failed to group by" + e.getMessage());
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        em.close();
    }
}
