package com.projeto.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Funcionario (diagrama de classes): o CPF e o identificador.
 */
@Entity
public class Funcionario {

    @Id
    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotNull(message = "Salário é obrigatório")
    @Positive(message = "Salário deve ser maior que zero")
    private Double salario;

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Double getSalario() { return salario; }
    public void setSalario(Double salario) { this.salario = salario; }
}
