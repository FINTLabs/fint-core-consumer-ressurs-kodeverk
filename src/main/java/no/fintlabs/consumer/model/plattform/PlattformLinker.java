package no.fintlabs.consumer.model.plattform;

import no.fint.model.resource.ressurs.kodeverk.PlattformResource;
import no.fint.model.resource.ressurs.kodeverk.PlattformResources;
import no.fint.relations.FintLinker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
public class PlattformLinker extends FintLinker<PlattformResource> {

    public PlattformLinker() {
        super(PlattformResource.class);
    }

    public void mapLinks(PlattformResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public PlattformResources toResources(Collection<PlattformResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public PlattformResources toResources(Stream<PlattformResource> stream, int offset, int size, int totalItems) {
        PlattformResources resources = new PlattformResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(PlattformResource resource) {
        return getAllSelfHrefs(resource).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(PlattformResource resource) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(resource.getSystemId().getIdentifikatorverdi(), "systemid"));
        }

        return builder.build();
    }

    int[] hashCodes(PlattformResource resource) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(resource.getSystemId().getIdentifikatorverdi().hashCode());
        }

        return builder.build().toArray();
    }
}