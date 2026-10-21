package com.projeto.api.controller;

import com.projeto.api.model.Veiculo;
import com.projeto.api.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VeiculoController {
    private static final String ENDPOINT = "api/veiculos";

    @Autowired
    private VeiculoService _veiculoService;

    @PostMapping(ENDPOINT)
    public void createVeiculo(@Valid @RequestBody Veiculo veiculo){
        _veiculoService.create(veiculo);
    }

    @GetMapping(ENDPOINT + "/{placa}")
    public Veiculo findVeiculo(@PathVariable("placa") String placa){
        return _veiculoService.findOne(placa);
    }

    @GetMapping(ENDPOINT)
    public List<Veiculo> listVeiculos(){
        return _veiculoService.listAll();
    }

    @PutMapping(ENDPOINT + "/{placa}")
    public void updateVeiculo(@PathVariable("placa") String placa, @Valid @RequestBody Veiculo veiculo){
        _veiculoService.update(placa, veiculo);
    }

    @DeleteMapping(ENDPOINT + "/{placa}")
    public void deleteVeiculo(@PathVariable("placa") String placa){
        _veiculoService.delete(placa);
    }
}
