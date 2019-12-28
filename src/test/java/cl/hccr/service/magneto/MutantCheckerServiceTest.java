package cl.hccr.service.magneto;


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
        assertThat(mutantCheckerService.getVerticalStringArray(DNA)).isEqualTo(VERTICAL_RESULT);
    }

    @Test
    void getBackwardDiagonalArray(){
        assertThat(mutantCheckerService.getBackwardDiagonalArray(DNA, PATRON.length())).isEqualTo(BACKWARD_DIAGONAL_RESULT);
    }

    @Test
    void getForwardDiagonalArray(){
        assertThat(mutantCheckerService.getForwardDiagonalArray(DNA, PATRON.length())).isEqualTo(FORWARD_DIAGONAL_RESULT);
    }



}

