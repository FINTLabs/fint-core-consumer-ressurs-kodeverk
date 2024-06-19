package no.fintlabs.consumer.model.handhevingstype;

import no.fint.model.resource.ressurs.kodeverk.HandhevingstypeResource;
import no.fint.model.resource.ressurs.kodeverk.HandhevingstypeResources;
import no.fint.relations.FintLinker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
public class HandhevingstypeLinker extends FintLinker<HandhevingstypeResource> {

    public HandhevingstypeLinker() {
        super(HandhevingstypeResource.class);
    }

    public void mapLinks(HandhevingstypeResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public HandhevingstypeResources toResources(Collection<HandhevingstypeResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public HandhevingstypeResources toResources(Stream<HandhevingstypeResource> stream, int offset, int size, int totalItems) {
        HandhevingstypeResources resources = new HandhevingstypeResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(HandhevingstypeResource resource) {
        return getAllSelfHrefs(resource).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(HandhevingstypeResource resource) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(resource.getSystemId().getIdentifikatorverdi(), "systemid"));
        }

        return builder.build();
    }

    int[] hashCodes(HandhevingstypeResource resource) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(resource.getSystemId().getIdentifikatorverdi().hashCode());
        }

        return builder.build().toArray();
    }
}