package com.flys.bible.dao.service;

import android.util.Log;

import com.flys.bible.entities.DailyVerset;
import com.flys.bible.entities.DailyVersetData;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.rest.spring.annotations.Headers;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

@EBean(scope = EBean.Scope.Singleton)
public class Dao extends AbstractDao implements IDao {

    // client du service web
    @RestService
    protected WebClient webClient;
    // sécurité
    @Bean
    protected MyAuthInterceptor authInterceptor;
    // le RestTemplate
    private RestTemplate restTemplate;
    // factory du RestTemplate
    private SimpleClientHttpRequestFactory factory;
    //Headers
    private Headers headers;

    @AfterInject
    public void afterInject() {
        // log
        Log.d(className, "afterInject");
        // on construit le restTemplate
        factory = new SimpleClientHttpRequestFactory();
        restTemplate = new RestTemplate(factory);
        // on fixe le convertisseur jSON
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        // on fixe le restTemplate du client web
        webClient.setRestTemplate(restTemplate);
    }

    @Override
    public void setUrlServiceWebJson(String url) {
        // on fixe l'URL du service web
        webClient.setRootUrl(url);
    }

    @Override
    public void setUser(String user, String mdp) {
        // on enregistre l'utilisateur dans l'intercepteur
        authInterceptor.setUser(user, mdp);
    }

    @Override
    public void setTimeout(int timeout) {
        if (isDebugEnabled) {
            Log.d(className, String.format("setTimeout thread=%s, timeout=%s", Thread.currentThread().getName(), timeout));
        }
        // configuration factory
        factory.setReadTimeout(timeout);
        factory.setConnectTimeout(timeout);
    }

    @Override
    public void setBasicAuthentification(boolean isBasicAuthentificationNeeded) {
        if (isDebugEnabled) {
            Log.d(className, String.format("setBasicAuthentification thread=%s, isBasicAuthentificationNeeded=%s", Thread.currentThread().getName(), isBasicAuthentificationNeeded));
        }
        // intercepteur d'authentification ?
        if (isBasicAuthentificationNeeded) {
            // on ajoute l'intercepteur d'authentification
            List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
            interceptors.add(authInterceptor);
            restTemplate.setInterceptors(interceptors);
        }
    }

    @Override
    public void setAuthorization(boolean authorization) {
        if (authorization) {
            restTemplate.getInterceptors().add((request, body, execution) -> {
                request.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                request.getHeaders().setAcceptLanguage("fr");
                request.getHeaders().add("x-youversion-developer-token", "chA_7xx7hm3FaLJDutnXZkKQU8s");
                return execution.execute(request, body);
            });
            webClient.setRestTemplate(restTemplate);
        }

    }


    @Override
    public Observable<DailyVerset> getDailyVerset(int element, int version) {

        return getResponse(() -> webClient.getDailyVerset(element, version));
    }

    @Override
    public Observable<byte[]> getDailyVersetImage() {
        return getResponse(()->webClient.getDailyVersetImage());
    }

    @Override
    public Observable<DailyVersetData> getDailyVersets(int version) {
        return getResponse(() -> webClient.getDailyVersets(version));
    }

    // méthodes privées -------------------------------------------------
    private void log(String message) {
        if (isDebugEnabled) {
            Log.d(className, message);
        }
    }

    // todo : implémentation IDao
}
