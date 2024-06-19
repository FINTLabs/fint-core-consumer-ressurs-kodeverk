package no.fintlabs.consumer.model.handhevingstype;

import no.fint.model.resource.ressurs.kodeverk.HandhevingstypeResource;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import no.fintlabs.kafka.common.ListenerBeanRegistrationService;
import no.fintlabs.kafka.entity.EntityConsumerFactoryService;
import no.fintlabs.kafka.entity.topic.EntityTopicService;
import org.springframework.stereotype.Service;

@Service
public class HandhevingstypeKafkaConsumer extends EntityKafkaConsumer<HandhevingstypeResource> {

    public HandhevingstypeKafkaConsumer(
            EntityConsumerFactoryService entityConsumerFactoryService,
            ListenerBeanRegistrationService listenerBeanRegistrationService,
            EntityTopicService entityTopicService,
            HandhevingstypeConfig handhevingstypeConfig) {
        super(entityConsumerFactoryService, listenerBeanRegistrationService, entityTopicService, handhevingstypeConfig);
    }
}
