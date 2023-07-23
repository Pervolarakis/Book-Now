package com.example.Book.now.service;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;

@Service
@RequiredArgsConstructor
public class MailJwtService {

//    @Value("{mail.mailEncryptionKey}")
    private static final String jwtEmailKey = "217A25432A462D4A614E645267556B58703272357538782F413F4428472B4B62";

    public String generateEmailVerificationToken(String userMail){
        JwtClaimsSet claims = JwtClaimsSet
                .builder()
                .subject(userMail)
                .build();
        var encoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS512).build(), claims);
        return this.emailJWTEncoder().encode(encoderParameters).getTokenValue();
    }

    public String getEmailJwtSubject(String token){
        return this.emailJWTDecoder().decode(token).getSubject();
    }

    private JwtEncoder emailJWTEncoder(){
        return new NimbusJwtEncoder(new ImmutableSecret<>(jwtEmailKey.getBytes()));
    }

    public JwtDecoder emailJWTDecoder() {
        byte[] bytes = jwtEmailKey.getBytes();
        SecretKeySpec originalKey = new SecretKeySpec(bytes, 0, bytes.length,"RSA");
        return NimbusJwtDecoder.withSecretKey(originalKey).macAlgorithm(MacAlgorithm.HS512).build();
    }

}
