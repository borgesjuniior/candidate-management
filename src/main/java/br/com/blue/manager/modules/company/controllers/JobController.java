package br.com.blue.manager.modules.company.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blue.manager.modules.company.dto.CreateJobDTO;
import br.com.blue.manager.modules.company.entities.JobEntity;
import br.com.blue.manager.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {

  @Autowired
  private CreateJobUseCase createJobUseCase;

  @PostMapping
  private ResponseEntity<Object> create(@Valid @RequestBody CreateJobDTO createjoDto, HttpServletRequest request) {
    var companyId = request.getAttribute("company_id");

    JobEntity jobEntity = JobEntity.builder()
        .benefits(createjoDto.benefits())
        .companyId(UUID.fromString(companyId.toString()))
        .description(createjoDto.description())
        .level(createjoDto.level())
        .build();

    try {
      var result = this.createJobUseCase.execute(jobEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
