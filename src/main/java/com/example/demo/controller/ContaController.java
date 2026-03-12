package com.example.demo.controller;


import com.example.demo.model.Conta;
import com.example.demo.service.ContaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/contas")
@CrossOrigin(origins = "http://localhost:5173")

public class ContaController {
    
    private final ContaService contaService;

    public ContaController (ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping
    public List<Conta> listarContas() {
        return contaService.listarContas();
    }

    @PostMapping
    public ResponseEntity<Conta> criarConta(@RequestBody Conta conta){
        Conta novaConta = contaService.criarConta(conta);
        return ResponseEntity.ok(novaConta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConta(@PathVariable long id) {
        contaService.deletarConta(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conta> atualizarConta(@PathVariable long id, @RequestBody Conta contaAtualizada){
        Conta conta = contaService.atualizarConta(id, contaAtualizada);
        return ResponseEntity.ok(conta);
    }
}