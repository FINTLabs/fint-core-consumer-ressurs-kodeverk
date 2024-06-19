package no.fintlabs.consumer.model.plattform;

import no.fint.model.resource.ressurs.kodeverk.PlattformResource;
import no.fintlabs.core.consumer.shared.config.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class PlattformConfig extends ConsumerConfig<PlattformResource> {

    public PlattformConfig(ConsumerProps consumerProps) {
        super(consumerProps);
    }

    @Override
    protected String resourceName() {
        return "plattform";
    }
}
