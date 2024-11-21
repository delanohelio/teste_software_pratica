package br.edu.ifpe.services;

import br.edu.ifpe.models.Book;
import br.edu.ifpe.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book createBook(Book book) {
        Book savedBook = bookRepository.save(book);
        return savedBook;
    }

    public Book updateBook(Long id, Book book) {
        Book bookToBeupdated = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        bookToBeupdated.setTitle(book.getTitle());
        bookToBeupdated.setAuthor(book.getAuthor());
        bookToBeupdated.setPrice(book.getPrice());
        return bookRepository.save(bookToBeupdated);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}
