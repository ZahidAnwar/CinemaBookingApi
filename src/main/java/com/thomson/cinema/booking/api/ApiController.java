package com.thomson.cinema.booking.api;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import com.thomson.cinema.booking.api.dao.UserDao;
import com.thomson.cinema.booking.api.interfaces.Authenticate;

/*
 * Base controller to authenticate API users
 * 
 */

public class ApiController implements Authenticate {

	protected final Logger logger = Logger.getLogger(ApiController.class);
	private final String UNAUTHORIZED_MESSAGE = "{message: \"Authentication Failed!\"}";
	//private final String AUTHENTICATED_MESSAGE = "{message: \"Authenticated!\"}";
	public static final String INTERNAL_SERVER_ERROR = "{message: \"Internal Server Error!\"}";
	protected final String NOT_IMPLEMENTED_YET = "{message: \"Not Implemented Yet.!\"}";
	protected final String NOT_SUPPORTED_YET = "{message: \"Not Supported Yet.!\"}";
	protected final String ROW_ADDED = "{message: \"Row Added Successfully.!\"}";
	protected final String ROW_UPDATED = "{message: \"Row Updated Successfully.!\"}";
	protected final String ROW_DELETED = "{message: \"Row Deleted Successfully.!\"}";
	protected final String OPERATION_FAILED = "{message: \"Failed to add/modify/delete record, check spelling for the requested params or missing params.!\"}";

	@Autowired
	UserDao userDao;



	public boolean authenticate(String token) {

		if (!StringUtils.isEmpty(token)) {

			return userDao.authenticateApiUser(token);

		}

		return false;
	}

	public ResponseEntity<String> unAuthorised(String token, String ipAddress) {

		if (!StringUtils.isEmpty(token)) {
			userDao.saveUnauthorisedUser(token, ipAddress);
			logger.debug("Unauthorized user. token: " + token + " IP address: " + ipAddress);
		}

		return new ResponseEntity<String>(UNAUTHORIZED_MESSAGE, HttpStatus.UNAUTHORIZED);
	}
	

}
