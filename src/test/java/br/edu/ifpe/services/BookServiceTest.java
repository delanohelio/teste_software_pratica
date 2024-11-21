package br.edu.ifpe.services;


import br.edu.ifpe.models.Book;
import br.edu.ifpe.repositories.BookRepository;
import org.assertj.core.annotations.Beta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void getUniqueBookWhenAddOneBook() {
        Book book = Book.builder()
                .title("Java")
                .author("Jair")
                .price(10.0)
                .build();


        when(bookRepository.findAll()).thenReturn(List.of(book));
        when(bookRepository.save(book)).thenReturn(book);

        Book createdBook = bookService.createBook(book);
        List<Book> allBooks = bookService.getAllBooks();
        assertEquals(1, allBooks.size());
        assertEquals(createdBook, allBooks.getFirst());
    }

}
