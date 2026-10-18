package com.projeto.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Veiculos: dados que vem da API (nao e salvo no banco do Web).
 */
public class Veiculo {

    @NotBlank(message = "Placa é obrigatória")
    private String placa;

    @NotBlank(message = "Modelo é obrigatório")
    private String modelo;

    @NotBlank(message = "Marca é obrigatória")
    private String marca;

    @NotNull(message = "Ano do modelo é obrigatório")
    @Min(value = 1900, message = "Ano do modelo inválido")
    @Max(value = 2100, message = "Ano do modelo inválido")
    private Integer anoModelo;

    @NotNull(message = "Ano de fabricação é obrigatório")
    @Min(value = 1900, message = "Ano de fabricação inválido")
    @Max(value = 2100, message = "Ano de fabricação inválido")
    private Integer anoFabricacao;

    @NotBlank(message = "Cor é obrigatória")
    private String cor;

    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser maior que zero")
    private Double preco;

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public Integer getAnoModelo() { return anoModelo; }
    public void setAnoModelo(Integer anoModelo) { this.anoModelo = anoModelo; }

    public Integer getAnoFabricacao() { return anoFabricacao; }
    public void setAnoFabricacao(Integer anoFabricacao) { this.anoFabricacao = anoFabricacao; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
}
