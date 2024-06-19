package no.fintlabs.consumer.model.brukertype;

import no.fint.model.resource.ressurs.kodeverk.BrukertypeResource;
import no.fint.model.resource.ressurs.kodeverk.BrukertypeResources;
import no.fint.relations.FintLinker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
public class BrukertypeLinker extends FintLinker<BrukertypeResource> {

    public BrukertypeLinker() {
        super(BrukertypeResource.class);
    }

    public void mapLinks(BrukertypeResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public BrukertypeResources toResources(Collection<BrukertypeResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public BrukertypeResources toResources(Stream<BrukertypeResource> stream, int offset, int size, int totalItems) {
        BrukertypeResources resources = new BrukertypeResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(BrukertypeResource resource) {
        return getAllSelfHrefs(resource).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(BrukertypeResource resource) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(resource.getSystemId().getIdentifikatorverdi(), "systemid"));
        }

        return builder.build();
    }

    int[] hashCodes(BrukertypeResource resource) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(resource.getSystemId().getIdentifikatorverdi().hashCode());
        }

        return builder.build().toArray();
    }
}