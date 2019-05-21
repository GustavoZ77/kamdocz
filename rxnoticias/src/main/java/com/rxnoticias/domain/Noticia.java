package com.rxnoticias.domain;

public class Noticia {

    private long id;
    private String descripcion;
    private NewsType newsType;

    public Noticia (long id, String descripcion, NewsType newsType) {
        this.id = id;
        this.descripcion = descripcion;
        this.newsType = newsType;
    }

    public long getId () {
        return id;
    }

    public String getDescripcion () {
        return descripcion;
    }

    public NewsType getNewsType () {
        return newsType;
    }

    @Override
    public String toString () {
        return "Noticia{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", newsType=" + newsType +
                '}';
    }
}
