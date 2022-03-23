package com.inside.service.security;


import com.inside.dto.UserDTO;
import com.inside.exception.EntityNotFoundException;
import com.inside.model.User;
import com.inside.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JWTTokenProvider {

    private String secret = "someSecretLetters";
    private final UserService userService;
    private final PasswordEncoder encoder;

    public UserDTO parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            UserDTO dto = new UserDTO();
            dto.setName(body.getSubject());

            return dto;
        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

    public JWTEntity generateToken(UserDTO dto) {

        boolean isMatched = verifyPassword(dto);

        if (!isMatched){
            throw new JwtException("Wrong password");
        }

        Claims claims = Jwts.claims().setSubject(dto.getName());

        return new JWTEntity(Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact());
    }

    private boolean verifyPassword(UserDTO dto) {
        final var entity = userService.findByName(dto.getName())
                .orElseThrow(() -> new EntityNotFoundException(dto.getName(), User.class));

        return encoder.matches(dto.getPassword(), entity.getPassword());
    }
}
