package com.projeto.api.service;

import com.projeto.api.exception.NotFoundException;
import com.projeto.api.model.Veiculo;
import com.projeto.api.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository _veiculoRepository;

    public void create(Veiculo veiculo){
        _veiculoRepository.save(veiculo);
    }

    public Veiculo findOne(String placa){
        return _veiculoRepository.findById(placa)
                .orElseThrow(() ->
                        new NotFoundException("Veículo não encontrado. Placa: " + placa));
    }

    public List<Veiculo> listAll(){
        return _veiculoRepository.findAll();
    }

    public void update(String placa, Veiculo newVeiculo){
        Veiculo veiculo = findOne(placa);

        veiculo.setModelo(newVeiculo.getModelo());
        veiculo.setMarca(newVeiculo.getMarca());
        veiculo.setAnoModelo(newVeiculo.getAnoModelo());
        veiculo.setAnoFabricacao(newVeiculo.getAnoFabricacao());
        veiculo.setCor(newVeiculo.getCor());
        veiculo.setPreco(newVeiculo.getPreco());

        _veiculoRepository.save(veiculo);
    }

    public void delete(String placa){
        findOne(placa);

        _veiculoRepository.deleteById(placa);
    }
}
