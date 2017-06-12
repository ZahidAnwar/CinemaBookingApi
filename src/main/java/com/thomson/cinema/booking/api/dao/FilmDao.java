package com.thomson.cinema.booking.api.dao;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.google.gson.Gson;
import com.thomson.cinema.booking.api.entity.Film;

@Component
public class FilmDao {
	
	/*
	 * Handle Movie related queries
	 * 
	 * 
	 */
	
	final Logger logger = Logger.getLogger(FilmDao.class);
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional(readOnly = true)
	public String getMovies() {
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
			String sql = "select f.filmId, f.title, f.description, f.genre, f.rated, f.director, f.stars, f.showTime, s.screenName, "
					+ " s.totalSeats, count(b.filmId) as totalBooked from Film f "
					+ " left join Booking b on f.filmId = b.filmId "
					+ " inner join Screen s on f.screenId = s.screenId group by f.filmId;";
			
			NativeQuery<Film> query = session.createNativeQuery(sql, Film.class);
			
			List<Film> movies = query.getResultList();
			
			
			return new Gson().toJson(movies);
			
		} catch (Exception e) {
		  logger.equals(e);
		}
		
		return null;
	}

}
