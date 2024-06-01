package com.laksh.restro;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//converts data from java to json//
@RestController
@CrossOrigin(origins = "*")
public class AppDataController {

	@Autowired
	RestaurantRepository repo;
	
	//get the restros list//
	@GetMapping("/restaurants")
	public List<Restaurant> getRestaurants(){
		return (List<Restaurant>)repo.findAll();
	}
	
	
	//save(insert+update the restros)//
	@PostMapping("/restaurants")
	public Restaurant saveRestaurant(@RequestBody Restaurant resto) {
		return repo.save(resto);
	}

	
	
	// Delete restaurant//
    @DeleteMapping("/restaurants/{id}")
    public String deleteRestaurant(@PathVariable int id) {
        repo.deleteById(id);
        return "ok";
    }
    
    @PutMapping("/restaurants/updateByName/{name}")
    public ResponseEntity<?> updateRestaurantByName(@PathVariable String name, @RequestBody Restaurant updatedRestro) {
        List<Restaurant> restaurants = repo.findByName(name);
        if (!restaurants.isEmpty()) {
            for (Restaurant restaurant : restaurants) {
                restaurant.setAddress(updatedRestro.getAddress());
                restaurant.setCity(updatedRestro.getCity());
                restaurant.setCuisine(updatedRestro.getCuisine());
                restaurant.setRating(updatedRestro.getRating());
                restaurant.setImageurl(updatedRestro.getImageurl());
                repo.save(restaurant);
            }
            return ResponseEntity.ok("Restaurant(s) updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant with name " + name + " not found");
        }
    }



}


