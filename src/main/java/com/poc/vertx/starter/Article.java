package com.poc.vertx.starter;

public class Article {
    private String id;
    private String title;
    private String author;
    private String datePublished;
    private int numOfWords;

    public Article(String id, String title, String author, String datePublished, int numOfWords) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.datePublished = datePublished;
        this.numOfWords = numOfWords;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getDatePublished() { return datePublished; }
    public int getNumOfWords() { return numOfWords; }
}