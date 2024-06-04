package br.com.fiap.BrightOcean.repository;

import br.com.fiap.BrightOcean.model.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Long> {
}
