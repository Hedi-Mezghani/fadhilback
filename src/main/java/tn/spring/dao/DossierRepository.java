package tn.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.spring.entity.AddPdf;
import tn.spring.entity.Dossier;

import java.util.List;

public interface DossierRepository extends JpaRepository<Dossier,Long> {
    @Query("SELECT d FROM Dossier d WHERE d.user_id = :userId")
    List<Dossier> findByUserId(@Param("userId") Long userId);
}
