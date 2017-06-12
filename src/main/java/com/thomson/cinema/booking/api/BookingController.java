package com.thomson.cinema.booking.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thomson.cinema.booking.api.dao.BookingDao;
import com.thomson.cinema.booking.api.entity.Booking;
import com.thomson.cinema.booking.api.interfaces.ApiHandler;

@Controller
@RequestMapping("/booking/*")
public class BookingController extends ApiController implements ApiHandler {
	
	@Autowired
	BookingDao bookingDao;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> list(HttpServletRequest request, String token) {
		
		if (authenticate(token)) {
			logger.debug("Authorized user. Token=" + token);
			
			String customers = bookingDao.getBookedCustomers();
			logger.debug("Customer who reserved seat for future date(the reserved date is greater than 24 hours from now): " + customers);
			return new ResponseEntity<String>(customers, HttpStatus.OK);
		}

		return unAuthorised(token, request.getRemoteAddr());
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> add(HttpServletRequest request, String token, @ModelAttribute Booking booking) {
		
		if (authenticate(token)) {
			
			
			if(bookingDao.addBooking(booking)) {
				return new ResponseEntity<String>(ROW_ADDED, HttpStatus.OK);
			}
			
			
			return new ResponseEntity<String>(OPERATION_FAILED, HttpStatus.OK);
			
			
		}
		
		return unAuthorised(token, request.getRemoteAddr());
	}
	

	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> delete(HttpServletRequest request, String token, int bookingId) {
		if (authenticate(token) && bookingDao.cancelSeatWithBookingId(bookingId)) {
			
			return new ResponseEntity<String>(ROW_DELETED, HttpStatus.OK);
		}
		
		return unAuthorised(token, request.getRemoteAddr());
	}


}
