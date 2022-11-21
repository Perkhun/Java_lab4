package lpnu.dto;

import lpnu.entity.enumeration.Status;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

public class OperationDTO {
    private BookDTO book;
    private String status;
    private Long id;
    @NotNull
    @PastOrPresent
    private LocalDate operationDate;
    @Size(min = 2, max = 4)
    private String rack;
    @Positive
    @DecimalMax("5")
    private Integer shelf;
    @DecimalMin("0.0")
    @DecimalMax("5.0")
    private Double rating;

    public OperationDTO() {

    }

    public OperationDTO(final Long id, final BookDTO book, final LocalDate operationDate, @Valid final String rack, @Valid final Integer shelf) {

        this.book = book;
        this.status = Status.ISSUANCE_OF_BOOK.toString();
        this.id = id;
        this.operationDate = operationDate;
        this.rack = rack;
        this.shelf = shelf;
    }

    public OperationDTO(final Long id, final BookDTO book, final LocalDate operationDate, @Valid final String rack, @Valid final Integer shelf, final @Valid Double rating) {

        this.book = book;
        this.status = Status.BOOK_RETURN.toString();
        this.id = id;
        this.operationDate = operationDate;
        this.rack = rack;
        this.shelf = shelf;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(final BookDTO book) {
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

    public void setRating(final @Valid Double rating) {
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

    public void setRack(final @Valid String rack) {
        this.rack = rack;
    }

    public Integer getShelf() {
        return shelf;
    }

    public void setShelf(final @Valid Integer shelf) {
        this.shelf = shelf;
    }
}
