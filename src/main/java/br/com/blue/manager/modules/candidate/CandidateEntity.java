package br.com.blue.manager.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {
  private UUID id;
  private String name;

  @Pattern(regexp = "^(?!\\s*$).+", message = "O campo username não deve contar espaços")
  private String username;

  @Email(message = "O campo email deve conter um email válido")
  private String email;

  @Length(min = 8, max = 100)
  private String password;

  private String description;
  private String curriculum;
}
