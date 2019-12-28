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
    private final String[] DNA = {"AAAAAA","BBBBBB","CCCCCC","DDDDDD","EEEEEE","FFFFFF"};

    private final String[] FORWARD_DIAGONAL_RESULT = {
            "DCBA",
            "EDCBA",
            "FEDCBA",
            "FEDCB",
            "FEDC"
    };

    private final String[] BACKWARD_DIAGONAL_RESULT = {
            "CDEF",
            "BCDEF",
            "ABCDEF",
            "ABCDE",
            "ABCD"
    };

    @BeforeEach
    void setMockOutput() {
       mutantCheckerService = new SimpleMutantCheckerService();
    }

    @Test
    void getMatrix(){
        String[][] result = {
                {"A","A","A","A","A","A"},
                {"B","B","B","B","B","B"},
                {"C","C","C","C","C","C"},
                {"D","D","D","D","D","D"},
                {"E","E","E","E","E","E"},
                {"F","F","F","F","F","F"}
        };
        assertThat(mutantCheckerService.generateMatrix(DNA)).isEqualTo(result);
    }



    @Test
    void getVerticalStringArray(){
        String[] result = {
                "ABCDEF",
                "ABCDEF",
                "ABCDEF",
                "ABCDEF",
                "ABCDEF",
                "ABCDEF"};
        assertThat(mutantCheckerService.getVerticalStringArray(DNA)).isEqualTo(result);
    }

    @Test
    void getBackwardDiagonalArray(){
        int patronLength = 4;


        assertThat(mutantCheckerService.getBackwardDiagonalArray(DNA, patronLength)).isEqualTo(BACKWARD_DIAGONAL_RESULT);

    }



    /*



     */

/*

    @Test
    void passMatrix_ReturnHorizontalOrderedString(){
        String result = "AAABBBCCC";
        assertThat(mutantCheckerService.getHorizontalOrderString(MATRIX)).isEqualTo(result);
    }

    @Test
    void passMatrix_ReturnVerticalOrderedString(){
        String result = "ABCABCABC";
        assertThat(mutantCheckerService.getVerticlOrderString(MATRIX)).isEqualTo(result);
    }

    */
}

