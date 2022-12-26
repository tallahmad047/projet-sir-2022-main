package com.ca.formation.formationdemo1.services;

import com.ca.formation.formationdemo1.models.Personne;
import com.ca.formation.formationdemo1.repositories.PersonneRepository;
import com.ca.formation.formationdemo1.services.impl.PersonneServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class PersonneServiceImplTest {

  @Mock
  PersonneRepository personneRepository;

  @InjectMocks
  private PersonneServiceImpl personneServiceImpl;

  @Test
  public void ajouterPersonne() {
    Personne personne = new Personne("tonux", "samb", 50);
    personne.setId(1L);
    when(personneRepository.save(any())).thenReturn(personne);

    Personne personneResponse = personneServiceImpl.addPersonne(new Personne("tonux", "samb", 50));

    assertNotNull(personneResponse);

    verify(personneRepository, atLeastOnce()).save(any());
  }
  @Test
 public void addPersonne() {
    //Given
    Personne personneResponse = new Personne("tonux", "samb", 50);
    personneResponse.setId(1L);
    when(personneRepository.save(any())).thenReturn(personneResponse);

    //When
    Personne personAded =personneServiceImpl.addPersonne(personneResponse);

    //Then
    assertEquals("tonux",personAded.getNom());
    assertEquals(1,personAded.getId());
    verify(personneRepository,atLeastOnce()).save(any());
  }
  @Test
    public void deletePersonne(){
        Personne person = new Personne("tonux", "samb", 50);
        person.setId(1L);
        doNothing().when(personneRepository).delete(any());

        //When
        personneServiceImpl.deletePersonne(person.getId());

        //Then
        verify(personneRepository, atLeastOnce()).deleteById(1L);
        verifyNoMoreInteractions(personneRepository);
    }
    @Test
    public void getPersonneParNom(){
     /* List<Personne> person= Arrays.asList(new Personne("tonux", "samb", 50),
              new Personne("Penda", "gadji", 23),
              new Personne("Nambe", "tall", 27));
      personneRepository.saveAll(person);
      List <Personne> result=personneServiceImpl.getPersonneParNom("Nambe");
      assertEquals(0,result.size());
      assertEquals("tall",result.get(0).getPrenom());*/
        List<Personne> list = new ArrayList<Personne>();
        list.add(new Personne("tonux", "samb", 50));
        list.add(new Personne("tonux", "sow", 50));
        list.add(new Personne("Nambe", "tall", 50));
        when(personneRepository.findAll()).thenReturn(list);
        //When
        List<Personne> personList = personneServiceImpl.getPersonneParNom("tonux");
        //Then
        assertEquals(2, personList.size());
        assertEquals("samb",
        personList.get(0).getPrenom());
        assertEquals("samb",
                personList.get(1).getPrenom());
        verify(personneRepository, atLeastOnce()).findByNom("tonux");



    }
  // TODO: ajouter les autres tests sur methodes
}
