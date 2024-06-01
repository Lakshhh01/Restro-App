package com.laksh.restro;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer>{

	List<Restaurant> findByName(String name);

}
