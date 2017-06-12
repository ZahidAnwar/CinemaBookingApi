package com.thomson.cinema.booking.api.interfaces;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface ApiHandler {
	
	public ResponseEntity<String> list(HttpServletRequest request, String token);
	
	public ResponseEntity<String> delete(HttpServletRequest request, String token, int id);

}
