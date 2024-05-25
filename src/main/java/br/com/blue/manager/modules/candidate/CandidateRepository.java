package br.com.blue.manager.modules.candidate;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
  public Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);
}