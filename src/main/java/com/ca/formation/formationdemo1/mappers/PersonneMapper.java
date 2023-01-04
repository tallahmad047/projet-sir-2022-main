package com.ca.formation.formationdemo1.mappers;

import com.ca.formation.formationdemo1.dto.PersonneDto;
import com.ca.formation.formationdemo1.models.Personne;

import org.springframework.stereotype.Service;

@Service
public class PersonneMapper {


    public PersonneDto fromPersonne(Personne personne){
        PersonneDto personneDto=new PersonneDto();
        personneDto.setId(personne.getId());
        personneDto.setNom(personne.getNom());
        personneDto.setPrenom(personne.getPrenom());
        personneDto.setAge(personne.getAge());

        return  personneDto;
    }
    public Personne fromPersonneDto(PersonneDto personneDto){
        Personne personne=new Personne();
        personne.setId(personneDto.getId());
        personne.setNom(personneDto.getNom());
        personne.setPrenom(personneDto.getPrenom());
        personne.setAge(personneDto.getAge());

        return  personne;
    }
}
