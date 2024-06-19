package no.fintlabs.consumer.model.lisensmodell;

import no.fint.model.resource.ressurs.kodeverk.LisensmodellResource;
import no.fint.model.resource.ressurs.kodeverk.LisensmodellResources;
import no.fint.relations.FintLinker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
public class LisensmodellLinker extends FintLinker<LisensmodellResource> {

    public LisensmodellLinker() {
        super(LisensmodellResource.class);
    }

    public void mapLinks(LisensmodellResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public LisensmodellResources toResources(Collection<LisensmodellResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public LisensmodellResources toResources(Stream<LisensmodellResource> stream, int offset, int size, int totalItems) {
        LisensmodellResources resources = new LisensmodellResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(LisensmodellResource resource) {
        return getAllSelfHrefs(resource).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(LisensmodellResource resource) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(resource.getSystemId().getIdentifikatorverdi(), "systemid"));
        }

        return builder.build();
    }

    int[] hashCodes(LisensmodellResource resource) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(resource.getSystemId().getIdentifikatorverdi().hashCode());
        }

        return builder.build().toArray();
    }
}