package com.projeto.api.controller;

import com.projeto.api.model.Servico;
import com.projeto.api.service.ServicoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServiceController {
    private static final String ENDPOINT = "api/servicos";

    @Autowired
    private ServicoService _servicoService;

    @PostMapping(ENDPOINT)
    public void createServico(Servico servico){
        _servicoService.create(servico);
    }

    @GetMapping(ENDPOINT + "/{id}")
    public Servico findServico(@PathVariable("id") Integer id){
        return _servicoService.findOne(id);
    }

    @GetMapping(ENDPOINT)
    public List<Servico> listServicos(){
        return _servicoService.listAll();
    }

    @PutMapping(ENDPOINT + "/{id}")
    public void updateServico(@PathVariable("id") Integer id, Servico servico){
        _servicoService.update(id, servico);
    }

    @DeleteMapping(ENDPOINT + "/{id}")
    public void deleteServico(@PathVariable("id") Integer id){
        _servicoService.delete(id);
    }
}
