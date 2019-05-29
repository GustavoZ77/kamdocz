package tarea;

public class News {

    private String title;
    private NewsType type;

    public News(String title, NewsType type) {
        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public NewsType getType() {
        return type;
    }
}
