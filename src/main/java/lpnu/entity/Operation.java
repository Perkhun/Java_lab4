package lpnu.entity;

import lpnu.dto.BookDTO;
import lpnu.entity.enumeration.Status;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class Operation {
    Book book;
    private String status;
    private Long id;
    private LocalDate operationDate;
    private String rack;
    private Integer shelf;
    private Double rating;
    private User user;

    public Operation() {

    }

    public Operation(final Long id, final Book book, final User user, final LocalDate operationDate, final String rack, final Integer shelf) {
        this.book = book;
        this.status = Status.ISSUANCE_OF_BOOK.toString();
        this.id = id;
        this.operationDate = operationDate;
        this.rack = rack;
        this.shelf = shelf;
        this.user = user;
    }



    public Operation(final Long id, final Book book, final User user, final LocalDate operationDate, final String rack, final Integer shelf, final Double rating) {
        this.book = book;
        this.status = Status.BOOK_RETURN.toString();
        this.id = id;
        this.operationDate = operationDate;
        this.rack = rack;
        this.shelf = shelf;
        this.rating = rating;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(final Book book) {
        this.book = book;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(final Double rating) {
        this.rating = rating;
    }

    public LocalDate getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(final LocalDate operationDate) {
        this.operationDate = operationDate;
    }

    public String getRack() {
        return rack;
    }

    public void setRack(final String rack) {
        this.rack = rack;
    }

    public Integer getShelf() {
        return shelf;
    }

    public void setShelf(final Integer shelf) {
        this.shelf = shelf;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }
}
