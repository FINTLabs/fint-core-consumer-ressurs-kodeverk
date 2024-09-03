package no.fintlabs.consumer.model.brukertype;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.ressurs.kodeverk.BrukertypeResource;
import no.fintlabs.cache.Cache;
import no.fintlabs.cache.CacheManager;
import no.fintlabs.cache.packing.PackingTypes;
import no.fintlabs.core.consumer.shared.resource.CacheService;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class BrukertypeService extends CacheService<BrukertypeResource> {

    private final EntityKafkaConsumer<BrukertypeResource> entityKafkaConsumer;

    private final BrukertypeLinker linker;

    public BrukertypeService(
            BrukertypeConfig consumerConfig,
            CacheManager cacheManager,
            BrukertypeKafkaConsumer entityKafkaConsumer,
            BrukertypeLinker linker) {
        super(consumerConfig, cacheManager, entityKafkaConsumer);
        this.entityKafkaConsumer = entityKafkaConsumer;
        this.linker = linker;
    }

    @Override
    protected Cache<BrukertypeResource> initializeCache(CacheManager cacheManager, ConsumerConfig<BrukertypeResource> consumerConfig, String s) {
        return cacheManager.create(PackingTypes.POJO, consumerConfig.getOrgId(), consumerConfig.getResourceName());
    }

    @PostConstruct
    private void registerKafkaListener() {
        entityKafkaConsumer.registerListener(BrukertypeResource.class, this::addResourceToCache);
    }

    private void addResourceToCache(ConsumerRecord<String, BrukertypeResource> consumerRecord) {
        updateRetensionTime(consumerRecord.headers().lastHeader("topic-retension-time"));
        this.eventLogger.logDataRecieved();
        if (consumerRecord.value() == null) {
            getCache().remove(consumerRecord.key());
        } else {
            BrukertypeResource applikasjonResource = consumerRecord.value();
            linker.mapLinks(applikasjonResource);
            getCache().put(consumerRecord.key(), applikasjonResource, linker.hashCodes(applikasjonResource));
        }
    }

    @Override
    public Optional<BrukertypeResource> getBySystemId(String systemId) {
        return getCache().getLastUpdatedByFilter(systemId.hashCode(),
                (resource) -> Optional
                        .ofNullable(resource)
                        .map(BrukertypeResource::getSystemId)
                        .map(Identifikator::getIdentifikatorverdi)
                        .map(systemId::equals)
                        .orElse(false)
        );
    }
}
