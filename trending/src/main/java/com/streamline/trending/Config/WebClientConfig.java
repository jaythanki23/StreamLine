package com.streamline.trending.Config;

import com.streamline.trending.Client.PostClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;

    @Bean
    public WebClient postWebClient() {
        return  WebClient.builder()
                .baseUrl("http://STREAMLINE")
                .filter(filterFunction)
                .build();
    }

    @Bean
    public PostClient postClient() {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(postWebClient())).build();
        return httpServiceProxyFactory.createClient(PostClient.class);
    }
}
