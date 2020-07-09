package com.app.BookMe.repositories;

import com.app.BookMe.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LivroRep extends JpaRepository<Livro, Long> {
    @Query(value="select * From livro where id in (select min(id) from livro group by isbn)", nativeQuery = true)
    List<Livro> findDistinctIsbn();
    @Query("FROM Livro WHERE biblioteca_id = :bId and isbn = :i and disponibilidade = :disp")
    List<Livro> findByIsbnAndBibliotecaAndDisponiblidade(@Param("i") String isbn, @Param("bId") long biblioteca_id, @Param("disp") boolean disponibilidade);
    @Query(value="select distinct l.autor FROM livro l", nativeQuery = true)
    List<String> findDistinctAutor();
    @Query(value="select distinct l.editor FROM livro l", nativeQuery = true)
    List<String> findDistinctEditor();
    List<Livro> findByAutor(String autor);
    List<Livro> findLivrosByIDInAndTituloContains(List<Long> ids, String titulo);
    List<Livro> findLivrosByAutorInAndEditorInAndIDIn(List<String> autores, List<String> editores, List<Long> ids);
    @Query(value="select l.id From livro l where id in (select min(id) from livro where livro.biblioteca_id in :bId group by isbn)", nativeQuery = true)
    List<Long> findLivrosByDintinctIsbnAndBiblioteca(@Param("bId") List<Long> bIds);
}
