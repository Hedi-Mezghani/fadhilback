package tn.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.spring.entity.DossierContrat;

import java.util.List;

public interface DossierContratRepository extends JpaRepository<DossierContrat,Long> {
    @Query("SELECT d FROM DossierContrat d WHERE d.user_id = :userId")
    List<DossierContrat> findByUserId(@Param("userId") Long userId);
}
