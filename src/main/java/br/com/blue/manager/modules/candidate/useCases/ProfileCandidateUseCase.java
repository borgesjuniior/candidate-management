package br.com.blue.manager.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.blue.manager.modules.candidate.CandidateRepository;
import br.com.blue.manager.modules.candidate.dto.ProfileCandidateResponseDTO;

public class ProfileCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  public ProfileCandidateResponseDTO execute(UUID candidateId) {
    var candidate = this.candidateRepository.findById(candidateId).orElseThrow(() -> {
      throw new UsernameNotFoundException("User not found");
    });

    return new ProfileCandidateResponseDTO(
        candidate.getId(),
        candidate.getName(),
        candidate.getEmail(),
        candidate.getEmail(),
        candidate.getDescription());
  }
}
