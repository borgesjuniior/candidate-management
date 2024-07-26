package br.com.blue.manager.modules.company.useCases;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.blue.manager.modules.company.dto.AuthCompanyDTO;
import br.com.blue.manager.modules.company.repositories.CompanyRepository;

public class AuthCompanyUseCase {

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public void execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
    var company = this.companyRepository.findByUsername(authCompanyDTO.username()).orElseThrow(() -> {
      throw new UsernameNotFoundException("Company not found");
    });

    var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.password(), company.getPassword());

    if (!passwordMatches) {
      throw new AuthenticationException();
    }

  }
}
