package by.kolesnik.students.controller;

import by.kolesnik.students.entity.Book;
import by.kolesnik.students.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<String> findAllBooks() {
        return userService.findAllBooks();
    }

    @PostMapping("/{name}")
    public void addBook(@PathVariable String name) {
        userService.addBook(name);
    }

    @DeleteMapping("/{name}")
    public void deleteBook(@PathVariable String name) {
        userService.deleteBook(name);
    }
}
