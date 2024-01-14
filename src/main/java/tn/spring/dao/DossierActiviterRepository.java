package tn.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.spring.entity.DossierActiviter;

import java.util.List;

public interface DossierActiviterRepository extends JpaRepository<DossierActiviter,Long> {
    @Query("SELECT d FROM DossierActiviter d WHERE d.user_id = :userId")
    List<DossierActiviter> findByUserId(@Param("userId") Long userId);
}
