package com.thomson.cinema.booking.api.entity;

import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Film {
	@Id
	private int filmId;
	private String title;
	private String description;
	private String genre;
	private String rated;
	private Time showTime;
	private String director;
	private String stars;
	private String screenName;
	private int totalSeats;
	private int totalBooked;
	
	
	public int getFilmId() {
		return filmId;
	}
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getRated() {
		return rated;
	}
	public void setRated(String rated) {
		this.rated = rated;
	}
	public Time getShowTime() {
		return showTime;
	}
	public void setShowTime(Time showTime) {
		this.showTime = showTime;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getStars() {
		return stars;
	}
	public void setStars(String stars) {
		this.stars = stars;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	public int getTotalBooked() {
		return totalBooked;
	}
	public void setTotalBooked(int totalBooked) {
		this.totalBooked = totalBooked;
	}
	
	
	@Override
	public String toString() {
		return "Film [filmId=" + filmId + ", title=" + title + ", description=" + description + ", genre=" + genre
				+ ", rated=" + rated + ", showTime=" + showTime + ", director=" + director + ", stars=" + stars
				+ ", screenName=" + screenName + ", totalSeats=" + totalSeats + ", totalBooked=" + totalBooked + "]";
	}


}
