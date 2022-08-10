package com.example.bibliosystem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

//https://www.youtube.com/watch?v=8iHUjXkCuwI
//A virer
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class BiblioSystemApplicationTests {

    
    @InjectMocks
    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Test de la méthode du controlleur qui recupère la liste de livres: vérifie que la réponse ne soit pas vide
     *
     *
     */

    @Test
    public void testGetAll() throws JsonProcessingException {
        ResponseEntity<String> reponse = this.restTemplate
                .getForEntity("http://localhost:8080/livres", String.class);

        assertThat(reponse.getStatusCode(), equalTo(HttpStatus.OK));

        JsonNode reponseENJson = objectMapper.readTree(reponse.getBody());

        assertThat(reponseENJson.isMissingNode(), is(false));
        assertThat(reponseENJson.toString(),
                not(" "));

    }

    /**
     * Test de la méthode du controlleur qui recupère la liste de genres: vérifie que la réponse ne soit pas vide
     *
     *
     */

    @Test
    public void testGenres() throws JsonProcessingException{
        ResponseEntity<String> reponse = this.restTemplate
                .getForEntity("http://localhost:8080/genres", String.class);

        assertThat(reponse.getStatusCode(), equalTo(HttpStatus.OK));

        JsonNode reponseENJson = objectMapper.readTree(reponse.getBody());

        assertThat(reponseENJson.isMissingNode(), is(false));
        assertThat(reponseENJson.toString(),
                not(" "));
    }

    /**
     * Test de la méthode du controlleur qui recupère la liste de livres filtrée selon les critères spécifiés en
     * paramètres: vérifie que la réponse ne soit pas vide
     *
     *
     */

    @Test
    public void testLivresByCriteria() throws JsonProcessingException{
        ResponseEntity<String> reponse = this.restTemplate
                .getForEntity("http://localhost:8080/livresbycriteria", String.class);

        assertThat(reponse.getStatusCode(), equalTo(HttpStatus.OK));

        JsonNode reponseENJson = objectMapper.readTree(reponse.getBody());

        assertThat(reponseENJson.isMissingNode(), is(false));
        assertThat(reponseENJson.toString(),
                not(" "));
    }

    @Test
    public void testLivreByTitre() throws JsonProcessingException{
        ResponseEntity<String> reponse = this.restTemplate
                .getForEntity("http://localhost:8080/livresbycriteria?titre=Harry", String.class);

        assertThat(reponse.getStatusCode(), equalTo(HttpStatus.OK));

        JsonNode reponseENJson = objectMapper.readTree(reponse.getBody());

        assertThat(reponseENJson.isMissingNode(), is(false));
        assertThat(reponseENJson.toString(),
                equalTo("[{\"isbn\":\"95678987\",\"titre\":\"Harry Potter la chambre des secrets\",\"datePublication\":\"2001-04-03T22:00:00.000+00:00\"}]"));}

    @Test
    public void testLivresWithCriteria() throws JsonProcessingException{
        ResponseEntity<String> reponse = this.restTemplate
                .getForEntity("http://localhost:8080/livres?genre=Comédie&titre=Harry", String.class);

        assertThat(reponse.getStatusCode(), equalTo(HttpStatus.OK));

    }

    /**
     * Test de la création d'un objet Livre
     *
     *
     */

    @Test
    public void testLangues() throws JsonProcessingException{
        ResponseEntity<String> reponse = this.restTemplate
                .getForEntity("http://localhost:8080/langues", String.class);

        assertThat(reponse.getStatusCode(), equalTo(HttpStatus.OK));

        JsonNode reponseENJson = objectMapper.readTree(reponse.getBody());

        assertThat(reponseENJson.isMissingNode(), is(false));
        assertThat(reponseENJson.toString(),
                not(" "));
    }

    @Test
    public void testLivre(){

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1988);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 5);
        Date dateTest = cal.getTime();


        Livre l = new Livre("4253634", "Anna Karenina", dateTest);

        assertThat(l.getIsbn(), equalTo("4253634"));
        assertThat(l.getTitre(), equalTo("Anna Karenina"));
        assertThat(l.getDatePublication(), equalTo(dateTest));

    }

    @Test
    public void testAuteur(){
        Auteur test = new Auteur(1,"Hugo","Victor");

        assertThat(test.getId(),equalTo(1));
        assertThat(test.getNom(),equalTo("Hugo"));
        assertThat(test.getPrenom(),equalTo("Victor"));
    }

    @Test
    public void testEditeur(){
        Editeur testEditeur = new Editeur(1,"EditeurTest");
        assertThat(testEditeur.getId(),equalTo(1));
        assertThat(testEditeur.getNom(),equalTo("EditeurTest"));

    }

    @Test
    public void testGenre(){
        Genre testGenre = new Genre("Policier");
        assertThat(testGenre.getNom(),equalTo("Policier"));
    }

    @Test
    public void testLangue(){
        Langue testLangue = new Langue("Anglais");
        assertThat(testLangue.getNom(),equalTo("Anglais"));
    }

}
