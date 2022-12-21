package com.emnager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emnager.entity.Users;

public interface UserRepository extends JpaRepository<Users,Integer>{

	Optional<Users> findByuserName(String employeName);

}
