package itmo.blps.lab1.configuration.jta;

import cum.company.MailMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

@Configuration
@EnableTransactionManagement
public class JTAConfiguration {

    @Bean
    @Primary
    public JtaTransactionManager transactionManager(){
        return new JtaTransactionManager();
    }

    @Bean
    public KafkaTransactionManager kafkaTransactionManager(ProducerFactory<String, MailMessage> producerFactor){
        KafkaTransactionManager ktm =new KafkaTransactionManager<>(producerFactor);
        ktm.setTransactionSynchronization(AbstractPlatformTransactionManager.SYNCHRONIZATION_ON_ACTUAL_TRANSACTION);
        return ktm;
    }
}