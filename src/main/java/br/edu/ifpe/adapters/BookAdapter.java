package br.edu.ifpe.adapters;

import br.edu.ifpe.dtos.BookDTO;
import br.edu.ifpe.models.Book;

public class BookAdapter {

    public static Book toEntity(BookDTO dto) {
       return Book.builder()
               .title(dto.getTitle())
               .author(dto.getAuthor())
               .price(dto.getPrice())
               .build();
    }

    public static BookDTO toDTO(Book book) {
        return BookDTO.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .build();
    }
}
