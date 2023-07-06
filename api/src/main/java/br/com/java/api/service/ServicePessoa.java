package br.com.java.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.java.api.model.Message;
import br.com.java.api.repository.PessoaRepository;
import br.com.java.api.model.Pessoa;

@Service
public class ServicePessoa {
    
    @Autowired
    private Message message;

    @Autowired
    private PessoaRepository pessoaRepository;

    public ResponseEntity<?> cadastrar(Pessoa pessoa) {
        if(pessoa.getName().equals("")) {
            message.setMessage("Nome precisa ser preenchido");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if(pessoa.getAge() < 0) {
            message.setMessage("Insira uma idade válida");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(pessoaRepository.save(pessoa), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<?> selecionar() {
        return new ResponseEntity<>(pessoaRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> selecionarById(int id) {
        if(pessoaRepository.countById(id) == 0) {
            message.setMessage("Não foi encontrada nenhuma pessoa com este id.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(pessoaRepository.findById(id), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> editarById(Pessoa pessoa) {
        if(pessoaRepository.countById(pessoa.getId()) == 0) {
            message.setMessage("Pessoa não encontrada");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else if(pessoa.getName().equals("")) {
            message.setMessage("Informe um nome");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if(pessoa.getAge() < 0) {
            message.setMessage("Informe uma idade válida");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(pessoaRepository.save(pessoa), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> delete(int id) {
        if(pessoaRepository.countById(id) == 0) {
            message.setMessage("Pessoa não encontrada");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            Pessoa pessoaDeletada = pessoaRepository.findById(id);
            pessoaRepository.delete(pessoaDeletada);
            message.setMessage("Pessoa "+pessoaDeletada.getName()+" deletada");

            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

}