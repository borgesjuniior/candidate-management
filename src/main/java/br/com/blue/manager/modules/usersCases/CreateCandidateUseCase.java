package br.com.blue.manager.modules.usersCases;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blue.manager.exceptions.UserFoundException;
import br.com.blue.manager.modules.candidate.CandidateEntity;
import br.com.blue.manager.modules.candidate.CandidateRepository;

@Service
public class CreateCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  public CandidateEntity execute(CandidateEntity candidateEntity) {
    Optional<CandidateEntity> candidate = this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(),
        candidateEntity.getEmail());

    candidate.ifPresent(c -> {
      throw new UserFoundException();
    });

    return this.candidateRepository.save(candidateEntity);
  }

}
