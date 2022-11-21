package lpnu.service.impl;

import lpnu.dto.BookDTO;
import lpnu.dto.OperationDTO;
import lpnu.exception.ServiceException;
import lpnu.mapper.OperationToOperationDTOMapper;
import lpnu.repository.OperationRepository;
import lpnu.repository.UserRepository;
import lpnu.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationServiceImpl implements OperationService {
    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OperationToOperationDTOMapper operationMapper;

    @Override
    public OperationDTO getOperationsById(final Long id) {
        return operationMapper.toDTO(operationRepository.getOperationsById(id));
    }

    @Override
    public OperationDTO getOperationByBookAndUserId(final Long userId, final Long bookId) {
        return operationMapper.toDTO(operationRepository.getOperationByBookAndUserId(userId,bookId));
    }

    @Override
    public List<OperationDTO> getOperationsByUserId(final Long userId) {
        return operationRepository.getOperationsByUserId(userId).stream()
                .map(e -> operationMapper.toDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<OperationDTO> getIssuanceOfTheBookByUserId(final Long userId) {
        return operationRepository.getIssuanceOfTheBookByUserId(userId).stream()
                .map(e -> operationMapper.toDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<OperationDTO> getBookReturnByUserId(final Long userId) {
        return operationRepository.getBookReturnByUserId(userId).stream()
                .map(e -> operationMapper.toDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<OperationDTO> getOperationsByBookId(final Long bookId) {
        return operationRepository.getOperationsByBookId(bookId).stream()
                .map(e -> operationMapper.toDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<OperationDTO> getIssuanceOfTheBookByBookId(final Long bookId) {
        return operationRepository.getIssuanceOfTheBookByBookId(bookId).stream()
                .map(e -> operationMapper.toDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<OperationDTO> getBookReturnByBookId(final Long bookId) {
        return operationRepository.getBookReturnByBookId(bookId).stream()
                .map(e -> operationMapper.toDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<OperationDTO> getAllOperations() {
        return operationRepository.getAllOperations().stream()
                .map(e -> operationMapper.toDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<OperationDTO> getAllIssuancesOfTheBook() {
        return operationRepository.getAllIssuancesOfTheBook().stream()
                .map(e -> operationMapper.toDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<OperationDTO> getAllBookReturn() {
        return operationRepository.getAllBookReturn().stream()
                .map(e -> operationMapper.toDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public OperationDTO createOperation(final OperationDTO operationDTO, final Long userId, final Long bookId) {
        if (userId == null) {
            throw new ServiceException(400, "Operation should have user id ", null);
        } else if (bookId == null) {
            throw new ServiceException(400, "Operation should have book id ", null);
        }
        return operationMapper.toDTO(operationRepository.createOperation(operationMapper.toEntity(operationDTO, userId, bookId)));
    }

    @Override
    public OperationDTO updateOperation(final OperationDTO operationDTO, final Long userId, final Long bookId) {
        if (userId == null) {
            throw new ServiceException(400, "Operation should have user id ", null);
        } else if (bookId == null) {
            throw new ServiceException(400, "Operation should have book id ", null);
        }
        return operationMapper.toDTO(operationRepository.updateOperation(operationMapper.toEntity(operationDTO, userId, bookId)));
    }

    @Override
    public void deleteOperationByBookAndUserId(final Long userId, final Long bookId) {
        if (userId == null) {
            throw new ServiceException(400, "Operation should have user id ", null);
        } else if (bookId == null) {
            throw new ServiceException(400, "Operation should have book id ", null);
        }
        operationRepository.deleteOperationByBookAndUserId(userId, bookId);
    }

    @Override
    public void deleteOperationsByUserId(final Long userId) {
        if (userId == null) {
            throw new ServiceException(400, "Operation should have user id ", null);
        }
        operationRepository.deleteOperationsByUserId(userId);
    }

    @Override
    public void deleteOperationsByBookId(final Long bookId) {
        if (bookId == null) {
            throw new ServiceException(400, "Operation should have book id ", null);
        }
        operationRepository.deleteOperationsByBookId(bookId);
    }
}
