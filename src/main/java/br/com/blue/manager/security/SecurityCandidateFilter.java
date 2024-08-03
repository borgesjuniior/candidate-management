package br.com.blue.manager.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.blue.manager.providers.JWTCandidateProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityCandidateFilter extends OncePerRequestFilter {

  @Autowired
  private JWTCandidateProvider JWTProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    SecurityContextHolder.getContext().setAuthentication(null);

    if (request.getRequestURI().startsWith("/candidate")) {
      String bearerToken = request.getHeader("Authorization");

      if (bearerToken != null) {
        var subjetToken = this.JWTProvider.validateToken(bearerToken);

        if (subjetToken.isEmpty()) {
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          return;
        }

        request.setAttribute("candidate_id", subjetToken);

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
            subjetToken, null,
            Collections.emptyList());

        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    }

    filterChain.doFilter(request, response);
  }

}