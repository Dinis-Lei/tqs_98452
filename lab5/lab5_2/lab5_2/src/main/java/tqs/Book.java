package tqs;

import java.time.LocalDateTime;

public class Book {
	private final String title;
	private final String author;
	private final LocalDateTime published;
    
    public Book(String title, String author, LocalDateTime published) {
        this.title = title;
        this.author = author;
        this.published = published;
    }

    public Book(String title, String author, String date) {
        this.title = title;
        this.author = author;
        String[] dateAsList = date.split("-");
        this.published = LocalDateTime.of(Integer.parseInt(dateAsList[0]), Integer.parseInt(dateAsList[1]), Integer.parseInt(dateAsList[2]), 0, 0);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getPublished() {
        return published;
    }
 
	

}
