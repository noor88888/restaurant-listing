package com.food_order_api.Restaurant_Listing_API.Controller;

import com.food_order_api.Restaurant_Listing_API.Dto.RestaurantDto;
import com.food_order_api.Restaurant_Listing_API.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin

public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @PostMapping()
    ResponseEntity<RestaurantDto> createRestaurant(@RequestBody RestaurantDto restaurant)
    {
        RestaurantDto restaurantDto=restaurantService.createRestaurant(restaurant);
        return new ResponseEntity<>(restaurantDto, HttpStatus.CREATED);
    }

    @GetMapping()
    ResponseEntity<List<RestaurantDto>> getAllRestaurants()
    {
        List<RestaurantDto> restaurantDto=restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurantDto, HttpStatus.OK);
    }
    @GetMapping("fetchById/{restaurantId}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable Integer restaurantId) {
        return restaurantService.getRestaurantById(restaurantId);
    }

}
