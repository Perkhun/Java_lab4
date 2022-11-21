package lpnu.resource;

import lpnu.dto.BookDTO;
import lpnu.dto.OperationDTO;
import lpnu.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OperationResource {
    @Autowired
    private OperationService operationService;

    @GetMapping("/operations/{id}")
    public OperationDTO getOperationsById(final @PathVariable Long id) {
        return operationService.getOperationsById(id);
    }

    @GetMapping("/operations/{userId}/{bookId}")
    public OperationDTO getOperationByBookAndUserId(final @PathVariable Long userId, final @PathVariable Long bookId) {
        return operationService.getOperationByBookAndUserId(userId, bookId);
    }

    @GetMapping("/operations/user/{userId}")
    public List<OperationDTO> getOperationsByUserId(final @PathVariable Long userId) {
        return operationService.getOperationsByUserId(userId);
    }

    @GetMapping("/operations/issuance-of-the-book/user/{userId}")
    public List<OperationDTO> getIssuanceOfTheBookByUserId(final @PathVariable Long userId) {
        return operationService.getIssuanceOfTheBookByUserId(userId);
    }

    @GetMapping("/operations/book-return/user/{userId}")
    public List<OperationDTO> getBookReturnByUserId(final @PathVariable Long userId) {
        return operationService.getBookReturnByUserId(userId);
    }

    @GetMapping("/operations/book/{bookId}")
    public List<OperationDTO> getOperationsByBookId(final @PathVariable Long bookId) {
        return operationService.getOperationsByBookId(bookId);
    }

    @GetMapping("/operations/issuance-of-the-book/book/{bookId}")
    public List<OperationDTO> getIssuanceOfTheBookByBookId(final @PathVariable Long bookId) {
        return operationService.getIssuanceOfTheBookByBookId(bookId);
    }

    @GetMapping("/operations/book-return/book/{bookId}")
    public List<OperationDTO> getBookReturnByBookId(final @PathVariable Long bookId) {
        return operationService.getBookReturnByBookId(bookId);
    }

    @GetMapping("/operations")
    public List<OperationDTO> getAllOperations() {
        return operationService.getAllOperations();
    }

    @GetMapping("/operations/issuance-of-the-book")
    public List<OperationDTO> getAllIssuancesOfTheBook() {
        return operationService.getAllIssuancesOfTheBook();
    }

    @GetMapping("/operations/book-return")
    public List<OperationDTO> getAllBookReturn() {
        return operationService.getAllBookReturn();
    }

    @PostMapping("/operations/operation/{userId}/{bookId}")
    public OperationDTO createOperation(final @RequestBody OperationDTO operationDTO, final @PathVariable Long userId, final @PathVariable Long bookId) {
        return operationService.createOperation(operationDTO, userId, bookId);
    }

    @PutMapping("/operations/{userId}/{bookId}")
    public OperationDTO updateOperation(final @RequestBody OperationDTO operationDTO, final @PathVariable Long userId, final @PathVariable Long bookId) {
        return operationService.updateOperation(operationDTO, userId, bookId);
    }

    @DeleteMapping("/operations/{bookId}/{userId}")
    public void deleteOperationByBookAndUserId(final @PathVariable Long userId, final @PathVariable Long bookId) {
        operationService.deleteOperationByBookAndUserId(bookId,userId);
    }

    @DeleteMapping("/operations/user/{userId}")
    public void deleteOperationsByUserId(final @PathVariable Long userId) {
        operationService.deleteOperationsByUserId(userId);
    }

    @DeleteMapping("/operations/book/{bookId}")
    public void deleteOperationsByBookId(final @PathVariable Long bookId) {
        operationService.deleteOperationsByBookId(bookId);
    }
}
