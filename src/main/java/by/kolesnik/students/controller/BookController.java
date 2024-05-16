package by.kolesnik.students.controller;

import by.kolesnik.students.entity.Book;
import by.kolesnik.students.entity.User;
import by.kolesnik.students.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final Set<String> books = ConcurrentHashMap.newKeySet();

    {
        books.add("Пушкин");
        books.add("Плюшкин");
        books.add("Есенин");
    }

    @GetMapping
    public List<String> findAll() {
        return books.stream().toList();
    }


    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable String name) {
        books.remove(name);
    }
}
