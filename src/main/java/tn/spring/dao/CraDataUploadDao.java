package tn.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.entity.CraDataPdf;
@Repository
public interface CraDataUploadDao extends JpaRepository<CraDataPdf,Long> {
}
