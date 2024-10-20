package java15.config;

import jakarta.persistence.EntityManagerFactory;
import java15.entity.Book;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class DatabaseConnection {

    public static EntityManagerFactory getEntityManagerFactory() {
        Properties properties = new Properties();
        properties.put(Environment.JAKARTA_JDBC_URL, "jdbc:postgresql://localhost:5432/postgres");
        properties.put(Environment.JAKARTA_JDBC_USER, "postgres");
        properties.put(Environment.JAKARTA_JDBC_PASSWORD, "1234");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.SHOW_SQL, "true");

        Configuration configuration = new Configuration();
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(Book.class);
        return configuration.buildSessionFactory().unwrap(EntityManagerFactory.class);




    }

}
