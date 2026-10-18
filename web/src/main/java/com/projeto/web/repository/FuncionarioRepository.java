package com.projeto.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.web.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, String> {
}
