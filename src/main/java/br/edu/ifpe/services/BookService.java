package br.edu.ifpe.services;

import br.edu.ifpe.dtos.BookDTO;
import br.edu.ifpe.repositories.BookRepository;
import br.edu.ifpe.adapters.BookAdapter;
import br.edu.ifpe.models.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class BookService {
    
    private final BookRepository bookRepository;

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookAdapter::toDTO)
                .collect(Collectors.toList());
    }

    public BookDTO getBookById(Long id) {
        return bookRepository.findById(id)
                .map(BookAdapter::toDTO)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public BookDTO createBook(BookDTO bookDTO) {
        Book book = BookAdapter.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return BookAdapter.toDTO(savedBook);
    }

    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPrice(bookDTO.getPrice());
        Book updatedBook = bookRepository.save(book);
        return BookAdapter.toDTO(updatedBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public void deleteInBatch(List<Long> listIds) {
        listIds.forEach(id -> bookRepository.deleteById(id));
    }
}
