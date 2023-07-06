package br.com.java.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.java.api.model.Pessoa;
import br.com.java.api.repository.PessoaRepository;
import br.com.java.api.service.ServicePessoa;

@RestController
public class Controller {
    
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ServicePessoa servicePessoa;

    @PostMapping("/cadastro/pessoa")
    public ResponseEntity<?> cadastrar(@RequestBody Pessoa pessoa) {
        return servicePessoa.cadastrar(pessoa);
    }

    @GetMapping("/pessoas")
    public ResponseEntity<?> selecionar() {
        return servicePessoa.selecionar();
    }

    @GetMapping("/pessoa/{id}")
    public ResponseEntity<?> selecionarById(@PathVariable int id) {
        return servicePessoa.selecionarById(id);
    }

    @PutMapping("/editar")
    public ResponseEntity<?> editarById(@RequestBody Pessoa pessoa) {
        return servicePessoa.editarById(pessoa);
    }

    @GetMapping("")
    public String message() {
        return "Hello World!";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Seja bem vindo!";
    }

    @GetMapping("/welcome/{name}")
    public String welcome(@PathVariable String name) {
        return "Seja bem vindo " + name;
    }

    @PostMapping("/pessoa")
    public Pessoa pessoa(@RequestBody Pessoa p) {
        return p;
    }

    @DeleteMapping("/deletar/pessoa/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return servicePessoa.delete(id);
    }

    @GetMapping("/count")
    public long contador() {
        return pessoaRepository.count();
    }

    @GetMapping("/ordernarNomes")
    public List<Pessoa> ordenarNomes() {
        return pessoaRepository.findByOrderByNameDesc();
    }

    @GetMapping("/pessoas/{name}")
    public List<Pessoa> selecionarNomes(@PathVariable String name) {
        return pessoaRepository.findByNameOrderByAgeDesc(name);
    }

    @GetMapping("/search/{letter}")
    public List<Pessoa> search(@PathVariable String letter) {
        return pessoaRepository.findByNameContaining(letter);
    }

    @GetMapping("/start/{letter}")
    public List<Pessoa> startWith(@PathVariable String letter) {
        return pessoaRepository.findByNameStartsWith(letter);
    }

    @GetMapping("/end/{letter}")
    public List<Pessoa> endWith(@PathVariable String letter) {
        return pessoaRepository.findByNameEndsWith(letter);
    }

    @GetMapping("/somaIdades")
    public int somaIdade() {
        return pessoaRepository.somaIdade();
    }

    @GetMapping("/idadeMaiorIgual/{age}")
    public List<Pessoa> idadeMaiorIgual(@PathVariable int age) {
        return pessoaRepository.idadeMaiorIgual(age);
    }







    @GetMapping("/status")
    public ResponseEntity<?> status() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}