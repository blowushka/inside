package com.inside.service.security;

import com.inside.dto.UserDTO;
import com.inside.exception.EntityNotFoundException;
import com.inside.exception.JwtValidatingException;
import com.inside.model.User;
import com.inside.service.UserService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.xml.bind.DatatypeConverter;

@Service
@RequiredArgsConstructor
public class JWTTokenProvider {

    private static final String SECRET = "jsdueHsadpaewlwl21lwW";
    private final UserService userService;
    private final PasswordEncoder encoder;


    public JWTEntity generateToken(UserDTO dto) {

        boolean isMatched = verifyPassword(dto);

        if (!isMatched) {
            throw new JwtException("Wrong password");
        }

        Claims claims = Jwts.claims().setSubject(dto.getName());

        return new JWTEntity(Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact());
    }

    private boolean verifyPassword(UserDTO dto) {
        final var entity = userService.findByName(dto.getName())
                .orElseThrow(() -> new EntityNotFoundException(dto.getName(), User.class));

        return encoder.matches(dto.getPassword(), entity.getPassword());
    }

    public void verifyToken(String userName, String bearerToken) {

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                .parseClaimsJws(bearerToken.replace("Bearer_", ""))
                .getBody();
        if (!claims.getSubject().equals(userName)){
            throw new JwtValidatingException("This is not your token!");
        }
    }
}
