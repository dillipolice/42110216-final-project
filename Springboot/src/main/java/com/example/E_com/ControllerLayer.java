package com.example.E_com; // Package declaration

import org.springframework.beans.factory.annotation.Autowired; // Import for dependency injection
import org.springframework.web.bind.annotation.*; // Import for REST annotations
import java.util.List; // Import for List type

@CrossOrigin // Allows cross-origin requests (consider specifying origins for production)
@RestController // Marks this class as a REST controller
@RequestMapping("/api/fruits") // Base path for all endpoints
public class ControllerLayer {

    @Autowired // Injects ServiceLayer instance
    private ServiceLayer serviceLayer;

    // Check API status
    @GetMapping("/status") // Maps GET /api/fruits/status
    public String status() {
        return "Active"; // Returns "Active" to confirm API is running
    }

    // Read: Get all fruits
    @GetMapping // Maps GET /api/fruits
    public List<Fruit> getAllFruit() {
        return serviceLayer.getAllFruit(); // Calls ServiceLayer to get all fruits
    }

    // Create: Add a new fruit
    @PostMapping("/addfruit") // Maps POST /api/fruits/addfruit
    public String addFruit(@RequestBody Fruit fruit) {
        if (fruit != null && fruit.getName() != null) { // Validate fruit and name
            serviceLayer.addFruit(fruit); // Call ServiceLayer to add fruit
            return "added"; // Indicate success
        }
        return "not added"; // Indicate failure
    }

    // Update: Update an existing fruit
    @PutMapping("/updatefruit") // Maps PUT /api/fruits/updatefruit
    public String updateFruit(@RequestBody Fruit fruit) {
        if (fruit != null && fruit.getId() != 0) { // Validate fruit and ID
            if (serviceLayer.updateFruit(fruit)) { // Call ServiceLayer to update
                return "updated"; // Indicate success
            }
        }
        return "not updated"; // Indicate failure
    }

    // Delete: Delete a fruit by ID
    @DeleteMapping("/{id}") // Maps DELETE /api/fruits/{id}
    public String deleteFruit(@PathVariable int id) { // Use PathVariable instead of RequestBody
        if (id != 0) { // Validate ID
            if (serviceLayer.deleteFruitById(id)) { // Call ServiceLayer to delete
                return "deleted"; // Indicate success
            }
        }
        return "not deleted"; // Indicate failure
    }
}