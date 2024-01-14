package tn.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.entity.AddPdf;

import java.util.List;

@Repository
public interface AddPdfDao extends JpaRepository<AddPdf,Long> {
    public List<AddPdf>findByUserId(Long userId);
}
