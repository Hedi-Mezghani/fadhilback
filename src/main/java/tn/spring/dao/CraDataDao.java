package tn.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.spring.entity.CraData;

import java.util.Date;
import java.util.List;
@Repository
public interface CraDataDao extends JpaRepository<CraData, Long> {
    List<CraData> findCraDataByDate(Date date);
    List<CraData> findByUserId(Long userId);


    List<CraData> findByDateAndUserId(@Param("date") Date date, @Param("userId") Long userId);


    List<CraData> findByYearAndMonth(@Param("year") int year, @Param("month") int month);


    List<CraData> findByYear(@Param("year") int year);


    List<CraData> findByMonth(@Param("month") int month);

}
