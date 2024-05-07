package br.mackenzie.restapi.medias.controller;

import java.util.*;

import org.springframework.web.server.ResponseStatusException;

import br.mackenzie.restapi.medias.entity.Filme;
import br.mackenzie.restapi.medias.repository.FilmeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class FilmeController {
  @Autowired
  private FilmeRepository repository;

  @GetMapping("/filmes")
  public List<Filme> getFilmes() {
    return repository.findAll();
  }

  // @GetMapping("/filmes/procurar")
  // public List<Filme> getfilmesFromTime(@RequestParam String filme) {
  //   return repository.findByTituloIgnoreCaseOrAutorIgnoreCase(filme, filme);
  // }

  @GetMapping("/filmes/{id}")
  public ResponseEntity<Filme> getFilmeById(@PathVariable UUID id) {
    Optional<Filme> filme = repository.findById(id);
    if (filme.isPresent()) {
      return ResponseEntity.ok(filme.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // @PostMapping("/filmes")
  // public Filme postNewFilme(@RequestBody Filme filmeBody) {
  //   Filme novoFilme = new Filme(filmeBody.getTitulo(), filmeBody.getAutor(), filmeBody.getPais(), filmeBody.getEditora(), filmeBody.getAno(),filmeBody.getDescricao());
  //   getFilmes().add(novoFilme);
  //   return repository.save(novoFilme);
  // }

  @PostMapping("/filmes")
  //@PreAuthorize("hasRole('ADMIN')")
  public Filme postFilme(@RequestBody Filme filme) {
      return repository.save(filme);
  }

  @PutMapping("/filmes/{id}")
  Optional<Filme> updateFilme(@RequestBody Filme filmeBody, @PathVariable UUID id) {
    Optional<Filme> opt = repository.findById(id);

    if (opt.isPresent()) {
      Filme filme = opt.get();
      opt.get().setTitulo(filmeBody.getTitulo());
      filme.setAutor(filmeBody.getAutor());
      filme.setPais(filmeBody.getPais());
      filme.setEditora(filmeBody.getEditora());
      repository.save(filme);
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return opt;
  }

  @DeleteMapping("/filmes/{id}")
  void deleteFilme(@PathVariable UUID id) {
    Optional<Filme> opt = repository.findById(id);
    if(opt.isPresent()){ 
      repository.deleteById(id);
    }else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
   
  }

}
