package cl.hccr.service.magneto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class BeanConfig {

    @Bean
    public SqsClient getSqsClient(){

        SqsClient client = SqsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(ProfileCredentialsProvider.builder()
                        .profileName("userMercadoLibre")
                        .build())
                .build();
        return client;
    }
}
