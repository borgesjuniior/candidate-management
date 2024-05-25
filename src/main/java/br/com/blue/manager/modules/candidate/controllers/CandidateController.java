package br.com.blue.manager.modules.candidate.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blue.manager.exceptions.UserFoundException;
import br.com.blue.manager.modules.candidate.CandidateEntity;
import br.com.blue.manager.modules.candidate.CandidateRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

  @Autowired
  private CandidateRepository candidateRepository;

  @GetMapping
  public CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity) {
    Optional<CandidateEntity> candidate = this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(),
        candidateEntity.getEmail());

    candidate.ifPresent(c -> {
      throw new UserFoundException();
    });

    return this.candidateRepository.save(candidateEntity);
  }
}
