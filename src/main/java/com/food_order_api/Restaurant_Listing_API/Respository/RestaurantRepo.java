package com.food_order_api.Restaurant_Listing_API.Respository;

import com.food_order_api.Restaurant_Listing_API.Entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant,Integer> {
}
