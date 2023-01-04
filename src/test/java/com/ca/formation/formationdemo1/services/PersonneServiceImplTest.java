package com.ca.formation.formationdemo1.services;

import com.ca.formation.formationdemo1.dto.PersonneDto;
import com.ca.formation.formationdemo1.models.Personne;
import com.ca.formation.formationdemo1.models.Utilisateur;
import com.ca.formation.formationdemo1.repositories.PersonneRepository;
import com.ca.formation.formationdemo1.repositories.UtilisateurRepository;
import com.ca.formation.formationdemo1.services.impl.PersonneServiceImpl;
import com.ca.formation.formationdemo1.services.impl.UtilisateurServiceImpl;
import io.micrometer.core.instrument.config.validate.ValidationException;
import org.springframework.security.core.Authentication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class PersonneServiceImplTest {

  @Mock
  PersonneRepository personneRepository;

    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UtilisateurServiceImpl utilisateurServiceImpl;

  @InjectMocks
  private PersonneServiceImpl personneServiceImpl;
    @Mock
    UtilisateurRepository utilisateurRepository;
    @Mock
    private AuthenticationManager authenticationManager;
    // Test de la méthode login

    @Test
    public void testLogin() {
        // Préparer les données de test
        String username = "username";
        String password = "password";
        Utilisateur utilisateurRequest = new Utilisateur(username, password);

        // Mocker le comportement du repository
        Utilisateur utilisateurMock = new Utilisateur(username, password);
        when(utilisateurRepository.findByUsername(username)).thenReturn(Optional.of(utilisateurMock));

        // Mocker le comportement de l'authentication manager
        Authentication authenticationMock = mock(Authentication.class);
        when(authenticationManager.authenticate(any())).thenReturn(authenticationMock);
        when(authenticationMock.getPrincipal()).thenReturn(utilisateurMock);

        // Appeler la méthode à tester

        Utilisateur utilisateurResponse = utilisateurServiceImpl.login(utilisateurRequest);

        // Vérifier que la méthode retourne bien l'utilisateur attendu
        assertEquals(utilisateurMock, utilisateurResponse);
    }

    // Test de la méthode registration
    @Test
    public void testRegistration() throws ValidationException, javax.xml.bind.ValidationException {
        // Préparer les données de test
        String username = "username";
        String password = "password";
        Utilisateur utilisateurRequest = new Utilisateur(username, password);

        // Mocker le comportement du repository
        Utilisateur utilisateurMock = new Utilisateur(username, password);
        when(utilisateurRepository.save(utilisateurRequest)).thenReturn(utilisateurMock);

        // Mocker le comportement du password encoder
        String encodedPassword = "encoded_password";
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);

        // Appeler la méthode à tester
        Utilisateur utilisateurResponse = utilisateurServiceImpl.registration(utilisateurRequest);

        // Vérifier que la méthode retourne bien l'utilisateur attendu
        assertEquals(utilisateurMock, utilisateurResponse);
    }

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
        List<Personne> list = new ArrayList<Personne>();
        list.add(new Personne("tonuxxx", "samb", 50));
        list.add(new Personne("tonux", "sow", 50));
        list.add(new Personne("Nambe", "tall", 50));
       when(personneRepository.findByNom("tonux")).thenReturn(list);
        //When
        List<Personne> personList = personneServiceImpl.getPersonneParNom("tonux");
        //Then
        assertEquals(3, personList.size());
        assertEquals("sow",
        personList.get(1).getPrenom());
        //assertEquals("sow",
              //  personList.get(1).getPrenom());
       // verify(personneRepository, atLeastOnce()).findByNom("tonux");



    }
  // TODO: ajouter les autres tests sur methodes
}
