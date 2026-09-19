package com.KeremYilmaz.galerist.starter.repository;

import com.KeremYilmaz.galerist.starter.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
}
