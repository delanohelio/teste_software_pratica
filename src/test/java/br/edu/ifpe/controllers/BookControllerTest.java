package br.edu.ifpe.controllers;

import br.edu.ifpe.dtos.BookDTO;
import br.edu.ifpe.fixtures.BookDTOFixture;
import br.edu.ifpe.fixtures.BookFixture;
import br.edu.ifpe.services.BookService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void whenTestGetAllBooksReturnOK() throws Exception {
        BookDTO book1 = BookDTOFixture.fixtureBookDTO3();

        BookDTO book2 = BookDTOFixture.fixtureBookDTO4();

        when(bookService.getAllBooks()).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title").value(book1.getTitle()))
                .andExpect(jsonPath("$[1].title").value(book2.getTitle()));
    }

    @Test
    void whenTestGetBookByIDValidReturnOK() throws Exception {
        Long dummyId = 1L;
        BookDTO book1 = BookDTOFixture.fixtureBookDTO3();

        when(bookService.getBookById(dummyId)).thenReturn(book1);

        mockMvc.perform(get("/books/".concat(dummyId.toString())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(book1.getTitle()))
                .andExpect(jsonPath("$.author").value(book1.getAuthor()));
    }

    @Test
    void whenTestGetBookByIDInvalidReturnNotFound() throws Exception {
        Long dummyId = 1L;

        when(bookService.getBookById(dummyId)).thenThrow(RuntimeException.class);

        mockMvc.perform(get("/books/".concat(dummyId.toString())))
                .andExpect(status().isNotFound());
    }


    @Test
    void testCreateBook() throws Exception {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Clean Code");
        bookDTO.setAuthor("Robert C. Martin");
        bookDTO.setPrice(45.0);

        when(bookService.createBook(Mockito.any(BookDTO.class))).thenReturn(bookDTO);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Clean Code\",\"author\":\"Robert C. Martin\",\"price\":45.0}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Clean Code"));
    }
}
