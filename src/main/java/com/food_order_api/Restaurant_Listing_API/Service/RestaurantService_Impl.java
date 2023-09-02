package com.food_order_api.Restaurant_Listing_API.Service;

import com.food_order_api.Restaurant_Listing_API.Dto.RestaurantDto;
import com.food_order_api.Restaurant_Listing_API.Entity.Restaurant;
import com.food_order_api.Restaurant_Listing_API.Mapper.RestaurantMapper;
import com.food_order_api.Restaurant_Listing_API.Respository.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService_Impl implements RestaurantService
{
    @Autowired
    RestaurantRepo restaurantRepo;

    @Override
    public RestaurantDto createRestaurant(RestaurantDto restaurantDTO) {
        Restaurant savedRestaurant =  restaurantRepo.save(RestaurantMapper.INSTANCE.mapRestaurantDtoTORestaurant(restaurantDTO));
        return RestaurantMapper.INSTANCE.mapRestaurantTORestaurantDto(savedRestaurant);
    }

    @Override
    public List<RestaurantDto> getAllRestaurants() {

        List<Restaurant> restaurants=restaurantRepo.findAll();
        List<RestaurantDto> restaurantDto=restaurants.stream()
                .map(restaurant -> RestaurantMapper.INSTANCE.mapRestaurantTORestaurantDto(restaurant)).collect(Collectors.toList());
        return restaurantDto;
    }

    @Override
    public ResponseEntity<RestaurantDto> getRestaurantById(int restaurantId)
    {
        Optional<Restaurant> restaurant=restaurantRepo.findById(restaurantId);
        if(restaurant.isPresent()){
            return new ResponseEntity<>(RestaurantMapper.INSTANCE.mapRestaurantTORestaurantDto(restaurant.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
