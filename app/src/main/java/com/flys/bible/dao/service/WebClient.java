package com.flys.bible.dao.service;

import com.flys.bible.entities.DailyVerset;
import com.flys.bible.entities.DailyVersetData;

import org.androidannotations.rest.spring.annotations.Accept;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.MediaType;
import org.androidannotations.rest.spring.api.RestClientRootUrl;
import org.androidannotations.rest.spring.api.RestClientSupport;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

@Rest(converters = {MappingJackson2HttpMessageConverter.class})
public interface WebClient extends RestClientRootUrl, RestClientSupport {

    //set of daily verset
    @Get("/verse_of_the_day/{element}?version_id={version}")
    List<DailyVerset> getDailyVersets(@Path("element") int element, @Path("version") int version);

    @Get("/verse_of_the_day/{element}?version_id={version}")
    DailyVerset getDailyVerset(@Path("element") int element, @Path("version") int version);

    @Get("/verse_of_the_day?version_id={version}")
    DailyVersetData getDailyVersets(@Path("version") int version);

    @Get("")
    @Accept(MediaType.APPLICATION_OCTET_STREAM)
    byte[] getDailyVersetImage();

}
