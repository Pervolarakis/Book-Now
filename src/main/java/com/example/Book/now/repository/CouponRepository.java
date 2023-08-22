package com.example.Book.now.repository;

import com.example.Book.now.Entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, String> {

    Optional<Coupon> findById(String id);

    List<Coupon> findAll();

}
