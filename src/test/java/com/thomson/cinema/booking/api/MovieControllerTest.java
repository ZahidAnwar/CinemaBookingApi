package com.thomson.cinema.booking.api;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thomson.cinema.booking.api.dao.UserDao;
import com.thomson.cinema.booking.api.entity.Film;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;

//@RunWith(SpringRunner.class)
//@ContextConfiguration(locations = { "classpath:**/applicationContext-test.xml" })
public class MovieControllerTest extends ApiControllerTest {
	

	@InjectMocks
	MovieController movieController;
	
	@Mock
	HttpServletRequest request;
	
    @InjectMocks
    UserDao ud;
    
    @Mock
    SessionFactory sf;


	@Test
	public void testMovieList() throws Exception {
		
		assertNotNull(movieController);
		assertNotNull(ud);
		assertNotNull(sf);
		movieController.userDao = ud;
		movieController.userDao.sessionFactory = sf;
		
		LocalTime lt = LocalTime.of(10, 00);

		Film f1 = new Film();
		f1.setFilmId(1);
		f1.setTitle("Despicable Me 3");
		f1.setDescription("Test description");
		f1.setGenre("Animated");
		f1.setRated("TBC");
		f1.setShowTime(Time.valueOf(lt));
		f1.setDirector("Chris Renaud, Pierre Coffin");
		f1.setStars("Jenny Slate, Kristen Wiig, Steve Carell, Miranda Cosgrove");
		f1.setScreenName("Screen-1");
		f1.setTotalSeats(60);
		f1.setTotalBooked(2);
		
		
		lt = LocalTime.of(10, 30);
		Film f2 = new Film();
		f2.setFilmId(2);
		f2.setTitle("The Mummy");
		f2.setDescription("Test description2");
		f2.setGenre("Action");
		f2.setRated("PG-13");
		f2.setShowTime(Time.valueOf(lt));
		f2.setDirector("Alex Kurtzman");
		f2.setStars("Tom Cruise, Sofia Boutella, Annabelle Wallis, Russel Crowe");
		f2.setScreenName("Screen-2");
		f2.setTotalSeats(65);
		f2.setTotalBooked(4);
		
		String json =  movieController.list(request, TOKEN).toString();
		logger.debug(json);
		
		List<Film> films = new Gson().fromJson(json, new TypeToken<List<Film>>(){}.getType());
		
		when(films).thenReturn(Arrays.asList(f1, f2));
		mockMvc.perform(get("/Movies/Get")).andExpect(status().isOk())
				.andExpect(content()
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].filmId", is(1)))
				.andExpect(jsonPath("$[0].title", is("Despicable Me 3")))
				.andExpect(jsonPath("$[0].description", is("Test description")))
				.andExpect(jsonPath("$[0].genre", is("Animated")))
				.andExpect(jsonPath("$[0].rated", is("TBC")))
				.andExpect(jsonPath("$[0].showTime", is("10:00:00 AM")))
				.andExpect(jsonPath("$[0].director", is("Chris Renaud, Pierre Coffin")))
				.andExpect(jsonPath("$[0].stars", is("Jenny Slate, Kristen Wiig, Steve Carell, Miranda Cosgrove")))
				.andExpect(jsonPath("$[0].screenName", is("Screen-1")))
				.andExpect(jsonPath("$[0].totalSeats", is(60)))
				.andExpect(jsonPath("$[0].totalBooked", is(2)))
		
				.andExpect(jsonPath("$[1].filmId", is(1)))
				.andExpect(jsonPath("$[1].title", is("The Mummy")))
				.andExpect(jsonPath("$[1].description", is("Test description2")))
				.andExpect(jsonPath("$[1].genre", is("Action")))
				.andExpect(jsonPath("$[1].rated", is("PG-13")))
				.andExpect(jsonPath("$[1].showTime", is("10:30:00 AM")))
				.andExpect(jsonPath("$[1].director", is("Alex Kurtzman")))
				.andExpect(jsonPath("$[1].stars", is("Tom Cruise, Sofia Boutella, Annabelle Wallis, Russel Crowe")))
				.andExpect(jsonPath("$[1].screenName", is("Screen-2")))
				.andExpect(jsonPath("$[1].totalSeats", is(64)))
				.andExpect(jsonPath("$[1].totalBooked", is(4)));

		verify(movieController, times(1));
		verifyNoMoreInteractions(movieController);
	}

}
