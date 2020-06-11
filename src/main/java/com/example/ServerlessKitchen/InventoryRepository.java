package com.example.ServerlessKitchen;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, String> {

    List<Inventory> findByQuantityGreaterThan(Integer quantity);

    /*@Query(value = "Select * From inventory i where i.quantity > 0", nativeQuery = true)
    List<Inventory> findInventory();*/
}
