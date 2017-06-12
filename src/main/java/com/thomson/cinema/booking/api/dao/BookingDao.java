package com.thomson.cinema.booking.api.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.google.gson.Gson;
import com.thomson.cinema.booking.api.entity.Booking;

@Component
public class BookingDao {
	
	final Logger logger = Logger.getLogger(BookingDao.class);
	
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public boolean addBooking(Booking b) {
				
		if(b.getFilmId() > 0 
				&& b.getUserId() > 0 
				&& b.getReserveDate() != null) {
		
			b.setBookingTime(new Timestamp(new Date().getTime()));
			Session session = sessionFactory.getCurrentSession();
			session.save(b);
			return true;
		}
		
		return false;
	}

	@Transactional
	public boolean cancelSeatWithBookingId(int bookingId) {
		
		if(bookingId > 0) {
			
			Booking b = new Booking();
			b.setBookingId(bookingId);
			
			Session session = sessionFactory.getCurrentSession();
			session.delete(b);
			return true;
			
		}
		
		return false;
	}

	@Transactional
	public String getBookedCustomers() {
		
		Session session = sessionFactory.getCurrentSession();

		try {
			
			NativeQuery<Booking> query = session.createNativeQuery("select * from Booking where reserveDate >= (NOW() + INTERVAL 1 DAY)", Booking.class);

			List<Booking> movies = query.getResultList();

			return new Gson().toJson(movies);
			
		} catch (Exception e) {
			logger.equals(e);
		}

		return null;
	}

}
