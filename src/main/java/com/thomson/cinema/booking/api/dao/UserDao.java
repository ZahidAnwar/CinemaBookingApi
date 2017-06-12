package com.thomson.cinema.booking.api.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thomson.cinema.booking.api.entity.ApiUser;

//@Service("dao")
@Component
public class UserDao {
	
	/*
	 * Authenticate api user with token
	 * An unique java.util.UUID.randomUUID();
	 * 
	 * 
	 */
	
	final Logger logger = Logger.getLogger(UserDao.class);
	
	@Autowired
	public SessionFactory sessionFactory;
	
	@Transactional(readOnly = true)
	public boolean authenticateApiUser(String token) {
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
			ApiUser user = (ApiUser) session.createQuery("from ApiUser where token = :token", ApiUser.class)
						.setParameter("token", token)
						.getSingleResult();
			
			return user != null;
			
		} catch (Exception e) {
		  logger.equals(e);
		}
		
		return false;
	}

	
	/*
	 * Add code in future to save unauthorized user's record with their IP address
	 */
	@Transactional(readOnly = false)
	public void saveUnauthorisedUser(String token, String ipAddress) {
		//Not supported yet
	}
}
