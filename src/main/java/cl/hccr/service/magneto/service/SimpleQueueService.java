package cl.hccr.service.magneto.service;

import cl.hccr.service.magneto.domain.MutantRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
public class SimpleQueueService implements QueueService {
    private SqsClient sqsClient;
    private ObjectMapper objectMapper;
    private final static String QUEUE_URL = "https://sqs.us-east-1.amazonaws.com/054232979367/mercado-libre-test";

    public SimpleQueueService(SqsClient sqsClient, ObjectMapper objectMapper) {
        this.sqsClient = sqsClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public void queueMutantRequest(MutantRequest request) {
        try {
            sqsClient.sendMessage(SendMessageRequest.builder()
                    .queueUrl(QUEUE_URL)
                    .messageBody(objectMapper.writeValueAsString(request))
                    .build());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
