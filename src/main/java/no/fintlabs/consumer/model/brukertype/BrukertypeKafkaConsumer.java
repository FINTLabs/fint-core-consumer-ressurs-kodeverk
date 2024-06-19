package no.fintlabs.consumer.model.brukertype;

import no.fint.model.resource.ressurs.kodeverk.BrukertypeResource;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import no.fintlabs.kafka.common.ListenerBeanRegistrationService;
import no.fintlabs.kafka.entity.EntityConsumerFactoryService;
import no.fintlabs.kafka.entity.topic.EntityTopicService;
import org.springframework.stereotype.Service;

@Service
public class BrukertypeKafkaConsumer extends EntityKafkaConsumer<BrukertypeResource> {

    public BrukertypeKafkaConsumer(
            EntityConsumerFactoryService entityConsumerFactoryService,
            ListenerBeanRegistrationService listenerBeanRegistrationService,
            EntityTopicService entityTopicService,
            BrukertypeConfig brukertypeConfig) {
        super(entityConsumerFactoryService, listenerBeanRegistrationService, entityTopicService, brukertypeConfig);
    }
}
