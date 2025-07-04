package com.example.E_com; // Package declaration

import jakarta.persistence.Entity; // Import for JPA entity annotation
import jakarta.persistence.GeneratedValue; // Import for auto-generated ID
import jakarta.persistence.GenerationType; // Import for ID generation strategy
import jakarta.persistence.Id; // Import for primary key annotation

@Entity // Marks this class as a JPA entity, mapped to a database table
public class Fruit {

    @Id // Marks 'id' as the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto-generates the ID value
    private int id; // Primary key field
    private String name; // Fruit name field
    private float price; // Price field (changed from Double in earlier versions)
    private float weight; // Weight field in kg (replaces quantity)

    public Fruit() { // Default constructor required by JPA
    }

    public Fruit(String name, float price, float weight) { // Constructor for creating a Fruit without ID
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public Fruit(int id, String name, float price, float weight) { // Constructor for creating a Fruit with ID
        this.id = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public int getId() { // Getter for ID
        return id;
    }

    public void setId(int id) { // Setter for ID
        this.id = id;
    }

    public String getName() { // Getter for name
        return name;
    }

    public void setName(String name) { // Setter for name
        this.name = name;
    }

    public float getPrice() { // Getter for price (correct naming, returns float)
        return price;
    }

    public void setPrice(float price) { // Setter for price
        this.price = price;
    }

    public float getWeight() { // Getter for weight
        return weight;
    }

    public void setWeight(float weight) { // Setter for weight
        this.weight = weight;
    }

    @Override
    public String toString() { // String representation for logging
        return "Fruit{id=" + id + ", name='" + name + "', price=" + price + ", weight=" + weight + "}";
    }
}