package reviews;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.domain.Page;

import java.util.List;

@JsonPropertyOrder({"page", "pages", "totalEntities", "entities"})
public class PageDecorator<T> {

    private final Page<T> page;

    public PageDecorator(Page<T> page) {
        this.page = page;
    }
    @JsonProperty("entities")
    public List<T> getContent() {
        return this.page.getContent();
    }

    @JsonProperty("pages")
    public int getTotalPages() {
        return page.getTotalPages();
    }

    @JsonProperty("totalEntities")
    public long getTotalElements() {
        return page.getTotalElements();
    }

    @JsonProperty("page")
    public long getPage() {
        return page.getNumber();
    }
}
