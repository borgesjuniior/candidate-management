package br.com.blue.manager.modules.company;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blue.manager.modules.company.entities.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
  public Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);
}
