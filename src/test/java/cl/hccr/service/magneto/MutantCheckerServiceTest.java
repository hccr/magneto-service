package cl.hccr.service.magneto;


import cl.hccr.service.magneto.service.MutantCheckerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MutantCheckerServiceTest {



    private MutantCheckerService mutantCheckerService;


    @BeforeEach
    void setMockOutput() {
        when(textService.getText()).thenReturn(MOCK_OUTPUT);
    }


    @Test
    void contextLoads() {
        assertEquals(showService.getShowLable(), MOCK_OUTPUT);
    }
}

