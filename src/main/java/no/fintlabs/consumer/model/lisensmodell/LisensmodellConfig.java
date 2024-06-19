package no.fintlabs.consumer.model.lisensmodell;

import no.fint.model.resource.ressurs.kodeverk.LisensmodellResource;
import no.fintlabs.core.consumer.shared.config.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class LisensmodellConfig extends ConsumerConfig<LisensmodellResource> {

    public LisensmodellConfig(ConsumerProps consumerProps) {
        super(consumerProps);
    }

    @Override
    protected String resourceName() {
        return "lisensmodell";
    }
}
