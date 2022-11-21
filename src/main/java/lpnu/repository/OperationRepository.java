package lpnu.repository;

import lpnu.entity.Book;
import lpnu.entity.Operation;
import lpnu.entity.enumeration.Status;
import lpnu.exception.ServiceException;
import lpnu.util.Util;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OperationRepository {
    @PostConstruct
    public void init() {
        savedOperations = new ArrayList<>();
    }

    private static Long lastId = 0L;
    private List<Operation> savedOperations;

    public Operation getOperationsById(final Long id) {
        return savedOperations.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "Operation with id: " + id + " not found", null));
    }

    public Operation getOperationByBookAndUserId(final Long userId, final Long bookId) {
        return savedOperations.stream()
                .filter(e -> e.getUser().getId().equals(userId) && e.getBook().getId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "Operation with user id and book id: " + userId + " and " + bookId + " not found ", null));
    }

    public List<Operation> getOperationsByUserId(final Long userId) {
        return savedOperations.stream()
                .filter(e -> e.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

    public List<Operation> getIssuanceOfTheBookByUserId(final Long userId) {
        return savedOperations.stream()
                .filter(e -> e.getUser().getId().equals(userId))
                .filter(e -> e.getStatus().equals(Status.ISSUANCE_OF_BOOK.toString()))
                .collect(Collectors.toList());
    }

    public List<Operation> getBookReturnByUserId(final Long userId) {
        return savedOperations.stream()
                .filter(e -> e.getUser().getId().equals(userId))
                .filter(e -> e.getStatus().equals(Status.BOOK_RETURN.toString()))
                .collect(Collectors.toList());
    }

    public List<Operation> getOperationsByBookId(final Long bookId) {
        return savedOperations.stream()
                .filter(e -> e.getBook().getId().equals(bookId))
                .collect(Collectors.toList());
    }

    public List<Operation> getIssuanceOfTheBookByBookId(final Long bookId) {
        return savedOperations.stream()
                .filter(e -> e.getBook().getId().equals(bookId))
                .filter(e -> e.getStatus().equals(Status.ISSUANCE_OF_BOOK.toString()))
                .collect(Collectors.toList());
    }

    public List<Operation> getBookReturnByBookId(final Long bookId) {
        return savedOperations.stream()
                .filter(e -> e.getBook().getId().equals(bookId))
                .filter(e -> e.getStatus().equals(Status.BOOK_RETURN.toString()))
                .collect(Collectors.toList());
    }

    public List<Operation> getAllOperations() {
        return savedOperations;
    }

    public List<Operation> getAllIssuancesOfTheBook() {
        return savedOperations.stream()
                .filter(e -> e.getStatus().equals(Status.ISSUANCE_OF_BOOK.toString()))
                .collect(Collectors.toList());
    }

    public List<Operation> getAllBookReturn() {
        return savedOperations.stream()
                .filter(e -> e.getStatus().equals(Status.BOOK_RETURN.toString()))
                .collect(Collectors.toList());
    }

    public Operation createOperation(final Operation operation) {
        if (operation.getUser().getId() == null) {
            throw new ServiceException(400, "Operation should have user id ", null);
        } else if (operation.getBook().getId() == null) {
            throw new ServiceException(400, "Operation should have book id ", null);
        } else if (operation.getOperationDate() == null) {
            throw new ServiceException(400, "Operation should have operation date ", null);
        } else if (savedOperations.stream()
                .filter(e -> e.getUser().getId().equals(operation.getUser().getId())
                        && e.getBook().getId().equals(operation.getBook().getId())
                && e.getStatus().equals(Status.BOOK_RETURN.toString()))
                .findFirst().orElse(null) != null) {
            throw new ServiceException(400, "Operation already exists ", null);
        }

        if (operation.getRating() != null && operation.getRack() != null) {
            throw new ServiceException(400, "Operation should have either rating or rack ", null);
        } else if (operation.getRating() != null && operation.getShelf() != null) {
            throw new ServiceException(400, "Operation should have either rating or shelf ", null);
        } else if (operation.getRack() != null && operation.getShelf() == null) {
            throw new ServiceException(400, "If an operation has a rack, it must also contain a shelf ", null);
        } else if (operation.getShelf() != null && operation.getRack() == null) {
            throw new ServiceException(400, "If an operation has a shelf, it must also contain a rack ", null);
        } else if (operation.getShelf() != null && (!operation.getShelf().equals(operation.getBook().getLocation().get(operation.getRack())))) {
            throw new ServiceException(400, "The rack number or shelf number does not match where the book is located", null);
        }

        if (operation.getRating() != null) {
            operation.setStatus(Status.BOOK_RETURN.toString());
        } else if (operation.getRack() != null && operation.getShelf() != null) {
            operation.setStatus(Status.ISSUANCE_OF_BOOK.toString());
        }

        ++lastId;
        operation.setId(lastId);
        savedOperations.add(operation);

        if (operation.getUser().getOperations() != null) {
            operation.getUser().getOperations().add(operation);
        } else {
            operation.getUser().setOperations(new ArrayList<>(Collections.singletonList(operation)));
        }

        return operation;
    }

    public Operation updateOperation(final Operation operation) {
        if (operation.getUser().getId() == null) {
            throw new ServiceException(400, "Operation should have user id ", null);
        } else if (operation.getBook().getId() == null) {
            throw new ServiceException(400, "Operation should have book id", null);
        } else if (operation.getOperationDate() == null) {
            throw new ServiceException(400, "Operation should have operation date ", null);
        }

        final Operation savedOperation = getOperationByBookAndUserId(operation.getBook().getId(), operation.getUser().getId());

        if (operation.getRack() != null && operation.getShelf() == null) {
            throw new ServiceException(400, "If an operation has a rack, it must also contain a shelf ", null);
        } else if (operation.getShelf() != null && operation.getRack() == null) {
            throw new ServiceException(400, "If an operation has a shelf, it must also contain a rack ", null);
        } else if (operation.getShelf() != null && (!operation.getShelf().equals(operation.getBook().getLocation().get(operation.getRack())))) {
            throw new ServiceException(400, "The rack number or shelf number does not match where the book is located", null);
        }

        if (operation.getRating() != null) {
            savedOperation.setBook(operation.getBook());
            savedOperation.setStatus(Status.BOOK_RETURN.toString());
            savedOperation.setRating(Util.round(operation.getRating()));
            savedOperation.setOperationDate(operation.getOperationDate());
            savedOperation.setRack(operation.getRack());
            savedOperation.setShelf(operation.getShelf());
            savedOperation.setUser(operation.getUser());
        } else if (operation.getRack() != null && operation.getShelf() != null) {
            savedOperation.setBook(operation.getBook());
            savedOperation.setStatus(Status.ISSUANCE_OF_BOOK.toString());
            savedOperation.setRating(null);
            savedOperation.setOperationDate(operation.getOperationDate());
            savedOperation.setRack(operation.getRack());
            savedOperation.setShelf(operation.getShelf());
            savedOperation.setUser(operation.getUser());
        }

        final Operation savedOperations = getOperationsById(operation.getId());

        return savedOperation;
    }

    public void deleteOperationByBookAndUserId(final Long userId, final Long bookId) {
        if (userId == null) {
            throw new ServiceException(400, "Operation should have user id ", null);
        } else if (bookId == null) {
            throw new ServiceException(400, "Operation should have book id ", null);
        }

        savedOperations.stream()
                .filter(e -> e.getUser().getId().equals(userId) && e.getBook().getId().equals(bookId))
                .peek(e -> e.getUser().setOperations(e.getUser().getOperations().stream()
                        .filter(b -> !b.getBook().getId().equals(bookId))
                        .collect(Collectors.toList())))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "Operation with user id and book id: " + userId + " and " + bookId + " not found ", null));

        savedOperations = savedOperations.stream()
                .filter(e -> !(e.getUser().getId().equals(userId) && e.getBook().getId().equals(bookId)))
                .collect(Collectors.toList());
    }

    public void deleteOperationsByUserId(final Long userId) {
        if (userId == null) {
            throw new ServiceException(400, "Operation should have user id ", null);
        }

        savedOperations.stream()
                .filter(e -> e.getUser().getId().equals(userId))
                .peek(e -> e.getUser().setOperations(null))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "Operation with user id : " + userId + " not found ", null));

        savedOperations = savedOperations.stream()
                .filter(e -> !(e.getUser().getId().equals(userId)))
                .collect(Collectors.toList());
    }

    public void deleteOperationsByBookId(final Long bookId) {
        if (bookId == null) {
            throw new ServiceException(400, "Operation should have book id ", null);
        }

        savedOperations.stream()
                .filter(e -> e.getBook().getId().equals(bookId))
                .peek(e -> e.getUser().setOperations(e.getUser().getOperations().stream()
                        .filter(b -> !b.getBook().getId().equals(bookId))
                        .collect(Collectors.toList())))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "Operation with and book id: " + bookId + " not found ", null));

        savedOperations = savedOperations.stream()
                .filter(e -> !(e.getBook().getId().equals(bookId)))
                .collect(Collectors.toList());
    }
}
