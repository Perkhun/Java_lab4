package lpnu.service;

import lpnu.dto.BookDTO;
import lpnu.dto.OperationDTO;

import java.util.List;

public interface OperationService {
    OperationDTO getOperationsById(Long id);
    OperationDTO getOperationByBookAndUserId(Long userId, Long bookId);
    List<OperationDTO> getOperationsByUserId(Long userId);
    List<OperationDTO> getIssuanceOfTheBookByUserId(Long userId);
    List<OperationDTO> getBookReturnByUserId(Long userId);
    List<OperationDTO> getOperationsByBookId(Long bookId);
    List<OperationDTO> getIssuanceOfTheBookByBookId(Long bookId);
    List<OperationDTO> getBookReturnByBookId(Long bookId);
    List<OperationDTO> getAllOperations();
    List<OperationDTO> getAllIssuancesOfTheBook();
    List<OperationDTO> getAllBookReturn();
    OperationDTO createOperation(OperationDTO operationDTO, Long userId, Long bookId);
    OperationDTO updateOperation(OperationDTO operationDTO, Long userId, Long bookId);
    void deleteOperationByBookAndUserId(Long userId, Long bookId);
    void deleteOperationsByUserId(Long userId);
    void deleteOperationsByBookId(Long bookId);
}
