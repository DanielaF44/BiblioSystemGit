package com.example.bibliosystem;

import com.example.bibliosystem.common.Helper;
import com.example.bibliosystem.entity.*;
import com.example.bibliosystem.repository.BiblioRepository;
import com.example.bibliosystem.repository.GenreRepository;
import com.example.bibliosystem.service.BiblioService;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.assertThat;

// IMPORTANT :  avant de lancer les tests il faut que
//spring.profiles.active=TEST
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class BiblioSystemApplicationTests {


    @InjectMocks
    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Mock
    private EntityManager entityManager;

    @Autowired
    BiblioRepository biblioRepository;

    @Autowired
    GenreRepository genreRepository;

    @InjectMocks
    private BiblioService biblioService;


    private ObjectMapper objectMapper = new ObjectMapper();

    /**************************************************************************************
     * common.Helper
     *************************************************************************************/
    @Test
    public void testcheckStringLengthBetween(){
       assertThat(Helper.checkStringLengthBetween("Test",-1,850),is(true));
       assertThat(Helper.checkStringLengthBetween("",-1,-2),is(true));
       assertThat(Helper.checkStringLengthBetween("test",850,-2),is(false));
       assertThat(Helper.checkStringLengthBetween("test",850,880),is(false));
       assertThat(Helper.checkStringLengthBetween("test",0,2),is(false));
        assertThat(Helper.checkStringLengthBetween("test",0,4),is(true));

    }

    /**************************************************************************************
     * Dashboard
     *************************************************************************************/
    //@WithMockUser(username = "test@test.com", password = "Test123!", roles = "USER")
    @Test
    public void testDashboardLivrePlusPretes() throws JsonProcessingException {
        ResponseEntity<String> reponse = this.restTemplate
                .getForEntity("http://localhost:8080/dashboard/livrespretes?ordreTri=1", String.class);
        assertThat(reponse.getStatusCode(), equalTo(HttpStatus.OK));
        JsonNode reponseENJson = objectMapper.readTree(reponse.getBody());
        assertThat(reponseENJson.isMissingNode(), is(false));
        assertThat(reponseENJson.toString(),
                not(" "));
    }

    @Test
    public void testDashboardLivremoinsPretes() throws JsonProcessingException {
        ResponseEntity<String> reponse = this.restTemplate
                .getForEntity("http://localhost:8080/dashboard/livrespretes?ordreTri=0", String.class);
        assertThat(reponse.getStatusCode(), equalTo(HttpStatus.OK));
        JsonNode reponseENJson = objectMapper.readTree(reponse.getBody());
        assertThat(reponseENJson.isMissingNode(), is(false));
        assertThat(reponseENJson.toString(),
                not(" "));
    }

    @Test
    public void testDashboardLivrePretesSansOrdre() throws JsonProcessingException {
        ResponseEntity<String> reponse = this.restTemplate
                .getForEntity("http://localhost:8080/dashboard/livrespretes", String.class);
        assertThat(reponse.getStatusCode(), equalTo(HttpStatus.OK));
        JsonNode reponseENJson = objectMapper.readTree(reponse.getBody());
        assertThat(reponseENJson.isMissingNode(), is(false));
        assertThat(reponseENJson.toString(),
                not(" "));
    }

    @Test
    public void testDashboardAuteursPlusLus() throws JsonProcessingException {
        ResponseEntity<String> reponse = this.restTemplate
                .getForEntity("http://localhost:8080/dashboard/auteurslus?ordretri=1", String.class);
        assertThat(reponse.getStatusCode(), equalTo(HttpStatus.OK));
        JsonNode reponseENJson = objectMapper.readTree(reponse.getBody());
        assertThat(reponseENJson.isMissingNode(), is(false));
        assertThat(reponseENJson.toString(),
                not(" "));
    }

    @Test
    public void testDashboardAuteursMoinsLus() throws JsonProcessingException {
        ResponseEntity<String> reponse = this.restTemplate
                .getForEntity("http://localhost:8080/dashboard/auteurslus?ordretri=0", String.class);
        assertThat(reponse.getStatusCode(), equalTo(HttpStatus.OK));
        JsonNode reponseENJson = objectMapper.readTree(reponse.getBody());
        assertThat(reponseENJson.isMissingNode(), is(false));
        assertThat(reponseENJson.toString(),
                not(" "));
    }

    @Test
    public void testDashboardAuteursLusSansOrdre() throws JsonProcessingException {
        ResponseEntity<String> reponse = this.restTemplate
                .getForEntity("http://localhost:8080/dashboard/auteurslus", String.class);
        assertThat(reponse.getStatusCode(), equalTo(HttpStatus.OK));
        JsonNode reponseENJson = objectMapper.readTree(reponse.getBody());
        assertThat(reponseENJson.isMissingNode(), is(false));
        assertThat(reponseENJson.toString(),
                not(" "));
    }





    /**************************************************************************************
     * BiblioService
     *************************************************************************************/

    @Test
    public void testGenres() throws JsonProcessingException {
        ResponseEntity<String> reponse = this.restTemplate
                .getForEntity("http://localhost:8080/genres", String.class);

        assertThat(reponse.getStatusCode(), equalTo(HttpStatus.OK));

        JsonNode reponseENJson = objectMapper.readTree(reponse.getBody());

        assertThat(reponseENJson.isMissingNode(), is(false));
        assertThat(reponseENJson.toString(),
                not(" "));
    }

    @Test
    public void testLangues() throws JsonProcessingException {
        ResponseEntity<String> reponse = this.restTemplate
                .getForEntity("http://localhost:8080/langues", String.class);

        assertThat(reponse.getStatusCode(), equalTo(HttpStatus.OK));

        JsonNode reponseENJson = objectMapper.readTree(reponse.getBody());

        assertThat(reponseENJson.isMissingNode(), is(false));
        assertThat(reponseENJson.toString(),
                not(" "));
    }


    @Test
    public void testLivresByCriteriaAll() throws JsonProcessingException {
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
                not(" "));
    }

    @Test
    public void testLivresByGenre() throws JsonProcessingException{
        ResponseEntity<String> reponse = this.restTemplate
                .getForEntity("http://localhost:8080/livres?genre=Comédie", String.class);
        assertThat(reponse.getStatusCode(), equalTo(HttpStatus.OK));
        JsonNode reponseENJson = objectMapper.readTree(reponse.getBody());
        assertThat(reponseENJson.isMissingNode(), is(false));
        assertThat(reponseENJson.toString(),
                not(" "));
    }

    @Test
    public void testDispos()throws JsonProcessingException{
        ResponseEntity<String> reponse = this.restTemplate
                .getForEntity("http://localhost:8080/disponibilite?isbn=95678987", String.class);
        assertThat(reponse.getStatusCode(), equalTo(HttpStatus.OK));
        JsonNode reponseENJson = objectMapper.readTree(reponse.getBody());
        assertThat(reponseENJson.isMissingNode(), is(false));
        assertThat(reponseENJson.toString(),
                not(" "));
    }

    /**************************************************************************************
     * JPA / Repository
     *************************************************************************************/

    @Test
    public void testLangueClass(){
        Langue testLangue = new Langue("Anglais");
        assertThat(testLangue.getNom(),equalTo("Anglais"));
    }

    @Test
    public void testLivre(){

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1988);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 5);
        Date dateTest = cal.getTime();

        ArrayList<Auteur> auteurList = new ArrayList<>();
        Auteur auteur = new Auteur();
        auteur.setId(1);
        auteur.setNom("Hugo");
        auteur.setPrenom("Victor");
        auteurList.add(auteur);

        ArrayList<Genre> genreList = new ArrayList<>();
        genreList.add(new Genre("genre"));

        Livre l = new Livre();
        l.setAuteurs(auteurList);
        l.setCover("/cover.png");
        l.setDescription("description");
        l.setDatePublication(dateTest);
        l.setGenres(genreList);
        l.setIsbn("123456");
        l.setEditeur(new Editeur(1,"Editeur"));
        Langue langue = new Langue();
        langue.setNom("Français");
        l.setLangue(langue);
        l.setTitre("Titre");


        assertThat(l.getIsbn(), equalTo("123456"));
        assertThat(l.getCover(), equalTo("/cover.png"));
        assertThat(l.getTitre(), equalTo("Titre"));
        assertThat(l.getDescription(), equalTo("description"));
        assertThat(l.getAuteurs().get(0).getNom(), equalTo("Hugo"));
        assertThat(l.getGenres().get(0).getNom(), equalTo("genre"));
        assertThat(l.getLangue().getNom(), equalTo("Français"));
        assertThat(l.getEditeur().getNom(), equalTo("Editeur"));
        assertThat(l.getDatePublication(), equalTo(dateTest));

    }

    @Test
    public void testAuteur(){
        Auteur test = new Auteur();
        test.setId(1);
        test.setNom("Hugo");
        test.setPrenom("Victor");

        assertThat(test.getId(),equalTo(1));
        assertThat(test.getNom(),equalTo("Hugo"));
        assertThat(test.getPrenom(),equalTo("Victor"));
    }

    @Test
    public void testEditeur(){
        Editeur emptyEditeur = new Editeur();
        Editeur testEditeur = new Editeur(1,"EditeurTest");
        assertThat(testEditeur.getId(),equalTo(1));
        assertThat(testEditeur.getNom(),equalTo("EditeurTest"));

    }

    @Test
    public void testGenre(){
        Genre emptyGenre = new Genre();
        Genre testGenre = new Genre("Policier");
        assertThat(testGenre.getNom(),equalTo("Policier"));
    }

    @Test
    public void testBibliotheque(){
        Bibliotheque biblio = new Bibliotheque();
        biblio.setNom("Biblio");
        assertThat(biblio.getNom(),equalTo("Biblio"));
    }

    @Test
    public void testExemplaire(){
        Exemplaire testEx = new Exemplaire();

        testEx.setExemplaireId(1);

        Livre l = new Livre();
        l.setIsbn("123456");
        testEx.setIsbn(l);

        Pret p = new Pret();
        p.setPret_id(1);
        testEx.setPretId(p);

        assertThat(testEx.getExemplaireId(), equalTo(1));
        assertThat(testEx.getIsbn().getIsbn(), equalTo("123456"));
        assertThat(testEx.getPretId().getPret_id(), equalTo(1));
    }


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
        exemplaireTest.setExemplaireId(1);


        Pret testPret = new Pret();
        testPret.setPret_id(1);
        testPret.setRenouvele(true);
        testPret.setDateFin(dateFinTest);
        testPret.setDateDebut(dateDebutTest);
        testPret.setExemplaireId(exemplaireTest);

        assertThat(testPret.getPret_id(), equalTo(1));
        assertThat(testPret.getDateDebut(), equalTo(dateDebutTest));
        assertThat(testPret.getDateFin(), equalTo(dateFinTest));
        assertThat(testPret.getRenouvele(), equalTo(true));
        assertThat(testPret.getExemplaireId().getExemplaireId(), equalTo(1));
    }






}
