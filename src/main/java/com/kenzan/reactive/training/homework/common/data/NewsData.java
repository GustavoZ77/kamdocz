package com.kenzan.reactive.training.homework.common.data;

import com.kenzan.reactive.training.homework.common.model.News;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.kenzan.reactive.training.homework.common.types.NewsType.*;

public class NewsData {
    private NewsData() {}

    public static List<News> getNews() {
        final List<News> newsList = new ArrayList<>();

        newsList.add(News.builder().id(1).description("Tigres defeats Leon").type(SPORTS).build());
        newsList.add(News.builder().id(2).description("Tuca gets mad").type(SPORTS).build());
        newsList.add(News.builder().id(3).description("AMLO hates media").type(POLITICS).build());
        newsList.add(News.builder().id(4).description("Nobody liked the final season of GOT").type(ENTERTAINMENT).build());
        newsList.add(News.builder().id(5).description("Everybody wants to watch Chernobyl").type(ENTERTAINMENT).build());
        newsList.add(News.builder().id(6).description("PEMEX politicians under investigation").type(POLITICS).build());

        Collections.shuffle(newsList);

        return newsList;
    }
}
