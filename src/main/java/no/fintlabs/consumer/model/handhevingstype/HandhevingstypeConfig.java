package no.fintlabs.consumer.model.handhevingstype;

import no.fint.model.resource.ressurs.kodeverk.HandhevingstypeResource;
import no.fintlabs.core.consumer.shared.config.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class HandhevingstypeConfig extends ConsumerConfig<HandhevingstypeResource> {

    public HandhevingstypeConfig(ConsumerProps consumerProps) {
        super(consumerProps);
    }

    @Override
    protected String resourceName() {
        return "handhevingstype";
    }
}
