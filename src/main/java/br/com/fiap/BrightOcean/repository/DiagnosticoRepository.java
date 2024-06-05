package br.com.fiap.BrightOcean.repository;

import br.com.fiap.BrightOcean.model.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {
}
