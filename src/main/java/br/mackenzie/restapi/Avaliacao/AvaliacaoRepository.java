package br.mackenzie.restapi.Avaliacao;

import java.util.UUID;

// import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;




public interface AvaliacaoRepository extends JpaRepository<Avaliacao, UUID> {
   
}
