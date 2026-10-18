package com.projeto.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.projeto.web.dto.Servico;
import com.projeto.web.dto.Veiculo;

/**
 * Faz as chamadas HTTP para o projeto API (Veiculos e Servicos).
 * Se a API estiver fora do ar, os metodos lancam RestClientException.
 */
@Service
public class ApiClient {

    private final RestClient rest;

    public ApiClient(RestClient.Builder builder, @Value("${api.url}") String apiUrl) {
        this.rest = builder.baseUrl(apiUrl).build();
    }

    // ---------- Veiculos ----------

    public List<Veiculo> listarVeiculos() {
        return rest.get().uri("/api/veiculos").retrieve()
                .body(new ParameterizedTypeReference<List<Veiculo>>() { });
    }

    public Veiculo buscarVeiculo(String placa) {
        return rest.get().uri("/api/veiculos/{placa}", placa).retrieve().body(Veiculo.class);
    }

    public void criarVeiculo(Veiculo veiculo) {
        rest.post().uri("/api/veiculos").body(veiculo).retrieve().toBodilessEntity();
    }

    public void atualizarVeiculo(String placa, Veiculo veiculo) {
        rest.put().uri("/api/veiculos/{placa}", placa).body(veiculo).retrieve().toBodilessEntity();
    }

    public void excluirVeiculo(String placa) {
        rest.delete().uri("/api/veiculos/{placa}", placa).retrieve().toBodilessEntity();
    }

    // ---------- Servicos ----------

    public List<Servico> listarServicos() {
        return rest.get().uri("/api/servicos").retrieve()
                .body(new ParameterizedTypeReference<List<Servico>>() { });
    }

    public Servico buscarServico(Integer id) {
        return rest.get().uri("/api/servicos/{id}", id).retrieve().body(Servico.class);
    }

    public void criarServico(Servico servico) {
        rest.post().uri("/api/servicos").body(servico).retrieve().toBodilessEntity();
    }

    public void atualizarServico(Integer id, Servico servico) {
        rest.put().uri("/api/servicos/{id}", id).body(servico).retrieve().toBodilessEntity();
    }

    public void excluirServico(Integer id) {
        rest.delete().uri("/api/servicos/{id}", id).retrieve().toBodilessEntity();
    }
}
