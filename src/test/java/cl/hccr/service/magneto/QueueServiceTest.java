package cl.hccr.service.magneto;

import cl.hccr.service.magneto.domain.MutantRequest;
import cl.hccr.service.magneto.service.QueueService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class QueueServiceTest {

    @MockBean
    private SqsClient sqsClient;

    @Autowired
    private QueueService queueService;

    @Test
    void sendMutantRequestToQueue_ShouldNotFail(){
        given(sqsClient.sendMessage(
                SendMessageRequest.builder().build()))
                .willReturn(SendMessageResponse.builder().build());

        queueService.queueMutantRequest(new MutantRequest());
    }
}
