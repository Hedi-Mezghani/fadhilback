package tn.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import tn.spring.service.UserService;
import tn.spring.dao.UserDao;
import tn.spring.dao.WorksDao;

import tn.spring.dto.WorksDto;
import tn.spring.entity.User;
import tn.spring.entity.Works;

import tn.spring.exceptions.WorksNotFoundExeption;
import tn.spring.mapper.WorksMapper;

@AllArgsConstructor
@Transactional
@Service
@Slf4j

public class WorksServiceImp implements WorksService {
	
	private WorksDao WorksDao;
	
	private WorksMapper dtoMapper;
	private UserService userservice;

	@Override
	public WorksDto saveWorks(WorksDto worksDto) {
		
		log.info("saving new works");
//		worksDto.setDateJour(new Date());
//		worksDto.setNombreHeure(4);
		Works works = dtoMapper.fromWorksDto(worksDto);
		Works saveWorks = WorksDao.save(works);
		return dtoMapper.fromWorks(saveWorks);
	}

	@Override
	public List<WorksDto> ListWorks() {
		List<Works> Works= WorksDao.findAll();
		List<WorksDto>WorksDtos = Works.stream().map(cat -> dtoMapper.fromWorks(cat)).collect(Collectors.toList());
		return WorksDtos;
	}

	@Override
	public WorksDto getWorks(Long worksId) throws WorksNotFoundExeption {
		Works works=WorksDao.findById(worksId).orElseThrow(() -> new WorksNotFoundExeption("Task not found"));
		return dtoMapper.fromWorks(works);
	}

	@Override
	public WorksDto updateWorks(WorksDto WorksDto) {
		
		log.info("Update Works");
		Works works = dtoMapper.fromWorksDto(WorksDto);
		Works saveWorks = WorksDao.save(works);
		return dtoMapper.fromWorks(saveWorks);
	}

	@Override
	public void deleteWorks(Long worksId) {
		WorksDao.deleteById(worksId);
		
	}

	@Override
	public Page<Works> listWorks2(int page,int size) {
	  Page<Works> allworks2=WorksDao.findAll(PageRequest.of(page, size));
	  //Page<WorksDto>WorksDtos = (Page<WorksDto>) allworks2.stream().map(cat -> dtoMapper.fromWorks(cat)).collect(Collectors.toList());
	  return allworks2;
	}

	@Override
	public List<WorksDto> findWorksByUserid(Long id) {
		
		List<Works> worksbyuser=new ArrayList<>();
		List<Works> all=WorksDao.findAll();
		
		for (Works works : all) {
			if(works.getUser().getId()==id)
			{
				worksbyuser.add(works);
			}
			
		}
		List<WorksDto>WorksDtos = worksbyuser.stream().map(cat -> dtoMapper.fromWorks(cat)).collect(Collectors.toList());
		return WorksDtos;
	}

//	@Override
//	public Works saveWorksWithUSer(Long id, Works works) {
//		log.info("saving new works");
//		works.setDate_jour(new Date());
//		works.setNombre_heur(4);
//		
//		Optional<User> u = userservice.findById(id);
//		works.setUser(u);
//		
//		
//		return works;
//	}

	

}
