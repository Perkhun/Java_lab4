package lpnu.entity;

import java.time.Year;
import java.util.Map;

public class Book {
    private Long id;
    private String title;
    private String author;
    private Year yearOfPublication;
    private Double rating;
    private Map<String, Integer> location;

    public Book(){
    }

    public Book(final Long id, final String title, final String author, final Year yearOfPublication, final Double rating,
                final Map<String, Integer> location) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.rating = rating;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public Year getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(final Year yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(final Double rating) {
        this.rating = rating;
    }

    public Map<String, Integer> getLocation() {
        return location;
    }

    public void setLocation(final Map<String, Integer> location) {
        this.location = location;
    }
}

