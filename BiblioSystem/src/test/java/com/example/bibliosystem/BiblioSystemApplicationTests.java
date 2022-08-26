package com.example.bibliosystem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityManager;
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
                equalTo("[{\"isbn\":\"95678987\",\"titre\":\"Harry Potter la chambre des secrets\",\"datePublication\":\"2001-04-03T22:00:00.000+00:00\",\"editeur\":{\"id\":1,\"nom\":\"Hachette\"},\"description\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris quis leo vitae ante efficitur volutpat. Donec sit amet placerat metus. Sed iaculis sollicitudin felis, non eleifend augue molestie at. Quisque fringilla cursus ullamcorper. Integer fringilla accumsan velit ut placerat. Mauris lobortis, eros sit amet varius euismod, augue orci maximus neque, vitae mollis est odio in ipsum. Morbi ut venenatis est, ac cursus augue. Proin gravida, sapien vel tempus lacinia, diam quam aliquet ante, et porta velit nibh et mauris.\",\"langue\":{\"nom\":\"Français\"},\"auteurs\":[{\"id\":1,\"nom\":\"Rowling\",\"prenom\":\"JK\"}],\"genres\":[{\"nom\":\"Comédie\"}]}]"));}

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

    @Test
    public void testPrets()throws JsonProcessingException{
        ResponseEntity<String> reponse = this.restTemplate
                .getForEntity("http://localhost:8080/prets?utilisateurId=1", String.class);

        assertThat(reponse.getStatusCode(), equalTo(HttpStatus.OK));

        JsonNode reponseENJson = objectMapper.readTree(reponse.getBody());

        assertThat(reponseENJson.isMissingNode(), is(false));
        assertThat(reponseENJson.toString(),
                not(" "));
    }

    @Mock
    private EntityManager manager;

    @Mock
    private PretRepository pretRepository;

    @Mock
    private ExemplaireRepository exemplaireRepository;

    @InjectMocks
    private BiblioService biblioService;

    @Test
    public void testPret(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2022);
        cal.set(Calendar.MONTH, Calendar.AUGUST);
        cal.set(Calendar.DAY_OF_MONTH, 22);
        Date dateDebutTest = cal.getTime();

        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, 2022);
        cal2.set(Calendar.MONTH, Calendar.SEPTEMBER);
        cal2.set(Calendar.DAY_OF_MONTH, 22);
        Date dateFinTest = cal2.getTime();

        Exemplaire exemplaireTest = new Exemplaire();

        manager.persist(exemplaireTest);



        Pret testPret = new Pret(dateDebutTest, dateFinTest, false, exemplaireTest);
        System.out.println(testPret.getDateDebut());
        assertThat(testPret.getDateDebut(), equalTo(dateDebutTest));
        assertThat(testPret.getDateFin(), equalTo(dateFinTest));
        assertThat(testPret.getRenouvele(), equalTo(false));
        assertThat(testPret.getExemplaireId(), equalTo(exemplaireTest));
    }

    @Test
    public void testDispos()throws JsonProcessingException{
        ResponseEntity<String> reponse = this.restTemplate
                .getForEntity("http://localhost:8080/disponibilite?isbn=95678987", String.class);

        assertThat(reponse.getStatusCode(), equalTo(HttpStatus.OK));

        biblioService.countExemplaire("95678987");

        JsonNode reponseENJson = objectMapper.readTree(reponse.getBody());

        assertThat(reponseENJson.isMissingNode(), is(false));
        assertThat(reponseENJson.toString(),
                equalTo("[[\"Biblio1\",1],[\"Biblio2\",1]]"));
    }



}
