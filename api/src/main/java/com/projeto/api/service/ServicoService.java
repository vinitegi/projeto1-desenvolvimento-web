package com.projeto.api.service;

import com.projeto.api.exception.NotFoundException;
import com.projeto.api.model.Servico;
import com.projeto.api.repository.ServicoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository _servicoRepository;

    public void create(Servico servico){
        _servicoRepository.save(servico);
    }

    public Servico findOne(Integer idServico){
        return _servicoRepository.findById(idServico)
                .orElseThrow(() ->
                        new IllegalArgumentException("Serviço não encontrado. Id: " + idServico));
    }

    public List<Servico> listAll(){
        return _servicoRepository.findAll();
    }

    public void update(Integer idServico, Servico newServico){
        Servico servico = findOne(idServico);

        servico.setDescricao(newServico.getDescricao());
        servico.setDtCadastro(newServico.getDtCadastro());

        _servicoRepository.save(servico);
    }

    public void delete(Integer idServico){
        Servico servico = findOne(idServico);

        _servicoRepository.deleteById(idServico);
    }
}
