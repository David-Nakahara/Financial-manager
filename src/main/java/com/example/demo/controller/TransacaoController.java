package com.example.demo.controller;

import com.example.demo.model.Transacao;
import com.example.demo.model.TransacaoRequest;
import com.example.demo.service.TransacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transacoes")
@CrossOrigin(origins = "http://localhost:5173")
public class TransacaoController {

    private final TransacaoService transacaoService;
    

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping("/conta/{contaId}")
    public List<Transacao> listarTransacaosPorConta(@PathVariable Long contaId) {
        return transacaoService.listarTransacoesPorConta(contaId);
    }

    @PostMapping("/depositar/{contaId}")
public ResponseEntity<Transacao> depositar(@PathVariable Long contaId,
    @RequestBody TransacaoRequest request) {
    return ResponseEntity.ok(transacaoService.depositar(contaId, request.getValor(), request.getDescricao(), request.getCategoria()));
}

    @PostMapping("/sacar/{contaId}")
public ResponseEntity<Transacao> sacar(@PathVariable Long contaId,
    @RequestBody TransacaoRequest request) {
    return ResponseEntity.ok(transacaoService.sacar(contaId, request.getValor(), request.getDescricao(), request.getCategoria()));

}

@PutMapping("/{id}")
public ResponseEntity<Transacao> editar(@PathVariable Long id, @RequestBody TransacaoRequest request) {
    return ResponseEntity.ok(transacaoService.editar(id, request.getValor(), request.getDescricao(), request.getCategoria()));
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deletar(@PathVariable Long id) {
    transacaoService.deletar(id);
    return ResponseEntity.noContent().build();
}

}