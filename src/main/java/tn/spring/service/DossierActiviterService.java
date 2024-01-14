package tn.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.dao.DossierActiviterRepository;
import tn.spring.entity.DossierActiviter;

import java.util.List;

@Service
public class DossierActiviterService {
    @Autowired
    private DossierActiviterRepository dossierActiviterRepository;

    public List<DossierActiviter> getDossiersByUserId(Long userId) {
        return dossierActiviterRepository.findByUserId(userId);
    }

    public DossierActiviter ajouterDossier(DossierActiviter dossierActiviter) {
        // Ajoutez la logique nécessaire pour ajouter un dossier
        return dossierActiviterRepository.save(dossierActiviter);
    }

    public List<DossierActiviter> listerDossiers() {
        // Ajoutez la logique nécessaire pour lister tous les dossiers
        return dossierActiviterRepository.findAll();
    }

    public DossierActiviter getDossierById(Long id) {
        // Ajoutez la logique nécessaire pour récupérer un dossier par son ID
        return dossierActiviterRepository.findById(id).orElse(null);
    }

    public DossierActiviter editerDossier(Long id, DossierActiviter dossierActiviter) {
        // Ajoutez la logique nécessaire pour éditer un dossier existant
        // Vérifiez si le dossier avec l'ID donné existe dans la base de données
        // Effectuez les mises à jour nécessaires
        // Retournez le dossier édité
        return dossierActiviterRepository.findById(id).map(existingDossier -> {
            existingDossier.setNom(dossierActiviter.getNom());
            existingDossier.setDerniereModification(dossierActiviter.getDerniereModification());
            existingDossier.setUser_id(dossierActiviter.getUser_id());
            return dossierActiviterRepository.save(existingDossier);
        }).orElse(null);
    }

    public void supprimerDossier(Long id) {
        // Ajoutez la logique nécessaire pour supprimer un dossier par son ID
        dossierActiviterRepository.deleteById(id);
    }
}
