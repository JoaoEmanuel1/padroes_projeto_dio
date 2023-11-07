package me.dio.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.domain.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, String> {

}
