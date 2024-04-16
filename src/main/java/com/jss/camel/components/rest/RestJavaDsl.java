package com.jss.camel.components.rest;

import com.jss.camel.dto.WeatherDto;
import com.jss.secrets.SearchSecret;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.support.DefaultMessage;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.apache.camel.Exchange.HTTP_RESPONSE_CODE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Component
public class RestJavaDsl extends RouteBuilder {

    private final WeatherDataProvider weatherDataProvider;
    public RestJavaDsl(WeatherDataProvider weatherDataProvider) {
        this.weatherDataProvider = weatherDataProvider;
    }

    //http://localhost:8080/services/javadsl/weather/{city}
    //http://localhost:8080/services/javadsl/weather/london
    //http://localhost:8080/services/javadsl/weather/new york
    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.auto);

        from("rest:get:javadsl/weather/{city}?produces=application/json")
                .outputType(WeatherDto.class)
                .process(this::getWeatherData);

    }

    private void getWeatherData(Exchange exchange) {
        String city = exchange.getMessage().getHeader("city", String.class);
        WeatherDto currentWeather = weatherDataProvider.getCurrentWeather(city);

        if(Objects.nonNull(currentWeather)) {
            Message message = new DefaultMessage(exchange.getContext());
            message.setBody(currentWeather);
            exchange.setMessage(message);
        } else {
            exchange.getMessage().setHeader(HTTP_RESPONSE_CODE, NOT_FOUND.value());
        }

        SearchSecret secret = new SearchSecret();
        secret.getAndPrintSecret();

    }

}
