package com.KeremYilmaz.galerist.starter.repository;

import com.KeremYilmaz.galerist.starter.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken , Long> {
}
