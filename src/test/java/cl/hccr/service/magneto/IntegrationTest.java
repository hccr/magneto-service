package cl.hccr.service.magneto;

import cl.hccr.service.magneto.domain.MutantRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@SpringBootTest(webEnvironment = RANDOM_PORT)
class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;
    private final static String URL = "/mutant";
    private final static String [] MUTANT_DNA = {
            "ATGCGA",
            "CAGTGC",
            "TTATGT",
            "AGAAGG",
            "CCCCTA",
            "TCACTG"
    };

    private final static String [] NOT_MUTANT_DNA = {
            "ATGCGA",
            "CAGTGC",
            "TTATTT",
            "AGACGG",
            "GCGTCA",
            "TCACTG"
    };


    @Test
    void sendRequest_returnIsMutant() {
        //arrange
        MutantRequest mutant = new MutantRequest(MUTANT_DNA);
        HttpEntity<MutantRequest> request = new HttpEntity<>(mutant);

        //act
        ResponseEntity<Void> response = restTemplate.exchange(URL, HttpMethod.POST, request, Void.class);

        //assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void sendRequest_returnIsNotMutant() {
        //arrange
        MutantRequest mutant = new MutantRequest(NOT_MUTANT_DNA);
        HttpEntity<MutantRequest> request = new HttpEntity<>(mutant);

        //act
        ResponseEntity<Void> response = restTemplate.exchange(URL, HttpMethod.POST, request, Void.class);

        //assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);

    }





}