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
    private final static String[] FORWARD_DIAGONAL_RESULT = {
            "ATGC",
            "CGATG",
            "TCATGA",
            "CCAGC",
            "ACGT"
    };

    private final static String[] BACKWARD_DIAGONAL_RESULT = {
            "TGCC",
            "CTACT",
            "AAAATG",
            "TGTGA",
            "GTGG"
    };
    private final static String[] VERTICAL_RESULT = {
            "ACTACT",
            "TATGCC",
            "GGAACA",
            "CTTACC",
            "GGGGTT",
            "ACTGAG"
    };

    private final static String[][] MATRIX_RESULT = {
            {"A","T","G","C","G","A"},
            {"C","A","G","T","G","C"},
            {"T","T","A","T","G","T"},
            {"A","G","A","A","G","G"},
            {"C","C","C","C","T","A"},
            {"T","C","A","C","T","G"}
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
        assertThat(mutantCheckerService.generateMatrix(MUTANT_DNA_1)).isEqualTo(MATRIX_RESULT);
    }

    @Test
    void getVerticalStringArray(){
        assertThat(mutantCheckerService.getVerticalStringArray(MATRIX_RESULT)).isEqualTo(VERTICAL_RESULT);
    }

    @Test
    void getBackwardDiagonalArray(){
        assertThat(mutantCheckerService.getBackwardDiagonalArray(MATRIX_RESULT, 4)).isEqualTo(BACKWARD_DIAGONAL_RESULT);
    }

    @Test
    void getForwardDiagonalArray(){
        assertThat(mutantCheckerService.getForwardDiagonalArray(MATRIX_RESULT, 4)).isEqualTo(FORWARD_DIAGONAL_RESULT);
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

