package lpnu.mapper;

import lpnu.dto.BookDTO;
import lpnu.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookToBookDTOMapper {
    public Book toEntity(final BookDTO bookDTO) {
        final Book book = new Book();

        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setYearOfPublication(bookDTO.getYearOfPublication());
        book.setRating(bookDTO.getRating());
        book.setLocation(bookDTO.getLocation());

        return book;
    }

    public BookDTO toDTO(final Book book) {
        final BookDTO bookDTO = new BookDTO();

        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setYearOfPublication(book.getYearOfPublication());
        bookDTO.setRating(book.getRating());
        bookDTO.setLocation(book.getLocation());

        return bookDTO;
    }
}
