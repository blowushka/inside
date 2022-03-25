package com.inside.service.security;

import com.inside.config.TestConfig;
import com.inside.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.DatatypeConverter;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(TestConfig.class)
class JWTTokenProviderTest {

    @Autowired
    private UserDTO userDTO;

    private static final String SECRET = "jsdueHsadpaewlwl21lwW";
    private static final String TOKEN = "Bearer_eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpbGlhIHByb2tvZmV2In0.KTnS5aYU6PsCFdZ9Q5zstI8vQd2oSLwowJaY-FFNt65_RKix0yo7np7aBag761pwul5d7r0AZgIx-MB9Yfz5GA";
    private static final String TEST_NAME = "ilia prokofev";

    @Test
    void generateToken() {

        userDTO.setName(TEST_NAME);

        Claims claims = Jwts.claims().setSubject(userDTO.getName());

        JWTEntity entity = new JWTEntity(Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact());

        Assertions.assertEquals(TOKEN.replace("Bearer_", ""), entity.token());
    }

    @Test
    void verifyToken() {

        userDTO.setName(TEST_NAME);

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                .parseClaimsJws(TOKEN.replace("Bearer_", ""))
                .getBody();

        Assertions.assertEquals(userDTO.getName(), claims.getSubject());
    }
}