package br.edu.ifpe.fixtures;

import br.edu.ifpe.models.Book;

public class BookFixture {

    public static Book fixtureBook1() {
        return Book.builder()
                .title("Java")
                .author("Jair")
                .price(50.0)
                .build();
    }

    public static Book fixtureBook2() {
        return Book.builder()
                .id(2L)
                .title("Django")
                .author("Yanka")
                .price(100.0)
                .build();
    }

}
