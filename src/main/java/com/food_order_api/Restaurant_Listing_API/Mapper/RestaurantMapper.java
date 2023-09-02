package com.food_order_api.Restaurant_Listing_API.Mapper;

import com.food_order_api.Restaurant_Listing_API.Dto.RestaurantDto;
import com.food_order_api.Restaurant_Listing_API.Entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurantMapper
{
    RestaurantMapper INSTANCE= Mappers.getMapper(RestaurantMapper.class);

    Restaurant mapRestaurantDtoTORestaurant(RestaurantDto restaurantDto);

    RestaurantDto mapRestaurantTORestaurantDto(Restaurant restaurant);
}
