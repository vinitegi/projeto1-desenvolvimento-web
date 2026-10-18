package com.projeto.web.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Servicos: dados que vem da API (nao e salvo no banco do Web).
 */
public class Servico {

    private Integer id;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotNull(message = "Data de cadastro é obrigatória")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dtCadastro = LocalDate.now();

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDate getDtCadastro() { return dtCadastro; }
    public void setDtCadastro(LocalDate dtCadastro) { this.dtCadastro = dtCadastro; }
}
