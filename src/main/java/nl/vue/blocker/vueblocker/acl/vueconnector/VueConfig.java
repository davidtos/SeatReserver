package nl.vue.blocker.vueblocker.acl.vueconnector;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class VueConfig {

    @Bean
    public WebClient createVueWebClient() {

        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(-1)).build();

        return WebClient.builder()
                .baseUrl("https://www.vuecinemas.nl")
                .exchangeStrategies(exchangeStrategies)
                .build();
    }
}
