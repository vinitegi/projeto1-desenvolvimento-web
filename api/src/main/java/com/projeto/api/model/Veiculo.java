package com.projeto.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Veiculo {

    @Id
    @NotBlank(message = "Informe a placa do veículo.")
    private String placa;

    @NotBlank(message = "Informe o modelo do veículo.")
    @Size(max = 50, message = "O modelo deve ter no máximo 50 caracteres.")
    private String modelo;

    @NotBlank(message = "Informe a marca do veículo.")
    @Size(max = 20, message = "A marca deve ter no máximo 20 caracteres.")
    private String marca;

    @NotNull(message = "Informe o ano do modelo.")
    private Integer anoModelo;

    @NotNull(message = "Informe o ano de fabricação do veículo.")
    private Integer anoFabricacao;

    @NotBlank(message = "Informe a cor do veículo.")
    @Size(max = 30, message = "A cor deve ter no máximo 30 caracteres.")
    private String cor;

    @NotNull(message = "Informe o preço do veículo.")
    @Positive(message = "O preço deve ser maior que zero.")
    private Double preco;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(Integer anoModelo) {
        this.anoModelo = anoModelo;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}