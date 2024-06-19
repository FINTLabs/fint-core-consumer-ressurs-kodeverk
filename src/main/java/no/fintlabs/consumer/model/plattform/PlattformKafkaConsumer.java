package no.fintlabs.consumer.model.plattform;

import no.fint.model.resource.ressurs.kodeverk.PlattformResource;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import no.fintlabs.kafka.common.ListenerBeanRegistrationService;
import no.fintlabs.kafka.entity.EntityConsumerFactoryService;
import no.fintlabs.kafka.entity.topic.EntityTopicService;
import org.springframework.stereotype.Service;

@Service
public class PlattformKafkaConsumer extends EntityKafkaConsumer<PlattformResource> {

    public PlattformKafkaConsumer(
            EntityConsumerFactoryService entityConsumerFactoryService,
            ListenerBeanRegistrationService listenerBeanRegistrationService,
            EntityTopicService entityTopicService,
            PlattformConfig plattformConfig) {
        super(entityConsumerFactoryService, listenerBeanRegistrationService, entityTopicService, plattformConfig);
    }
}
