package br.com.java.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.java.api.model.Pessoa;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Integer>{

    List<Pessoa> findAll();

    Pessoa findById(int id);

    List<Pessoa> findByNameOrderByAgeDesc(String name);

    List<Pessoa> findByOrderByNameAsc();
    List<Pessoa> findByOrderByNameDesc();

    List<Pessoa> findByNameContaining(String letter);

    List<Pessoa> findByNameStartsWith(String letter);
    List<Pessoa> findByNameEndsWith(String letter);

    @Query(value = "SELECT SUM(age) FROM pessoas", nativeQuery = true)
    int somaIdade();

    @Query(value = "SELECT * FROM pessoas WHERE age >= :age", nativeQuery = true)
    List<Pessoa> idadeMaiorIgual(int age);

    int countById(int id);

}