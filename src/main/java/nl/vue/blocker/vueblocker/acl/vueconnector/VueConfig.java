package nl.vue.blocker.vueblocker.acl.vueconnector;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class VueConfig {

    @Bean
    public WebClient createVueWebClient(){
       return WebClient.create("https://www.vuecinemas.nl");
    }
}
