package br.com.blue.manager.modules.candidate.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blue.manager.modules.candidate.CandidateEntity;
import br.com.blue.manager.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.blue.manager.modules.candidate.useCases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

  @Autowired
  private CreateCandidateUseCase candidateUseCase;

  @Autowired
  private ProfileCandidateUseCase profileCandidateUseCase;

  @PostMapping
  public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
    try {
      var result = this.candidateUseCase.execute(candidateEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping
  public ResponseEntity<Object> getMethodName(HttpServletRequest httpServletRequest) {
    var candidateId = httpServletRequest.getAttribute("candidate_id");

    try {
      var profile = this.profileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));
      return ResponseEntity.ok().body(profile);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
