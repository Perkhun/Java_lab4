package lpnu.service;

import java.util.List;

public interface AchievementService {
    List<String> getAllAchievementsByUserId(Long userId);
    Integer getAllOperationsQuantityByUserId(Long userId);
    Integer getIssuanceOfTheBookQuantityByUserId(Long userId);
    Integer getBookReturnQuantityByUserId(Long userId);
    Integer getAllOperationsQuantityByBookId(Long bookId);
    Integer getIssuanceOfTheBookQuantityByBookId(Long bookId);
    Integer getBookReturnQuantityByBookId(Long bookId);
    List<String> addAchievementsByUserId(Long userId);
}
