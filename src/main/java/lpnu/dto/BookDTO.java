package lpnu.dto;

import javax.validation.constraints.*;
import java.time.Year;
import java.util.Map;

public class BookDTO {
    private Long id;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String title;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String author;
    @NotNull
    @PastOrPresent
    private Year yearOfPublication;
    @DecimalMin("0.0")
    @DecimalMax("5.0")
    private Double rating;
    private Map<@Size(min = 2, max = 4) String, @Positive @DecimalMax("5") Integer> location;

    public BookDTO() {
    }

    public BookDTO(final Long id, final String title, final String author, final Year yearOfPublication, final Double rating,
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