package com.example.demo.service;

import com.example.demo.model.Conta;
import org.springframework.stereotype.Service;
import com.example.demo.model.Transacao;
import com.example.demo.repository.ContaRepository;
import com.example.demo.repository.TransacaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final ContaRepository contaRepository;

    public TransacaoService(TransacaoRepository transacaoRepository, ContaRepository contaRepository){
        this.transacaoRepository = transacaoRepository;
        this.contaRepository = contaRepository;
    }

    public List<Transacao> listarTransacoesPorConta(Long contaId){
        return transacaoRepository.findByConta_Id(contaId);
    }

    public Transacao depositar(Long contaId, Double valor, String descricao, String categoria){
        Conta conta = buscarConta(contaId);
        validarValor(valor);

        conta.setSaldo(conta.getSaldo() + valor);
        contaRepository.save(conta);

        return registrarTransacao(conta, valor, "DEPÓSITO", descricao, categoria);
    }

    public Transacao sacar(Long contaId, Double valor, String descricao, String categoria){
        Conta conta = buscarConta(contaId);
        validarValor(valor);

        if (conta.getSaldo() < valor) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo insuficiente");
        }

        conta.setSaldo(conta.getSaldo() - valor);
        contaRepository.save(conta);

        return registrarTransacao(conta, valor, "SAQUE", descricao, categoria);
    }

    private Conta buscarConta(Long contaId) {
        return contaRepository.findById(contaId)
            .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));
    }

    private void validarValor(Double valor) {
        if (valor <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valor deve ser positivo");
        }
    }

    private Transacao registrarTransacao(Conta conta, Double valor, String tipo, String descricao, String categoria) {
        Transacao transacao = new Transacao(null, valor, LocalDateTime.now(), tipo, descricao, categoria, conta);
        return transacaoRepository.save(transacao);
    }

    public Transacao editar(Long id, Double novoValor, String novaDescricao, String novaCategoria) {
    Transacao transacao = transacaoRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transação não encontrada"));

    validarValor(novoValor);

    Conta conta = transacao.getConta();
    Double valorAntigo = transacao.getValor();

    // Reverte o efeito antigo no saldo
    if (transacao.getTipo().equals("DEPÓSITO")) {
        conta.setSaldo(conta.getSaldo() - valorAntigo);
    } else {
        conta.setSaldo(conta.getSaldo() + valorAntigo);
    }

    // Aplica o novo valor no saldo
    if (transacao.getTipo().equals("DEPÓSITO")) {
        conta.setSaldo(conta.getSaldo() + novoValor);
    } else {
        if (conta.getSaldo() < novoValor) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo insuficiente");
        }
        conta.setSaldo(conta.getSaldo() - novoValor);
    }

    contaRepository.save(conta);

    transacao.setValor(novoValor);
    transacao.setDescricao(novaDescricao);
    transacao.setCategoria(novaCategoria);

    return transacaoRepository.save(transacao);
}

public void deletar(Long id) {
    Transacao transacao = transacaoRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transação não encontrada"));

    Conta conta = transacao.getConta();

    // Reverte o saldo
    if (transacao.getTipo().equals("DEPÓSITO")) {
        conta.setSaldo(conta.getSaldo() - transacao.getValor());
    } else {
        conta.setSaldo(conta.getSaldo() + transacao.getValor());
    }

    contaRepository.save(conta);
    transacaoRepository.deleteById(id);
}
}