package com.thomson.cinema.booking.api;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.thomson.cinema.booking.api.dao.UserDao;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.Assert.*;


public class ApiControllerTest {

	protected final Logger logger = Logger.getLogger(ApiControllerTest.class);
	protected final String TOKEN = "6d17f079-2186-49b9-97f3-7f45125b4486";
	protected MockMvc mockMvc;
	
    @InjectMocks
	private ApiController apiController;
    
    @InjectMocks
    UserDao ud;
    
    @Mock
    SessionFactory sf;
	
	
    @Before
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
    	mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
    }
	

	
	
	
	@Test
	public void testAuthenticate() throws Exception{

		assertNotNull(apiController);
		assertFalse(apiController.authenticate(""));
		assertFalse(apiController.authenticate(null));		
		assertNotNull(ud);
		assertNotNull(sf);
		apiController.userDao = ud;
		apiController.userDao.sessionFactory = sf;
		assertFalse(apiController.authenticate("123"));
		assertFalse(apiController.authenticate("random1234679735Test"));
	}
}
