package tn.spring.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tn.spring.dto.WorksDto;
import tn.spring.entity.Works;
import tn.spring.exceptions.WorksNotFoundExeption;

import tn.spring.service.WorksServiceImp;


@RestController
@RequestMapping("/api/works")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
public class WorksController {
	
	private WorksServiceImp worksServiceImp;
	
	@GetMapping("/")
	public List<WorksDto> listWorks(){
		return worksServiceImp.ListWorks();
	}
	
	@GetMapping("/list")
	public Page<Works> listWorks2(@RequestParam(name="page",defaultValue = "0")int page,
									@RequestParam(name="size",defaultValue = "20")int size){
		return worksServiceImp.listWorks2(page, size);
	}
	@GetMapping("/{id}")
	public WorksDto getWorks(@PathVariable(name="id")Long id) throws WorksNotFoundExeption{
		return worksServiceImp.getWorks(id);
	}
	@PostMapping("/")
	public WorksDto saveWorks(@RequestBody WorksDto worksDto) {
		return worksServiceImp.saveWorks(worksDto);
	}
	
//	@PostMapping("/{id}")
//	public Works saveWorksWithUser(@PathVariable(name="id")Long id,@RequestBody Works works) {
//		return worksServiceImp.saveWorksWithUSer(id, works);
//	}
		
	@PutMapping("/{worksid}")
	public WorksDto updateWorks(@PathVariable Long worksid , @RequestBody WorksDto worksDto ) {
		worksDto.setId(worksid);
		return worksServiceImp.updateWorks(worksDto);
	}
	@DeleteMapping("/{id}")
	public void deleteWorks(@PathVariable Long id) {
		worksServiceImp.deleteWorks(id);
	}
	
	@GetMapping("/listbyuser/{id}")
	public List<WorksDto> listWorksbyuser(@PathVariable(name = "id")Long id){
		return worksServiceImp.findWorksByUserid(id);
	}
	
	
	

}
