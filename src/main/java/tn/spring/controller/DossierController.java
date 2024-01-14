package tn.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.spring.entity.Dossier;
import tn.spring.service.DossierService;

import java.util.List;

@RestController
@RequestMapping("/api/dossier")
@CrossOrigin("http://localhost:4200")
public class DossierController {
    @Autowired
    private DossierService dossierService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Dossier>> getDossiersByUserId(@PathVariable Long userId) {
        List<Dossier> dossiers = dossierService.getDossiersByUserId(userId);
        return ResponseEntity.ok(dossiers);
    }
    @PostMapping
    public Dossier ajouterDossier(@RequestBody Dossier dossier) {
        return dossierService.ajouterDossier(dossier);
    }

    @GetMapping
    public ResponseEntity<List<Dossier>> listerDossiers() {
        List<Dossier> dossiers = dossierService.listerDossiers();
        return ResponseEntity.ok(dossiers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dossier> getDossierById(@PathVariable Long id) {
        Dossier dossier = dossierService.getDossierById(id);
        if (dossier != null) {
            return ResponseEntity.ok(dossier);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dossier> editerDossier(@PathVariable Long id, @RequestBody Dossier dossier) {
        Dossier dossierEdite = dossierService.editerDossier(id, dossier);
        if (dossierEdite != null) {
            return ResponseEntity.ok(dossierEdite);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerDossier(@PathVariable Long id) {
        dossierService.supprimerDossier(id);
        return ResponseEntity.noContent().build();
    }
}
