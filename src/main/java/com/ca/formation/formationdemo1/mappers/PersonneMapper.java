package com.ca.formation.formationdemo1.mappers;

import com.ca.formation.formationdemo1.dto.PersonneDto;
import com.ca.formation.formationdemo1.models.Personne;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PersonneMapper {
    /* public CustomerDTO fromCustomer(Customer customer){
        CustomerDTO customerDTO=new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
       // customerDTO.setId(customer.getId());
        //customerDTO.setName(customer.getName());
        //customerDTO.setEmail(customer.getEmail());
        return customerDTO;
    }
    public Customer fromCustomerDTO(CustomerDTO customerDTO){
        Customer customer=new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }*/

    public PersonneDto fromPersonne(Personne personne){
        PersonneDto personneDto=new PersonneDto();
        personneDto.setId(personne.getId());
        personneDto.setNom(personne.getNom());
        personneDto.setPrenom(personne.getPrenom());
        personneDto.setAge(personne.getAge());
        //BeanUtils.copyProperties(personne,personneDto);
        return  personneDto;
    }
    public Personne fromPersonneDto(PersonneDto personneDto){
        Personne personne=new Personne();
        personne.setId(personneDto.getId());
        personne.setNom(personneDto.getNom());
        personne.setPrenom(personneDto.getPrenom());
        personne.setAge(personneDto.getAge());
       // BeanUtils.copyProperties(personneDto,personne);
        return  personne;
    }
}
