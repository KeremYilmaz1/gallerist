package com.KeremYilmaz.galerist.starter.repository;

import com.KeremYilmaz.galerist.starter.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    boolean existsByAddressId(Long addressId);

    boolean existsByAccountId(Long accountId);

    boolean existsByAccountIdAndIdNot(Long accountId, Long id);

    boolean existsByTcknAndIdNot(String tckn, Long id);


}
