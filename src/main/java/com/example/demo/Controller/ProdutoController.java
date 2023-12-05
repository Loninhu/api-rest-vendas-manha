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

    @GetMapping
    public List<Produto> findAll(){
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public Produto findById(@PathVariable long id){
        return repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    public Produto insert(@RequestBody Produto produto){
        return repository.save(produto);
    }
}
