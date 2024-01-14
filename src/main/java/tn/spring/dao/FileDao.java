package tn.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.entity.File;
@Repository
public interface FileDao extends JpaRepository<File,Long> {

}
