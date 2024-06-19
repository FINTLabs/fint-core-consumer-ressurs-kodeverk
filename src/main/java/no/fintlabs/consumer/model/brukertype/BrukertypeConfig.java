package no.fintlabs.consumer.model.brukertype;

import no.fint.model.resource.ressurs.kodeverk.BrukertypeResource;
import no.fintlabs.core.consumer.shared.config.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class BrukertypeConfig extends ConsumerConfig<BrukertypeResource> {

    public BrukertypeConfig(ConsumerProps consumerProps) {
        super(consumerProps);
    }

    @Override
    protected String resourceName() {
        return "brukertype";
    }
}
