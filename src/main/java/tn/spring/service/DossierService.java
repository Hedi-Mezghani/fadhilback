package tn.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.dao.DossierRepository;
import tn.spring.entity.Dossier;

import java.util.List;

@Service
public class DossierService {

    @Autowired
    private DossierRepository dossierRepository;

    public List<Dossier> getDossiersByUserId(Long userId) {
        return dossierRepository.findByUserId(userId);
    }

    public Dossier ajouterDossier(Dossier dossier) {
        // Ajoutez la logique nécessaire pour ajouter un dossier
        return dossierRepository.save(dossier);
    }

    public List<Dossier> listerDossiers() {
        // Ajoutez la logique nécessaire pour lister tous les dossiers
        return dossierRepository.findAll();
    }

    public Dossier getDossierById(Long id) {
        // Ajoutez la logique nécessaire pour récupérer un dossier par son ID
        return dossierRepository.findById(id).orElse(null);
    }

    public Dossier editerDossier(Long id, Dossier dossier) {
        // Ajoutez la logique nécessaire pour éditer un dossier existant
        // Vérifiez si le dossier avec l'ID donné existe dans la base de données
        // Effectuez les mises à jour nécessaires
        // Retournez le dossier édité
        return dossierRepository.findById(id).map(existingDossier -> {
            existingDossier.setNom(dossier.getNom());
            existingDossier.setDerniereModification(dossier.getDerniereModification());
            existingDossier.setUser_id(dossier.getUser_id());
            return dossierRepository.save(existingDossier);
        }).orElse(null);
    }

    public void supprimerDossier(Long id) {
        // Ajoutez la logique nécessaire pour supprimer un dossier par son ID
        dossierRepository.deleteById(id);
    }
}
