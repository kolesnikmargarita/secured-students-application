package by.kolesnik.students.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity(name = "books")
@Table(name = "books")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "books")
    private Collection<User> users;
}
