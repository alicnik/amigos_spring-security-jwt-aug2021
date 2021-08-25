package com.getarrays.userservice.utilities;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.getarrays.userservice.models.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JWTUtilities {

    private static Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

    public static String createToken(
            HttpServletRequest request,
            String username,
            int expiryTime,
            List<String> roles
    ) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + expiryTime))
                .withIssuer(request.getRequestURI())
                .withClaim(
                        "roles",
                        roles
                )
                .sign(algorithm);
    }

    public static DecodedJWT getDecodedJWT(String authorisationHeader) {
        String token = authorisationHeader.substring("Bearer ".length());
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        return jwtVerifier.verify(token);
    }
}
