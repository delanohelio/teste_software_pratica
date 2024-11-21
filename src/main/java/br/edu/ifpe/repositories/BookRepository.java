package br.edu.ifpe.repositories;

import br.edu.ifpe.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
