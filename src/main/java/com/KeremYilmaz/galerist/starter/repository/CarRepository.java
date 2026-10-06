package com.KeremYilmaz.galerist.starter.repository;

import com.KeremYilmaz.galerist.starter.entity.Car;
import com.KeremYilmaz.galerist.starter.enums.CarStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    boolean existsByPlateAndIdNot(String plate, Long id);

    Page<Car> findByCarStatusType(CarStatusType carStatusType, Pageable pageable);

    boolean existsByPlate(String plate);
}
