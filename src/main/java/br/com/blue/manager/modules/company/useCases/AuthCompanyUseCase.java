package br.com.blue.manager.modules.company.useCases;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.blue.manager.modules.company.dto.AuthCompanyDTO;
import br.com.blue.manager.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

  @Value("${security.token.secret}")
  private String secretKey;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
    var company = this.companyRepository.findByUsername(authCompanyDTO.username()).orElseThrow(() -> {
      throw new UsernameNotFoundException("Username or password is incorrect");
    });

    var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.password(), company.getPassword());

    if (!passwordMatches) {
      throw new AuthenticationException();
    }

    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    var token = JWT.create().withIssuer("candidate-manager")
        .withSubject(company.getId().toString())
        .sign(algorithm);

    return token;
  }
}
