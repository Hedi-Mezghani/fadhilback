package tn.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.spring.dao.AddPdfDao;
import tn.spring.entity.AddPdf;

import java.util.List;

@RestController
@RequestMapping("/api/")
@CrossOrigin("http://localhost:4200")
public class AddPdfController {
    @Autowired
    private AddPdfDao addPdfDao;

    @GetMapping("user/{userId}/pdfs")
    public List<AddPdf>getPdfsByUserId(@PathVariable Long userId){
        return addPdfDao.findByUserId(userId);
    }

    @GetMapping("allpdf")
    public List<AddPdf>getAllPdf(){
        return addPdfDao.findAll();
    }
}
