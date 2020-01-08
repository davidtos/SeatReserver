package nl.vue.blocker.vueblocker.acl.layout;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

public class CinemaLayoutParser {

    public static PerformanceLayout parseLayout(String result){
        Document doc = Jsoup.parse(result);
        Elements elementsByTag = doc.getElementsByTag("script");
        List<Element> elementList = elementsByTag.stream()
                .filter(element -> !element.hasAttr("type"))
                .filter(element -> element.data().contains("window.shoppingCartData = "))
                .collect(Collectors.toList());
        String replace = elementList.get(0).data().replace("window.shoppingCartData = ", "");

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(replace, PerformanceLayout.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
