package br.com.blue.manager.modules.candidate.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blue.manager.modules.candidate.CandidateEntity;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

  @GetMapping("/")
  public ResponseEntity<Object> create(@RequestBody CandidateEntity candidateEntity) {
    System.out.println(candidateEntity.getEmail());
    return ResponseEntity.ok().body(candidateEntity);
  }
}
