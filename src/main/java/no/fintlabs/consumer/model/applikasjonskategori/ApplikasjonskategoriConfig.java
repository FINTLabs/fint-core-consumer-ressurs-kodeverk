package no.fintlabs.consumer.model.applikasjonskategori;

import no.fint.model.resource.ressurs.kodeverk.ApplikasjonskategoriResource;
import no.fintlabs.core.consumer.shared.config.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class ApplikasjonskategoriConfig extends ConsumerConfig<ApplikasjonskategoriResource> {

    public ApplikasjonskategoriConfig(ConsumerProps consumerProps) {
        super(consumerProps);
    }

    @Override
    protected String resourceName() {
        return "applikasjonkategori";
    }
}
