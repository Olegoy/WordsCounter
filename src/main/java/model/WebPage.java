package model;

import java.util.Map;
import java.util.Objects;

public class WebPage {

    private String name;
    private long id;
    private Map<String, Integer> uniqueWords;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<String, Integer> getUniqueWords() {
        return uniqueWords;
    }

    public void setUniqueWords(Map uniqueWords) {
        this.uniqueWords = uniqueWords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WebPage)) return false;
        WebPage webPage = (WebPage) o;
        return id == webPage.id &&
                name.equals(webPage.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "WebPage{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
