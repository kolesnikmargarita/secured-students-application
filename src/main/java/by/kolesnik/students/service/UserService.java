package by.kolesnik.students.service;

import by.kolesnik.students.entity.Book;
import by.kolesnik.students.entity.User;
import by.kolesnik.students.exception.EntityNotFoundException;
import by.kolesnik.students.repository.BookRepository;
import by.kolesnik.students.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BookService bookService;

    private User findByUserName() {
        final String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByUsername(name);
        if(user.isEmpty()) {
            throw new EntityNotFoundException("Пользователь не найден");
        }
        return user.get();
    }

    public List<String> findAllBooks() {
        final User user = findByUserName();
        return user.getBooks().stream().map(Book::getName).toList();
    }

    public void addBook(String name) {
        final Book book = bookService.findByName(name);
        final User user = findByUserName();
        final Collection<Book> books = user.getBooks();
        books.add(book);
        user.setBooks(books);
        userRepository.save(user);
    }

    public void deleteBook(String name) {
        final Book book = bookService.findByName(name);
        final User user = findByUserName();
        final Collection<Book> books = user.getBooks();
        books.remove(book);
        user.setBooks(books);
        userRepository.save(user);
    }
}
