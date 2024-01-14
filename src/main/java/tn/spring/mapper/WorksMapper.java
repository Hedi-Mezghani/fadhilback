package tn.spring.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import tn.spring.dto.WorksDto;

import tn.spring.entity.Works;
@Service
public class WorksMapper {
	public WorksDto fromWorks(Works works) {
		WorksDto worksDto = new WorksDto();
		BeanUtils.copyProperties(works, worksDto);
		return worksDto;
		
	}
	public Works fromWorksDto(WorksDto worksDto) {
		Works works = new Works();
		BeanUtils.copyProperties(worksDto,works );
		return works;
	}
	
	

}
