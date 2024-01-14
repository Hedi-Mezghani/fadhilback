package tn.spring.service;

import java.util.List;

import org.springframework.data.domain.Page;

import tn.spring.dto.WorksDto;
import tn.spring.entity.Works;
import tn.spring.exceptions.WorksNotFoundExeption;

public interface WorksService {
	
	WorksDto saveWorks(WorksDto WorksDto);
	
//	Works saveWorksWithUSer(Long id,Works works);
	
	
	List<WorksDto>ListWorks();
	
	Page<Works>listWorks2(int page,int size);
	
	WorksDto getWorks(Long worksId ) throws WorksNotFoundExeption;
	
	WorksDto updateWorks(WorksDto WorksDto);
	
	List<WorksDto> findWorksByUserid(Long id);
	
	void deleteWorks(Long worksId);

	

}
