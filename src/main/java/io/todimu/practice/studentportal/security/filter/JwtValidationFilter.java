package io.todimu.practice.studentportal.security.filter;

import io.todimu.practice.studentportal.security.jwt.JwtTokenProvider;
import io.todimu.practice.studentportal.utils.AuthoritiesConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@RequiredArgsConstructor
public class JwtValidationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                                   @NonNull FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = resolveToken(request);
        if (StringUtils.hasText(jwtToken) && this.jwtTokenProvider.validateToken(jwtToken)) {
            Authentication authentication = this.jwtTokenProvider.getAuthentication(jwtToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals("/api/v1/user/authenticate");
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AuthoritiesConstants.AUTHORITIES_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
