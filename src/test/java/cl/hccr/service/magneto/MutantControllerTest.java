package cl.hccr.service.magneto;

import cl.hccr.service.magneto.controller.MutantController;
import cl.hccr.service.magneto.domain.MutantRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MutantController.class)
class MutantControllerTest {



    @Autowired
    private MockMvc mockMvc;

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
    void postMutantRequestWithoutBody_ShouldResponseNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URL))
                .andExpect(status().isBadRequest());
    }
    @Test
    void postNoMutant_ShouldResponseForbidden() throws Exception {

        MutantRequest mutantRequest = new MutantRequest(NOT_MUTANT_DNA);
        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(mutantRequest);


        mockMvc.perform(
                MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isForbidden());

    }

    @Test
    void postMutant_ShouldResponseOK() throws Exception {

        MutantRequest mutantRequest = new MutantRequest(MUTANT_DNA);
        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(mutantRequest);


        mockMvc.perform(
                MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

    }


}
