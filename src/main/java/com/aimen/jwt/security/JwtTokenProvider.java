package com.aimen.jwt.security;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	private static final  String SECRET = "yourr256bbitssecretkkeyhheremmakeiitaatlleast332bbytesllong";
	private static final  long  EXPIRATION = 3600000;

	public String generateToken (Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Date now = new Date();
		Date expiryDate = new Date(now.getTime()+EXPIRATION);

		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.claim("roles", userDetails.getAuthorities().stream()
						.map(GrantedAuthority::getAuthority)
						.toList())
				.setIssuedAt(now)
				.setExpiration(expiryDate)
				.signWith(getKey())
				.compact();
	}

	public String getUsernameFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateToken (String token) {
		try {

			Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
			return true;

		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	private Key getKey() {
		byte[] keyBytes = Base64.getDecoder().decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
