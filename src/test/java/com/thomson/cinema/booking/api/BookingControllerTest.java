package com.thomson.cinema.booking.api;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.thomson.cinema.booking.api.dao.UserDao;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "classpath:**/applicationContext-test.xml" })
public class BookingControllerTest {

		@InjectMocks
		MovieController movieController;
		
		@Mock
		HttpServletRequest request;
		
	    @InjectMocks
	    UserDao ud;
	    
	    @Mock
	    SessionFactory sf;


		@Test
		public void testBookingList() throws Exception {
		
		}
		
		@Test
		public void testBookingAdd() throws Exception {
		
		}
		
		@Test
		public void testCancelBooking() throws Exception {
		
		}
}
