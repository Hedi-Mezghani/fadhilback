package tn.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.spring.entity.Project;


@Repository
public interface ProjectDao extends JpaRepository<Project, Long>{

}
