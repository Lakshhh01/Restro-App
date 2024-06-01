package com.laksh.restro;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="restaurants")
public class Restaurant {

	@Id
	@Column(name="id")
	int id;
	@Column(name="name")
	String name;
	@Column(name="cuisine")
	String cuisine;
	@Column(name="Address")
	String address;
	@Column(name="city")
	String city;
	@Column(name="rating")
	int rating;
	@Column(name="imageurl")
	String imageurl;
	
	public Restaurant(int id, String name, String cuisine, String address, String city, int rating, String imageurl) {
		super();
		this.id = id;
		this.name = name;
		this.cuisine = cuisine;
		this.address = address;
		this.city = city;
		this.rating = rating;
		this.imageurl = imageurl;
	}

	public Restaurant() {
		// TODO Auto-generated constructor stub
	}

	
	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", cuisine=" + cuisine + ", address=" + address + ", city="
				+ city + ", rating=" + rating + ", imageurl=" + imageurl + "]";
	}

	
	

}
