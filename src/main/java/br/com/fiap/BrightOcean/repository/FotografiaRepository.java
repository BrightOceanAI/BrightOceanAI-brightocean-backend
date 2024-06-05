package br.com.fiap.BrightOcean.repository;

import br.com.fiap.BrightOcean.model.Fotografia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotografiaRepository extends JpaRepository<Fotografia, Long> {
}
