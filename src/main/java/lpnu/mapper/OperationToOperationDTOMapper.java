package lpnu.mapper;

import lpnu.dto.OperationDTO;
import lpnu.entity.Operation;
import lpnu.exception.ServiceException;
import lpnu.repository.BookRepository;
import lpnu.repository.UserRepository;
import lpnu.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class OperationToOperationDTOMapper {
    @Autowired
    private BookToBookDTOMapper bookMapper;

    @Autowired
    @Lazy
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public Operation toEntity(final OperationDTO operationDTO, final Long userId, final Long bookId) {
        if (userId == null) {
            throw new ServiceException(400, "Operation should have a user ", null);
        } else if (bookId == null) {
            throw new ServiceException(400, "Operation should have a book ", null);
        }
        final Operation operation = new Operation();

        operation.setId(operationDTO.getId());
        operation.setBook(bookRepository.getBookById(bookId));
        operation.setStatus(operationDTO.getStatus());
        operation.setOperationDate(operationDTO.getOperationDate());
        operation.setRack(operationDTO.getRack());
        operation.setShelf(operationDTO.getShelf());
        if (operationDTO.getRating() != null) {
            operation.setRating(Util.round(operationDTO.getRating()));
        } else {
            operation.setRating(operationDTO.getRating());
        }
        operation.setUser(userRepository.getUserById(userId));

        return operation;
    }

    public OperationDTO toDTO(final Operation operation) {
        final OperationDTO operationDTO = new OperationDTO();

        operationDTO.setId(operation.getId());
        operationDTO.setBook(bookMapper.toDTO(operation.getBook()));
        operationDTO.setStatus(operation.getStatus());
        operationDTO.setOperationDate(operation.getOperationDate());
        operationDTO.setRack(operation.getRack());
        operationDTO.setShelf(operation.getShelf());
        if (operation.getRating() != null) {
            operationDTO.setRating(Util.round(operation.getRating()));
        } else {
            operationDTO.setRating(operation.getRating());
        }

        return operationDTO;
    }
}
