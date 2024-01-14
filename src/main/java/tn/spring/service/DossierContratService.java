package tn.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.dao.DossierContratRepository;
import tn.spring.entity.DossierContrat;

import java.util.List;
@Service
public class DossierContratService {
    @Autowired
    private DossierContratRepository dossierContratRepository;

    public List<DossierContrat> getDossiersByUserId(Long userId) {
        return dossierContratRepository.findByUserId(userId);
    }

    public DossierContrat ajouterDossier(DossierContrat dossierContrat) {
        // Ajoutez la logique nécessaire pour ajouter un dossier
        return dossierContratRepository.save(dossierContrat);
    }

    public List<DossierContrat> listerDossiers() {
        // Ajoutez la logique nécessaire pour lister tous les dossiers
        return dossierContratRepository.findAll();
    }

    public DossierContrat getDossierById(Long id) {
        // Ajoutez la logique nécessaire pour récupérer un dossier par son ID
        return dossierContratRepository.findById(id).orElse(null);
    }

    public DossierContrat editerDossier(Long id, DossierContrat dossierContrat) {

        return dossierContratRepository.findById(id).map(existingDossier -> {
            existingDossier.setNom(dossierContrat.getNom());
            existingDossier.setDerniereModification(dossierContrat.getDerniereModification());
            existingDossier.setUser_id(dossierContrat.getUser_id());
            return dossierContratRepository.save(existingDossier);
        }).orElse(null);
    }

    public void supprimerDossier(Long id) {
        dossierContratRepository.deleteById(id);
    }
}
