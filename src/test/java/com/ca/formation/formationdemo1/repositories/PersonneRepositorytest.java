package com.ca.formation.formationdemo1.repositories;

import com.ca.formation.formationdemo1.models.Personne;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PersonneRepositorytest {

  @Autowired
  PersonneRepository personneRepository;

  @Test
  public void ajouterPersonne() {
    Personne personne = personneRepository.save(new Personne("tonux", "samb", 50));
    assertNotNull(personne);
    assertEquals( "tonux",personne.getNom());
  }
  @Test
 public void findByNom(){
   


  List < Personne> personneList= personneRepository.findByNom("tonux");
    
      assertEquals(1,personneList.size());
      assertEquals("tonux",personneList.get(0).getNom());
    




  }
  @Test
    public void findByNomAndPrenom(){
     
      List < Personne> personneList= personneRepository.findByNomAndPrenom("tonux","samb");
      assertNotNull(personneList);
      assertEquals(1,personneList.size());
   


    }
    @Test
    public void create(){
        Personne personne = personneRepository.save(new Personne("tonux", "samb", 50));
        assertNotNull(personne);
        assertEquals("tonux", personne.getNom());
    }
///
    @Test
    public void update(){
        //Given
        Personne personne = personneRepository.save(new Personne("tonux", "samb", 50));
        personne.setNom("Coundoul");
        //When
        Personne personUpdated = personneRepository.save(personne);
        //Then
        assertNotNull(personUpdated);
        assertEquals("Coundoul", personUpdated.getNom());
    }
    
    @Test
    public void delete(){
        Personne personne = personneRepository.save(new Personne("tonux", "samb", 50));
        personne.setId(1L);
        personneRepository.delete(personne);
        assertNotNull(personne);
        assertEquals(200,HttpStatus.OK.value());

    }



    
    @Test
    public void findById()
    {
        Personne personne = personneRepository.save(new Personne("tonux", "samb", 50));
        Optional<Personne> personList=personneRepository.findById(personne.getId());
        assertNotNull(personList);
        assertEquals("tonux",personList.get().getNom());
    }

   

    @Test
    public void findAll()
    {
        List<Personne> personList= (List<Personne>) personneRepository.findAll();
        assertNotNull(personList);
        assertEquals(2,personList.size());
    }

  

}
