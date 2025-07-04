package com.example.E_com; // Package declaration

import org.springframework.data.jpa.repository.JpaRepository; // Import for JpaRepository
import org.springframework.stereotype.Repository; // Import for Repository annotation

@Repository // Marks this interface as a Spring Data repository
public interface RepoLayer extends JpaRepository<Fruit, Integer> { // Extends JpaRepository for Fruit entity with Integer ID
}