package com.KeremYilmaz.galerist.starter.repository;

import com.KeremYilmaz.galerist.starter.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}
