package nl.vue.blocker.vueblocker;

import io.micrometer.core.instrument.util.IOUtils;
import nl.vue.blocker.vueblocker.movies.acl.layout.CinemaLayoutParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

class CinamaLayoutParserTest {

    @Test
    void parseLayout(){
        CinemaLayoutParser cinamaLayoutParser = new CinemaLayoutParser();

        ClassLoader classLoader = getClass().getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream("RawLayout.txt")) {

            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            cinamaLayoutParser.parseLayout(result);

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}