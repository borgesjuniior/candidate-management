package br.com.blue.manager.modules.company.useCases;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.blue.manager.modules.company.entities.CompanyEntity;
import br.com.blue.manager.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public CompanyEntity execute(CompanyEntity companyEntity) {
    Optional<CompanyEntity> company = companyRepository.findByUsernameOrEmail(companyEntity.getUsername(),
        companyEntity.getEmail());

    company.ifPresent(c -> {
      throw new RuntimeException("Company already exists");
    });

    var passwordEncoded = passwordEncoder.encode(companyEntity.getPassword());
    companyEntity.setPassword(passwordEncoded);

    return this.companyRepository.save(companyEntity);
  }
}
