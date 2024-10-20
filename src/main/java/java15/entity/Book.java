package java15.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_id_gen"
    )
    @SequenceGenerator(
            name = "book_id_gen",
            sequenceName = "book_seq_name",
            allocationSize = 1
    )
    private Long id;
    private String title;
    @Column(name = "author_full_name")
    private String authorFullName;
    private BigDecimal price;

    public Book(String title, String authorFullName, BigDecimal price) {
        this.title = title;
        this.authorFullName = authorFullName;
        this.price = price;
    }
}
