package com.example.E_com; // Package declaration

import org.springframework.beans.factory.annotation.Autowired; // Import for dependency injection
import org.springframework.stereotype.Service; // Import for Service annotation
import org.springframework.dao.EmptyResultDataAccessException; // Import for handling JPA exceptions
import java.util.List; // Import for List type
import java.util.Optional; // Import for Optional type

@Service // Marks this class as a Spring service
public class ServiceLayer {

    @Autowired // Injects RepoLayer instance
    private RepoLayer repoLayer;

    // Get a sample fruit (not database-driven, for testing)
    public Fruit getFruit() {
        Fruit fruit = new Fruit("Watermelon", 20, 100); // Creates a sample Fruit object
        return fruit;
    }

    // Read: Get all fruits from the database
    public List<Fruit> getAllFruit() {
        System.out.println("Fetching all fruits"); // Log for debugging
        return repoLayer.findAll(); // Returns all Fruit entities
    }

    // Create: Add a new fruit
    public void addFruit(Fruit fruit) {
        System.out.println("Adding fruit: " + fruit.toString()); // Log the fruit being added
        repoLayer.save(fruit); // Saves the fruit to the database
    }

    // Update: Update an existing fruit (partial update)
    public boolean updateFruit(Fruit fruit) {
        System.out.println("Attempting to update fruit: " + fruit.toString()); // Log the update attempt
        if (fruit != null && fruit.getId() != 0) { // Check for valid fruit and ID
            Optional<Fruit> existingFruitOpt = repoLayer.findById(fruit.getId()); // Check if fruit exists
            if (existingFruitOpt.isPresent()) {
                Fruit existingFruit = existingFruitOpt.get(); // Get the existing fruit
                if (fruit.getName() != null) { // Update name if provided
                    existingFruit.setName(fruit.getName());
                }
                if (fruit.getPrice() != 0.0f) { // Update price if non-zero
                    existingFruit.setPrice(fruit.getPrice());
                }
                if (fruit.getWeight() != 0.0f) { // Update weight if non-zero
                    existingFruit.setWeight(fruit.getWeight());
                }
                repoLayer.save(existingFruit); // Save the updated fruit
                System.out.println("Successfully updated fruit: " + existingFruit.toString()); // Log success
                return true; // Indicate success
            } else {
                System.out.println("Update failed: Fruit with ID " + fruit.getId() + " not found"); // Log failure
                return false; // Indicate failure
            }
        }
        System.out.println("Update failed: Invalid fruit data (null fruit or invalid ID)"); // Log invalid input
        return false; // Indicate failure
    }

    // Delete: Delete a fruit by ID
    public boolean deleteFruitById(int id) {
        System.out.println("Attempting to delete fruit with ID: " + id); // Log the delete attempt
        if (id <= 0) { // Check for valid ID
            System.out.println("Delete failed: Invalid ID " + id); // Log invalid ID
            return false; // Indicate failure
        }
        try {
            Optional<Fruit> fruitOpt = repoLayer.findById(id); // Check if fruit exists
            if (fruitOpt.isPresent()) {
                repoLayer.deleteById(id); // Delete the fruit
                System.out.println("Successfully deleted fruit with ID: " + id); // Log success
                return true; // Indicate success
            } else {
                System.out.println("Delete failed: Fruit with ID " + id + " not found"); // Log not found
                return false; // Indicate failure
            }
        } catch (EmptyResultDataAccessException e) { // Handle case where ID doesn't exist
            System.out.println("Delete failed: Fruit with ID " + id + " not found (EmptyResultDataAccessException)"); // Log exception
            return false; // Indicate failure
        } catch (Exception e) { // Handle unexpected errors
            System.out.println("Delete failed: Unexpected error - " + e.getClass().getSimpleName() + ": " + e.getMessage()); // Log error
            return false; // Indicate failure
        }
    }
}