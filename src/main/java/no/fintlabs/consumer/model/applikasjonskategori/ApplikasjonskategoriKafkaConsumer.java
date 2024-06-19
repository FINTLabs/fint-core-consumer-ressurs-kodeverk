package no.fintlabs.consumer.model.applikasjonskategori;

import no.fint.model.resource.ressurs.kodeverk.ApplikasjonskategoriResource;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import no.fintlabs.kafka.common.ListenerBeanRegistrationService;
import no.fintlabs.kafka.entity.EntityConsumerFactoryService;
import no.fintlabs.kafka.entity.topic.EntityTopicService;
import org.springframework.stereotype.Service;

@Service
public class ApplikasjonskategoriKafkaConsumer extends EntityKafkaConsumer<ApplikasjonskategoriResource> {

    public ApplikasjonskategoriKafkaConsumer(
            EntityConsumerFactoryService entityConsumerFactoryService,
            ListenerBeanRegistrationService listenerBeanRegistrationService,
            EntityTopicService entityTopicService,
            ApplikasjonskategoriConfig applikasjonConfig) {
        super(entityConsumerFactoryService, listenerBeanRegistrationService, entityTopicService, applikasjonConfig);
    }
}
