package nl.vue.blocker.vueblocker;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@SpringBootApplication
@AllArgsConstructor
public class VueBlockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VueBlockerApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH").allowCredentials(true);
            }
        };
    }

//    @SneakyThrows
//    @Override
//    public void run(String... args) {
//        log.info("EXECUTING : command line runner");
//        // Movie[] movies = vueApi.getPerformancesByLocationAndDate(Location.EINDHOVEN, LocalDate.now());
//        // Movie[] movies2 = vueApi.getPerformancesByCinema(Location.EINDHOVEN, LocalDate.now());
//        // PerformanceLayout performanceLayout = vueApi.GetCinemaLayout(movies[0].getSlug(), movies[0].getPerformances().get(0).getId());
//        // Reservation reservation = vueApi.reserveSeat(8279811, 1);
//        Movie[] movies = vueApi.getExpectedMovies(LocalDate.now(), 730);
//        movies = movies;
//    }
}
