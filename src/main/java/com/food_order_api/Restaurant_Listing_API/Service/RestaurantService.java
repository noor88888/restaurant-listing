package com.food_order_api.Restaurant_Listing_API.Service;

import com.food_order_api.Restaurant_Listing_API.Dto.RestaurantDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RestaurantService
{

    RestaurantDto createRestaurant(RestaurantDto restaurant);

    List<RestaurantDto> getAllRestaurants();

   ResponseEntity< RestaurantDto> getRestaurantById(int restaurantId);
}
