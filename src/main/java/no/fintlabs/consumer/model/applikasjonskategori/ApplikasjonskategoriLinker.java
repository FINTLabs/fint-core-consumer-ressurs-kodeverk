package no.fintlabs.consumer.model.applikasjonskategori;

import no.fint.model.resource.ressurs.kodeverk.ApplikasjonskategoriResource;
import no.fint.model.resource.ressurs.kodeverk.ApplikasjonskategoriResources;
import no.fint.relations.FintLinker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
public class ApplikasjonskategoriLinker extends FintLinker<ApplikasjonskategoriResource> {

    public ApplikasjonskategoriLinker() {
        super(ApplikasjonskategoriResource.class);
    }

    public void mapLinks(ApplikasjonskategoriResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public ApplikasjonskategoriResources toResources(Collection<ApplikasjonskategoriResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public ApplikasjonskategoriResources toResources(Stream<ApplikasjonskategoriResource> stream, int offset, int size, int totalItems) {
        ApplikasjonskategoriResources resources = new ApplikasjonskategoriResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(ApplikasjonskategoriResource resource) {
        return getAllSelfHrefs(resource).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(ApplikasjonskategoriResource resource) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(resource.getSystemId().getIdentifikatorverdi(), "systemid"));
        }

        return builder.build();
    }

    int[] hashCodes(ApplikasjonskategoriResource resource) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(resource.getSystemId().getIdentifikatorverdi().hashCode());
        }

        return builder.build().toArray();
    }
}