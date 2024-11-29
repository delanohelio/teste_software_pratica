package br.edu.ifpe.services;


import br.edu.ifpe.dtos.BookDTO;
import br.edu.ifpe.fixtures.BookDTOFixture;
import br.edu.ifpe.fixtures.BookFixture;
import br.edu.ifpe.models.Book;
import br.edu.ifpe.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void getUniqueBookWhenAddOneBook() {
        Book book = BookFixture.fixtureBook1();

        when(bookRepository.save(book)).thenReturn(book);
        when(bookRepository.findAll()).thenReturn(List.of(book));

        BookDTO bookDTO = BookDTOFixture.fixtureBookDTO1();

        BookDTO createdBook = bookService.createBook(bookDTO);
        List<BookDTO> allBooks = bookService.getAllBooks();
        assertEquals(1, allBooks.size());
        assertEquals(createdBook, allBooks.getFirst());
    }

    @Test
    public void deleteBookInRepositoryWhenDeleteBookWasCalled() {
        Long dummyId = 1L;
        bookService.deleteBook(dummyId);
        verify(bookRepository).deleteById(dummyId);
        verify(bookRepository, times(1)).deleteById(dummyId);
    }

    @Test
    public void deleteBooksInRepositoryWhenDeleteInBatchWasCalled() {
        List<Long> dummyListId = List.of(1L, 2L, 3L);
        bookService.deleteInBatch(dummyListId);
        dummyListId.stream().forEach(id -> verify(bookRepository).deleteById(id));
    }

//    @Test
//    public void whenUpdateBookThatExistShouldReturnUpdatedBook() {
//        Book book = BookFixture.fixtureBook1();
//
//        String dummyNewTitle = "Novo Titulo";
//        Book bookUpdated = BookFixture.fixtureBook1();
//        bookUpdated.setTitle(dummyNewTitle);
//
//        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
//        when(bookRepository.save(book)).thenReturn(book);
//
//        BookDTO bookDTO = BookDTOFixture.fixtureBookDTO1();
//
//        BookDTO updatedBook = bookService.updateBook(book.getId(), bookDTO);
//
//        assertEquals(dummyNewTitle, updatedBook.getTitle());
//    }

//    @Test
    public void whenUpdateBookThatNotExistShouldThrowAnException() {
        Long dummyId = 1L;
        BookDTO bookDTO = BookDTOFixture.fixtureBookDTO1();
        assertThrows(RuntimeException.class,
                () -> bookService.updateBook(dummyId, bookDTO));
    }

//    @Test
//    public void whenCreateTwoBookWithSameTitleShouldThrowAnException(){
//        Book book = BookFixture.fixtureBook2();
//
//        when(bookRepository.save(book)).thenReturn(book);
//
//        BookDTO bookDTO = BookDTOFixture.fixtureBookDTO2();
//
//        BookDTO createdBook = bookService.createBook(bookDTO);
//        assertEquals(createdBook, bookDTO);
//
//        doThrow(RuntimeException.class).when(bookRepository).save(book);
//        Book createdBook2 = bookService.createBook(book);
//        assertNull(createdBook2);
//
//
//    }

}
