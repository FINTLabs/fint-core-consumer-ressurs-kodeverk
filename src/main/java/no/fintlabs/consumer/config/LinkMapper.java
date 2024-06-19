package no.fintlabs.consumer.config;

import com.google.common.collect.ImmutableMap;
import no.fint.model.ressurs.kodeverk.*;

import java.util.Map;

public class LinkMapper {

    public static Map<String, String> linkMapper() {
        return ImmutableMap.<String, String>builder()
                .put(Applikasjonskategori.class.getName(), "/ressurs/kodeverk/applikasjonskategori")
                .put(Brukertype.class.getName(), "/ressurs/kodeverk/brukertype")
                .put(Handhevingstype.class.getName(), "/ressurs/kodeverk/handhevingstype")
                .put(Lisensmodell.class.getName(), "/ressurs/kodeverk/lisensmodell")
                .put(Plattform.class.getName(), "/ressurs/kodeverk/plattform")
                .build();
    }

}