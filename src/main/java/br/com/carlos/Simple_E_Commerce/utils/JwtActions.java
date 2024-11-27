package br.com.carlos.Simple_E_Commerce.utils;

import br.com.carlos.Simple_E_Commerce.config.JwtConfig;
import br.com.carlos.Simple_E_Commerce.dto.LoginDto;
import br.com.carlos.Simple_E_Commerce.dto.LoginResponseDto;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtActions {

    @Autowired
    private JwtConfig jwtConfig;

    public LoginResponseDto jwtCreate(LoginDto dto) {

        var now = Instant.now();
        var expiresIn = 7 * 24 * 60 * 60L;


        var claims = JwtClaimsSet.builder()
                .issuer("ecommerce_login")
                .subject(dto.email())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", dto.role())
                .build();

        var jwtValue = jwtConfig.jwtEncoder().encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponseDto(jwtValue, expiresIn);
    }
}
