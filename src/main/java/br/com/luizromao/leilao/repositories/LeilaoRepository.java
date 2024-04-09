package br.com.luizromao.leilao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luizromao.leilao.model.Leilao;

@Repository
public interface LeilaoRepository extends JpaRepository<Leilao, Long> {

}
