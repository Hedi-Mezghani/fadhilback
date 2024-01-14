package tn.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.spring.entity.DossierActiviter;
import tn.spring.service.DossierActiviterService;

import java.util.List;
@RestController
@RequestMapping("/api/dossier/activiter")
@CrossOrigin("http://localhost:4200")
public class DossierActiviterController {
    @Autowired
    private DossierActiviterService dossierActiviterService;
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DossierActiviter>> getDossiersByUserId(@PathVariable Long userId) {
        List<DossierActiviter> dossiers = dossierActiviterService.getDossiersByUserId(userId);
        return ResponseEntity.ok(dossiers);
    }
    @PostMapping
    public DossierActiviter ajouterDossier(@RequestBody DossierActiviter dossierActiviter) {
        return dossierActiviterService.ajouterDossier(dossierActiviter);
    }

    @GetMapping
    public ResponseEntity<List<DossierActiviter>> listerDossiers() {
        List<DossierActiviter> dossierActiviters = dossierActiviterService.listerDossiers();
        return ResponseEntity.ok(dossierActiviters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DossierActiviter> getDossierById(@PathVariable Long id) {
        DossierActiviter dossierActiviter = dossierActiviterService.getDossierById(id);
        if (dossierActiviter != null) {
            return ResponseEntity.ok(dossierActiviter);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DossierActiviter> editerDossier(@PathVariable Long id, @RequestBody DossierActiviter dossierActiviter) {
        DossierActiviter dossierEdite = dossierActiviterService.editerDossier(id, dossierActiviter);
        if (dossierEdite != null) {
            return ResponseEntity.ok(dossierEdite);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerDossier(@PathVariable Long id) {
        dossierActiviterService.supprimerDossier(id);
        return ResponseEntity.noContent().build();
    }
}
