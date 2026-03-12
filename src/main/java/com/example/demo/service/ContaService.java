package com.example.demo.service;

import com.example.demo.model.Conta;
import com.example.demo.model.Transacao;
import com.example.demo.repository.ContaRepository;
import com.example.demo.repository.TransacaoRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final TransacaoRepository transacaoRepository;

    public ContaService(ContaRepository contaRepository, TransacaoRepository transacaoRepository) {
        this.contaRepository = contaRepository;
        this.transacaoRepository = transacaoRepository;
    }

    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

    public Conta criarConta(Conta conta) {
        Conta contaSalva = contaRepository.save(conta);

        if (contaSalva.getSaldo() > 0) {
            Transacao transacao = new Transacao();
            transacao.setTipo("DEPÓSITO");
            transacao.setValor(contaSalva.getSaldo());
            transacao.setData(LocalDateTime.now());
            transacao.setConta(contaSalva);
            transacaoRepository.save(transacao);
        }

        return contaSalva;
    }

    public void deletarConta(Long id) {
        contaRepository.deleteById(id);
    }

    public Conta atualizarConta(Long id, Conta contaAtualizada) {
        return contaRepository.findById(id)
            .map(conta -> {
                conta.setNome(contaAtualizada.getNome());
                conta.setSaldo(contaAtualizada.getSaldo());
                return contaRepository.save(conta);
            })
            .orElseThrow();
    }
}