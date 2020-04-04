package nl.vue.blocker.vueblocker.movies.acl.vueconnector;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class VueConfig {

    private static final int USE_ALL_AVAILABLE_MEMORY = -1;
    public static final String VUE_CINEMAS_NL_URL = "https://www.vuecinemas.nl";

    @Bean
    public WebClient createVueWebClient() {
        return WebClient.builder()
                .baseUrl(VUE_CINEMAS_NL_URL)
                .exchangeStrategies(getExchangeStrategyWithNoMemoryLimit())
                .build();
    }

    private ExchangeStrategies getExchangeStrategyWithNoMemoryLimit() {
        return ExchangeStrategies.builder()
                    .codecs(configurer -> configurer
                            .defaultCodecs()
                            .maxInMemorySize(USE_ALL_AVAILABLE_MEMORY))
                .build();
    }
}
