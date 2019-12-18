package app;

import java.util.Date;

class Article {
    int id;
    String title;
    Date created_at;
    Date updated_at;
    String content;
    int author;

    void inspect() {
        System.out.println("id: " + id);
        System.out.println("title: " + title);
        System.out.println("created_at: " + created_at);
        System.out.println("updated_at: " + updated_at);
        System.out.println("content: " + content);
        System.out.println("author: " + author);
    }
}