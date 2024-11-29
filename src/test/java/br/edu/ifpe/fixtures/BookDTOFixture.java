package br.edu.ifpe.fixtures;

import br.edu.ifpe.dtos.BookDTO;
import br.edu.ifpe.models.Book;

public class BookDTOFixture {

    public static BookDTO fixtureBookDTO1() {
        return BookDTO.builder()
                .title("Java")
                .author("Jair")
                .price(50.0)
                .build();
    }

    public static BookDTO fixtureBookDTO2() {
        return BookDTO.builder()
                .title("Django")
                .author("Yanka")
                .price(100.0)
                .build();
    }

    public static BookDTO fixtureBookDTO3() {
        return BookDTO.builder()
                .title("Clean Code")
                .author("Robert C. Martin")
                .price(45.0)
                .build();
    }

    public static BookDTO fixtureBookDTO4() {
        return BookDTO.builder()
                .title("Refactoring")
                .author("Martin Fowler")
                .price(50.0)
                .build();
    }

}
