package com.example.Book.now.repository;

import com.example.Book.now.Entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
//    List<UserProfile> findAll();
    Optional<UserProfile> findByUserId(Integer userId);

}
