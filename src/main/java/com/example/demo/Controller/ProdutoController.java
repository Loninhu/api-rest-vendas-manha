package com.example.demo.Controller;

import com.example.demo.Repository.ProdutoRepository;
import com.example.demo.model.Produto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoRepository repository;
    @GetMapping()
    public ResponseEntity<List<Produto>> findAll(){
        return ResponseEntity.ok(repository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Produto> findByID(@Valid @PathVariable long id){
        return repository
                .findById(id)
                .map(p -> ResponseEntity.ok(p))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@Valid @PathVariable long id) {
        repository.findAndDelete(id);
    return ResponseEntity.noContent().build();
    }
    @PostMapping()
    public ResponseEntity<Produto> insert(@Valid @RequestBody Produto produto){
        final var newProduto = repository.save(produto);
        final var location = URI.create("/produto/"+newProduto.getId());
        return ResponseEntity.created(location).body(newProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@Valid @PathVariable long id, @RequestBody Produto produto){
        final var msg = "O ID informado não conincide com o ID do objeto enviado.";
        if(id != produto.getId())
            throw new ResponseStatusException(HttpStatus.CONFLICT, msg);
        return ResponseEntity.ok(repository.save(produto));
    }

}
