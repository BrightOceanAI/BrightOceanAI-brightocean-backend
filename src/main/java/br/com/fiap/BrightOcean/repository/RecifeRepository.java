package br.com.fiap.BrightOcean.repository;

import br.com.fiap.BrightOcean.model.Recife;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecifeRepository extends JpaRepository<Recife, Long> {
}
