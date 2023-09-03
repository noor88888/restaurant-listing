package com.food_order_api.Restaurant_Listing_API.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.food_order_api.Restaurant_Listing_API.Dto.RestaurantDto;
import com.food_order_api.Restaurant_Listing_API.Entity.Restaurant;
import com.food_order_api.Restaurant_Listing_API.Mapper.RestaurantMapper;
import com.food_order_api.Restaurant_Listing_API.Respository.RestaurantRepo;
import com.food_order_api.Restaurant_Listing_API.Service.RestaurantService;
import com.food_order_api.Restaurant_Listing_API.Service.RestaurantService_Impl;


public class RestaurantServiceTest {
	
	 @Mock
	 RestaurantRepo restaurantRepo;

	    @InjectMocks
	    RestaurantService_Impl restaurantService;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this); //in order for Mock and InjectMocks annotations to take effect, you need to call MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testFindAllRestaurants() {
	        // Create mock restaurants
	        List<Restaurant> mockRestaurants = Arrays.asList(
	                new Restaurant(1, "Restaurant 1", "Address 1", "city 1", "Desc 1"),
	                new Restaurant(2, "Restaurant 2", "Address 2", "city 2", "Desc 2")
	        );
	        when(restaurantRepo.findAll()).thenReturn(mockRestaurants);

	        // Call the service method
	        List<RestaurantDto> restaurantDTOList = restaurantService.getAllRestaurants();

	        // Verify the result
	        assertEquals(mockRestaurants.size(), restaurantDTOList.size());
	        for (int i = 0; i < mockRestaurants.size(); i++) {
	            RestaurantDto expectedDTO = RestaurantMapper.INSTANCE.mapRestaurantTORestaurantDto(mockRestaurants.get(i));
	            assertEquals(expectedDTO, restaurantDTOList.get(i));
	        }

	        // Verify that the repository method was called
	        verify(restaurantRepo, times(1)).findAll();
	    }

	    @Test
	    public void testAddRestaurantInDB() {
	        // Create a mock restaurant to be saved
	        RestaurantDto mockRestaurantDTO = new RestaurantDto(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");
	        Restaurant mockRestaurant = RestaurantMapper.INSTANCE.mapRestaurantDtoTORestaurant(mockRestaurantDTO);

	        // Mock the repository behavior
	        when(restaurantRepo.save(mockRestaurant)).thenReturn(mockRestaurant);

	        // Call the service method
	        RestaurantDto savedRestaurantDTO = restaurantService.createRestaurant(mockRestaurantDTO);

	        // Verify the result
	        assertEquals(mockRestaurantDTO, savedRestaurantDTO);

	        // Verify that the repository method was called
	        verify(restaurantRepo, times(1)).save(mockRestaurant);
	    }

	    @Test
	    public void testFetchRestaurantById_ExistingId() {
	        // Create a mock restaurant ID
	        Integer mockRestaurantId = 1;

	        // Create a mock restaurant to be returned by the repository
	        Restaurant mockRestaurant = new Restaurant(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");

	        // Mock the repository behavior
	        when(restaurantRepo.findById(mockRestaurantId)).thenReturn(Optional.of(mockRestaurant));

	        // Call the service method
	        ResponseEntity<RestaurantDto> response = restaurantService.getRestaurantById(mockRestaurantId);

	        // Verify the response
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(mockRestaurantId, response.getBody().getRestaurantId());

	        // Verify that the repository method was called
	        verify(restaurantRepo, times(1)).findById(mockRestaurantId);
	    }

	    @Test
	    public void testFetchRestaurantById_NonExistingId() {
	        // Create a mock non-existing restaurant ID
	        Integer mockRestaurantId = 1;

	        // Mock the repository behavior
	        when(restaurantRepo.findById(mockRestaurantId)).thenReturn(Optional.empty());

	        // Call the service method
	        ResponseEntity<RestaurantDto> response = restaurantService.getRestaurantById(mockRestaurantId);

	        // Verify the response
	        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	        assertEquals(null, response.getBody());

	        // Verify that the repository method was called
	        verify(restaurantRepo, times(1)).findById(mockRestaurantId);
	    }

}
