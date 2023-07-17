package com.chatconnect.backend.security.jwt;

import java.security.Key;
import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.chatconnect.backend.repository.JwtRepository;
import com.chatconnect.backend.security.services.UserDetailsGet;

import ch.qos.logback.classic.Logger;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtils {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${chatconnect.app.jwtSecret}")
	private String jwtSecret;
	
	@Value("${chatconnect.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private JwtRepository jwtRepository;
	
	public String generateJwtToken(Authentication authentication) {
		UserDetailsGet userPrincipal = (UserDetailsGet) authentication.getPrincipal();
		return Jwts.builder().setSubject(userPrincipal.getUsername()).setIssuedAt(new Date()).setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(key(), SignatureAlgorithm.HS256).compact();
	}
	
	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}
	
	public String getUsernameFromJwtToken(String token) {
		return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
	}

	public long getUseridFromJwtToken(String token) {
		return Long.parseLong(Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject());
	}
	
	public boolean validateJwtToken(String authToken) {
		try {
			if (jwtRepository.existsByJwt(authToken)) {
				return false;
			}
			Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
			return true;
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch(ExpiredJwtException e) {
			logger.error("Expired JWT token: {}", e.getMessage());
		} catch(UnsupportedJwtException e) {
			logger.error("Unsupported JWT token: {}", e.getMessage());
		} catch(IllegalArgumentException e) {
			logger.error("JWT claims that string is empty: {}", e.getMessage());
		}
		return false;
	}

	public String getJwtFromRequest() {
		String headAuth = request.getHeader("Authorization");
		if (StringUtils.hasText(headAuth) && headAuth.startsWith("Bearer ")) {
			return headAuth.substring(7, headAuth.length());
		}
		return null;
	}
}
