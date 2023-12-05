package com.example.demo.Controller;

import com.example.demo.Repository.ProdutoRepository;
import com.example.demo.model.Produto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoRepository repository;
    @GetMapping()
    public List<Produto> list(){
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public Produto findByID(@PathVariable long id)
    {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        repository.findAndDelete(id);
    }
    @PostMapping()
    public Produto insert(@RequestBody Produto produto){
        return repository.save(produto);
    }

    @PutMapping("/{id}")
    public Produto update(@PathVariable long id, @RequestBody Produto produto){
        final var msg = "O ID informado não conincide com o ID do objeto enviado.";
        if(id != produto.getId())
            throw new ResponseStatusException(HttpStatus.CONFLICT, msg);
        return repository.save(produto);
    }

}
