package com.ca.formation.formationdemo1.services.impl;


import com.ca.formation.formationdemo1.exception.ResourceNotFoundException;

import com.ca.formation.formationdemo1.models.Personne;
import com.ca.formation.formationdemo1.repositories.PersonneRepository;
import com.ca.formation.formationdemo1.services.PersonneService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class PersonneServiceImpl implements PersonneService {


    private final PersonneRepository personneRepository;

    public PersonneServiceImpl( PersonneRepository personneRepository) {

        this.personneRepository = personneRepository;
    }

    @Override
    public List getPersonnes() {
        return (List) personneRepository.findAll();
    }

    @Override
    public Personne getPersonne(Long id) throws ResourceNotFoundException {
        return personneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Personne non trouvé "));
    }

    @Override
    public Personne updatePersonne(Long id, Personne personneRequest) throws ResourceNotFoundException  {
        Optional<Personne> optionalPersonne = personneRepository.findById(id);
        if(optionalPersonne.isEmpty()){
            throw new ResourceNotFoundException("Mise à jour impossible ");
        }
        Personne personne = optionalPersonne.get();
        //todo verifier si l'id est le même que celui qui est dans personne
        if (personne.getId() != id) {
            throw new ResourceNotFoundException("Id in path and object do not match");
        }
        //todo setter les valeur qui doivent etre mise à jour

        personne.setAge(personneRequest.getAge());
        personne.setPrenom(personneRequest.getPrenom());
        personne.setNom(personneRequest.getNom());
        return personneRepository.save(personne);
    }


        @Override
        public Personne addPersonne(Personne personne) {
            return personneRepository.save(personne);
        }


    @Override
    public void deletePersonne(Long id) {
            personneRepository.deleteById(id);
    }

    @Override
    public List<Personne> getPersonneParNom(String nom) {
        return personneRepository.findByNom(nom);
    }

}
