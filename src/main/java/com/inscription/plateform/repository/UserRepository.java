package com.inscription.plateform.repository;


import com.inscription.plateform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
