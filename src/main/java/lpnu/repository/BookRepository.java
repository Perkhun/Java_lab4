package lpnu.repository;

import lpnu.entity.Book;
import lpnu.entity.Operation;
import lpnu.exception.ServiceException;
import lpnu.service.AchievementService;
import lpnu.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookRepository {
    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    @Lazy
    private AchievementService achievementService;

    @PostConstruct
    public void init() {
        savedBooks = new ArrayList<>();
    }

    private static Long lastId = 0L;
    private List<Book> savedBooks;

    public Book getBookById(final Long id) {
        return savedBooks.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "Book with id: " + id + " not found", null));
    }

    public List<Book> getAllBooks() {
        return savedBooks;
    }

    public Book createBook(final Book book) {
        if (book.getId() != null) {
            throw new ServiceException(400, "Book should not have an id", null);
        } else if (savedBooks.stream()
                .filter(e -> e.getTitle().equals(book.getTitle()))
                .findFirst()
                .orElse(null) != null) {
            throw new ServiceException(400, "Book with title " + book.getTitle() + " already exists ", null);
        } else if (book.getTitle() == null) {
            throw new ServiceException(400, "Title must not be empty", null);
        } else if (book.getAuthor() == null) {
            throw new ServiceException(400, "Author must not be empty", null);
        } else if (book.getYearOfPublication() == null) {
            throw new ServiceException(400, "Year must not be empty", null);
        } else if (book.getRating() != null) {
            throw new ServiceException(400, "Book should not have a rating", null);
        } else if (book.getLocation() == null) {
            throw new ServiceException(400, "Location must not be empty", null);
        }

        ++lastId;
        book.setId(lastId);

        savedBooks.add(book);

        return book;
    }

    public Book updateBook(final Book book) {
        if (book.getId() == null) {
            throw new ServiceException(400, "Book should have an id ", null);
        } else if (savedBooks.stream()
                .filter(e -> e.getTitle().equals(book.getTitle()) && !e.getId().equals(book.getId()))
                .findFirst()
                .orElse(null) != null) {
            throw new ServiceException(400, "Book with title " + book.getTitle() + " already exists", null);
        } else if (book.getTitle() == null) {
            throw new ServiceException(400, "Title must not be empty ", null);
        } else if (book.getAuthor() == null) {
            throw new ServiceException(400, "Author must not be empty", null);
        } else if (book.getYearOfPublication() == null) {
            throw new ServiceException(400, "Year must not be empty", null);
        } else if (book.getRating() != null) {
            throw new ServiceException(400, "Book should not have a rating", null);
        } else if (book.getLocation() == null) {
            throw new ServiceException(400, "Location must not be empty", null);
        }

        final Book savedBook = getBookById(book.getId());

        savedBook.setTitle(book.getTitle());
        savedBook.setAuthor(book.getAuthor());
        savedBook.setYearOfPublication(book.getYearOfPublication());
        savedBook.setLocation(book.getLocation());

        if (achievementService.getBookReturnQuantityByBookId(book.getId()) > 0) {
            final double ratingAverage = operationRepository.getBookReturnByBookId(book.getId()).stream()
                            .mapToDouble(Operation::getRating).average().getAsDouble();

            savedBook.setRating(Util.round(ratingAverage));
        } else {
            savedBook.setRating(0.0);
        }

        return savedBook;
    }

    public void deleteBookById(final Long id) {
        if (id == null) {
            throw new ServiceException(400, "Id is not specified", null);
        }

        savedBooks.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "Book with id: " + id + " not found ", null ));

        savedBooks = savedBooks.stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList());

        if (achievementService.getAllOperationsQuantityByBookId(id) > 0) {
            operationRepository.deleteOperationsByBookId(id);
        }
    }
}
