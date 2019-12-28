package cl.hccr.service.magneto;


import cl.hccr.service.magneto.domain.MutantRequest;
import cl.hccr.service.magneto.service.SimpleMutantCheckerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class MutantCheckerServiceTest {



    private SimpleMutantCheckerService mutantCheckerService;
    private final static String[] DNA = {"AAAAAA","BBBBBB","CCCCCC","DDDDDD","EEEEEE","FFFFFF"};
    private final static String PATRON = "AAAA";
    private final static String[] FORWARD_DIAGONAL_RESULT = {
            "DCBA",
            "EDCBA",
            "FEDCBA",
            "FEDCB",
            "FEDC"
    };

    private final static String[] BACKWARD_DIAGONAL_RESULT = {
            "CDEF",
            "BCDEF",
            "ABCDEF",
            "ABCDE",
            "ABCD"
    };
    private final static String[] VERTICAL_RESULT = {
            "ABCDEF",
            "ABCDEF",
            "ABCDEF",
            "ABCDEF",
            "ABCDEF",
            "ABCDEF"};

    private final static String[][] MATRIX_RESULT = {
            {"A","A","A","A","A","A"},
            {"B","B","B","B","B","B"},
            {"C","C","C","C","C","C"},
            {"D","D","D","D","D","D"},
            {"E","E","E","E","E","E"},
            {"F","F","F","F","F","F"}
    };

    private final static String [] MUTANT_DNA_1 = {
            "ATGCGA",
            "CAGTGC",
            "TTATGT",
            "AGAAGG",
            "CCCCTA",
            "TCACTG"
    };

    private final static String [] MUTANT_DNA_2 = {
            "AAAATGAAAA",
            "TATCGATAAT",
            "TGATCCGTAA",
            "TGCTACGGTG",
            "TGCATAAGTT",
            "CATTCACCTT",
            "GAATATGCTA",
            "ACTGAAGTCC",
            "GATGTATCTC",
            "TATCGAATGT",
    };
    private final static String [] NO_MUTANT_DNA = {
            "ATGCGA",
            "CAGTGC",
            "TTATTT",
            "AGACGG",
            "GCGTCA",
            "TTACTG"
    };




    @BeforeEach
    void setMockOutput() {
       mutantCheckerService = new SimpleMutantCheckerService();
    }

    @Test
    void getMatrix(){
        assertThat(mutantCheckerService.generateMatrix(DNA)).isEqualTo(MATRIX_RESULT);
    }

    @Test
    void getVerticalStringArray(){
        assertThat(mutantCheckerService.getVerticalStringArray(MATRIX_RESULT)).isEqualTo(VERTICAL_RESULT);
    }

    @Test
    void getBackwardDiagonalArray(){
        assertThat(mutantCheckerService.getBackwardDiagonalArray(MATRIX_RESULT, PATRON.length())).isEqualTo(BACKWARD_DIAGONAL_RESULT);
    }

    @Test
    void getForwardDiagonalArray(){
        assertThat(mutantCheckerService.getForwardDiagonalArray(MATRIX_RESULT, PATRON.length())).isEqualTo(FORWARD_DIAGONAL_RESULT);
    }


    @Test
    void isMutant_ResultTrue(){
        assertThat(mutantCheckerService.isMutant(new MutantRequest(MUTANT_DNA_1))).isEqualTo(true);
        assertThat(mutantCheckerService.isMutant(new MutantRequest(MUTANT_DNA_2))).isEqualTo(true);
    }
    @Test
    void isMutant_ResultFalse(){
        assertThat(mutantCheckerService.isMutant(new MutantRequest(NO_MUTANT_DNA))).isEqualTo(false);
    }

}

