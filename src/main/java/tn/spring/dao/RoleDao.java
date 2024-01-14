package tn.spring.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.spring.entity.Role;
import tn.spring.enums.ERole;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
