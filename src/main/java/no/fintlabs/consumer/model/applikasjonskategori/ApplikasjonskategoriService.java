package no.fintlabs.consumer.model.applikasjonskategori;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.ressurs.kodeverk.ApplikasjonskategoriResource;
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
public class ApplikasjonskategoriService extends CacheService<ApplikasjonskategoriResource> {

    private final EntityKafkaConsumer<ApplikasjonskategoriResource> entityKafkaConsumer;

    private final ApplikasjonskategoriLinker linker;

    public ApplikasjonskategoriService(
            ApplikasjonskategoriConfig consumerConfig,
            CacheManager cacheManager,
            ApplikasjonskategoriKafkaConsumer entityKafkaConsumer,
            ApplikasjonskategoriLinker linker) {
        super(consumerConfig, cacheManager, entityKafkaConsumer);
        this.entityKafkaConsumer = entityKafkaConsumer;
        this.linker = linker;
    }

    @Override
    protected Cache<ApplikasjonskategoriResource> initializeCache(CacheManager cacheManager, ConsumerConfig<ApplikasjonskategoriResource> consumerConfig, String s) {
        return cacheManager.create(PackingTypes.POJO, consumerConfig.getOrgId(), consumerConfig.getResourceName());
    }

    @PostConstruct
    private void registerKafkaListener() {
        long retention = entityKafkaConsumer.registerListener(ApplikasjonskategoriResource.class, this::addResourceToCache);
        getCache().setRetentionPeriodInMs(retention);
    }

    private void addResourceToCache(ConsumerRecord<String, ApplikasjonskategoriResource> consumerRecord) {
        this.eventLogger.logDataRecieved();
        if (consumerRecord.value() == null) {
            getCache().remove(consumerRecord.key());
        } else {
            ApplikasjonskategoriResource applikasjonResource = consumerRecord.value();
            linker.mapLinks(applikasjonResource);
            getCache().put(consumerRecord.key(), applikasjonResource, linker.hashCodes(applikasjonResource));
        }
    }

    @Override
    public Optional<ApplikasjonskategoriResource> getBySystemId(String systemId) {
        return getCache().getLastUpdatedByFilter(systemId.hashCode(),
                (resource) -> Optional
                        .ofNullable(resource)
                        .map(ApplikasjonskategoriResource::getSystemId)
                        .map(Identifikator::getIdentifikatorverdi)
                        .map(systemId::equals)
                        .orElse(false)
        );
    }
}
