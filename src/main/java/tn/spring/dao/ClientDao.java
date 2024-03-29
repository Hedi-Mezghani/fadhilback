package tn.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.entity.Client;

@Repository
public interface ClientDao extends JpaRepository<Client, Long> {

}
