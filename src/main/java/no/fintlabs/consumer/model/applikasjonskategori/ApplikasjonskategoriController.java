package no.fintlabs.consumer.model.applikasjonskategori;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import no.fint.antlr.FintFilterService;
import no.fint.model.resource.ressurs.kodeverk.ApplikasjonskategoriResource;
import no.fint.relations.FintRelationsMediaType;
import no.fintlabs.consumer.config.RestEndpoints;
import no.fintlabs.core.consumer.shared.resource.ConsumerRestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(name = "Applikasjonskategori", value = RestEndpoints.APPLIKASJONSKATEGORI, produces = {FintRelationsMediaType.APPLICATION_HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class ApplikasjonskategoriController extends ConsumerRestController<ApplikasjonskategoriResource> {

    public ApplikasjonskategoriController(ApplikasjonskategoriService service, ApplikasjonskategoriLinker linker, FintFilterService oDataFilterService) {
        super(service, linker, oDataFilterService);
    }

    @PostConstruct
    private void registerIdentificators() {
        super.registerIdenficatorHandler("systemId", ApplikasjonskategoriResource::getSystemId);
    }
}
