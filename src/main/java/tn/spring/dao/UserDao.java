package tn.spring.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.spring.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Long > {
	  Optional<User> findByUsername(String username);
	  Optional<User> findByEmail(String email);

	  Boolean existsByUsername(String username);

	  Boolean existsByEmail(String email);
}
