package tn.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.spring.dto.WorksDto;
import tn.spring.entity.User;
import tn.spring.entity.Works;

public interface WorksDao extends JpaRepository<Works, Long> {
}
