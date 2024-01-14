package tn.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.spring.entity.DossierContrat;
import tn.spring.service.DossierContratService;

import java.util.List;
@RestController
@RequestMapping("/api/dossier/contrat")
@CrossOrigin("http://localhost:4200")
public class DossierContratContrroller {
    @Autowired
    private DossierContratService dossierContratService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DossierContrat>> getDossiersByUserId(@PathVariable Long userId) {
        List<DossierContrat> dossiers = dossierContratService.getDossiersByUserId(userId);
        return ResponseEntity.ok(dossiers);
    }
    @PostMapping
    public DossierContrat ajouterDossier(@RequestBody DossierContrat dossierContrat) {
        return dossierContratService.ajouterDossier(dossierContrat);
    }

    @GetMapping
    public ResponseEntity<List<DossierContrat>> listerDossiers() {
        List<DossierContrat> dossiers = dossierContratService.listerDossiers();
        return ResponseEntity.ok(dossiers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DossierContrat> getDossierById(@PathVariable Long id) {
        DossierContrat dossierContrat = dossierContratService.getDossierById(id);
        if (dossierContrat != null) {
            return ResponseEntity.ok(dossierContrat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DossierContrat> editerDossier(@PathVariable Long id, @RequestBody DossierContrat dossierContrat) {
        DossierContrat dossierEdite = dossierContratService.editerDossier(id, dossierContrat);
        if (dossierEdite != null) {
            return ResponseEntity.ok(dossierEdite);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerDossier(@PathVariable Long id) {
        dossierContratService.supprimerDossier(id);
        return ResponseEntity.noContent().build();
    }
}
