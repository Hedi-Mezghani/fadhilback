package tn.spring.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.spring.entity.Date;

@Repository
public interface DateDao extends JpaRepository<Date, Long>{

}
