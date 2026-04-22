package com.budgetpath.repository;

import com.budgetpath.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    // SELECT * FROM places WHERE region = ?
    List<Place> findByRegion(String region);

    // SELECT count(*) > 0 FROM places WHERE region = ?
    boolean existsByRegion(String region);

    // SELECT * FROM places WHERE region = ? AND category = ?
    List<Place> findByRegionAndCategory(String region, Place.Category category);

    // SELECT * FROM places WHERE region = ? AND price <= ?
    @Query("SELECT p FROM Place p WHERE p.region = :region AND p.price <= :maxPrice")
    List<Place> findAffordable(@Param("region") String region, @Param("maxPrice") int maxPrice);
}
