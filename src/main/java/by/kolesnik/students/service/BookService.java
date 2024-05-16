package by.kolesnik.students.service;

import by.kolesnik.students.entity.Book;
import by.kolesnik.students.exception.EntityNotFoundException;
import by.kolesnik.students.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book findByName(String name) {
        final Optional<Book> book = bookRepository.findByName(name);
        if(book.isEmpty()) {
            throw new EntityNotFoundException("Книга не найдена");
        }
        return book.get();
    }
}
