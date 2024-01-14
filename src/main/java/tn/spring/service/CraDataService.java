package tn.spring.service;

import tn.spring.entity.Client;
import tn.spring.entity.CraData;
import tn.spring.exceptions.ProjectNotFoundExeption;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

public interface CraDataService {
    CraData saveCraData(CraData craData);

    List<CraData> ListCraDatas();

    CraData getCraData(Long id ) throws ProjectNotFoundExeption;

    CraData updateCraData(CraData craData);

    void deleteCraData(Long id);
    ByteArrayInputStream craPDFReport(List<CraData> craData) throws MalformedURLException, IOException ;

    List<CraData> findCraDataByDate(Date date);

    List<CraData> findByUserId(Long userId);

    List<CraData> findByYearAndMonth(int year, int month);

    List<CraData> findByYear(int year);
    List<CraData> findByMonth(int month);
}
