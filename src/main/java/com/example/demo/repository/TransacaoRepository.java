package com.example.demo.repository;

import com.example.demo.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface TransacaoRepository extends JpaRepository<Transacao,Long> {
    List<Transacao> findByConta_Id(Long contaId);

}

