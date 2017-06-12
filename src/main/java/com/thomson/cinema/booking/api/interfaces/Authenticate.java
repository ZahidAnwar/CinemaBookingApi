package com.thomson.cinema.booking.api.interfaces;

import org.springframework.http.ResponseEntity;

public interface Authenticate {
	
	public boolean authenticate(String token);
	
	public ResponseEntity<String> unAuthorised(String token, String ipAddress);

}
