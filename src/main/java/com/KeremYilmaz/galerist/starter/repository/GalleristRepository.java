package com.KeremYilmaz.galerist.starter.repository;

import com.KeremYilmaz.galerist.starter.entity.Gallerist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleristRepository extends JpaRepository<Gallerist,Long> {
}
