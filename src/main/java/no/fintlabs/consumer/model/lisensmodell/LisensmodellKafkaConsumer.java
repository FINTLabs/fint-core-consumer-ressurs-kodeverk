package no.fintlabs.consumer.model.lisensmodell;

import no.fint.model.resource.ressurs.kodeverk.LisensmodellResource;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import no.fintlabs.kafka.common.ListenerBeanRegistrationService;
import no.fintlabs.kafka.entity.EntityConsumerFactoryService;
import no.fintlabs.kafka.entity.topic.EntityTopicService;
import org.springframework.stereotype.Service;

@Service
public class LisensmodellKafkaConsumer extends EntityKafkaConsumer<LisensmodellResource> {

    public LisensmodellKafkaConsumer(
            EntityConsumerFactoryService entityConsumerFactoryService,
            ListenerBeanRegistrationService listenerBeanRegistrationService,
            EntityTopicService entityTopicService,
            LisensmodellConfig lisensmodellConfig) {
        super(entityConsumerFactoryService, listenerBeanRegistrationService, entityTopicService, lisensmodellConfig);
    }
}
