package br.mackenzie.restapi.Avaliacao;

import java.util.*;

// import org.springframework.web.server.ResponseStatusException;

// import br.mackenzie.restapi.Avaliacao.Avaliacao;
// import br.mackenzie.restapi.Avaliacao.AvaliacaoRepository;
// import br.mackenzie.restapi.Filme.Filme;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class AvaliacaoController {
  @Autowired
  private AvaliacaoRepository repository;


 @GetMapping("/avaliacoes")
  public List<Avaliacao> getAvaliacoes() {
    return repository.findAll();
  }


  @PostMapping("/avaliacoes")
  public Avaliacao postNewAvaliacao(@RequestBody Avaliacao avaliacaoBody) {
    Avaliacao novaAvaliacao = new Avaliacao(avaliacaoBody.getAutor(), avaliacaoBody.getDate(),avaliacaoBody.getTime(),avaliacaoBody.getComment(), avaliacaoBody.getItemId());
    getAvaliacoes().add(novaAvaliacao);
    return repository.save(novaAvaliacao);
  }
}
