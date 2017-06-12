package com.thomson.cinema.booking.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thomson.cinema.booking.api.dao.FilmDao;
import com.thomson.cinema.booking.api.interfaces.ApiHandler;

@Controller
@RequestMapping("/movie/*")
public class MovieController extends ApiController implements ApiHandler {
	
	@Autowired
	FilmDao filmDao;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> list(HttpServletRequest request, String token) {
		
		if (authenticate(token)) {
			logger.debug("Authorized user. Token=" + token);
			
			String movies = filmDao.getMovies();
			logger.debug("movies: " + movies);
			return new ResponseEntity<String>(movies, HttpStatus.OK);
		}

		return unAuthorised(token, request.getRemoteAddr());
	}



	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> delete(HttpServletRequest request, String token, int fimlId) {
		
		if (!authenticate(token)) {
			return unAuthorised(token, request.getRemoteAddr());
		}
		
		return new ResponseEntity<String>(NOT_IMPLEMENTED_YET, HttpStatus.NOT_IMPLEMENTED);
	}

}
