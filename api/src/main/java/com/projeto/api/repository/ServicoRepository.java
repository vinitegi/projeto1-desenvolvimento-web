package com.projeto.api.repository;

import com.projeto.api.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Veiculo, Integer> {
}
