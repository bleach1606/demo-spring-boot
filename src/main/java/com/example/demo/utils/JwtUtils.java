package com.example.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUtils {

  public static String secretKey() {
    return EnvUtils.getProperty("application.jwt.confirmation-secret");
  }

  public static String build(String subject, String secretKey, int expirationSeconds, Map<String, Object> claims) {
    Date now = new Date();
    return Jwts.builder()
        .setSubject(subject)
        .setIssuedAt(now)
        .claim(UUID.randomUUID().toString(), UUID.randomUUID())
        .addClaims(claims)
        .setExpiration(new Date(now.getTime() + expirationSeconds * 1000L))
        .signWith(SignatureAlgorithm.HS512, secretKey)
        .compact();
  }

  public static String build(String subject, String secretKey, int expirationSeconds) {
    return build(subject, secretKey, expirationSeconds, Collections.emptyMap());
  }

  public static String build(String subject, int expirationSeconds, Map<String, Object> claims) {
    return build(subject, secretKey(), expirationSeconds, claims);
  }

  public static String build(String subject, int expirationSeconds) {
    return build(subject, secretKey(), expirationSeconds);
  }

  public static Claims parse(String jwtString, String secretKey) {
    return Jwts.parser().setSigningKey(secretKey)
        .parseClaimsJws(jwtString)
        .getBody();
  }

  public static Claims parse(String jwtString) {
    return parse(jwtString, secretKey());
  }

}
