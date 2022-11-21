package lpnu.service.impl;

import lpnu.entity.enumeration.Achievement;
import lpnu.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class AchievementServiceImpl implements AchievementService {
    @Autowired
    @Lazy
    private UserServiceImpl userService;

    @Autowired
    private OperationServiceImpl operationService;

    @PostConstruct
    public void init() {
        savedAchievements = new HashMap<>();
    }

    private Map<Long, List<String>> savedAchievements;

    @Override
    public List<String> getAllAchievementsByUserId(final Long userId) {
        return savedAchievements.get(userId);
    }

    @Override
    public Integer getAllOperationsQuantityByUserId(final Long userId) {
        return operationService.getOperationsByUserId(userId).size();
    }

    @Override
    public Integer getIssuanceOfTheBookQuantityByUserId(final Long userId) {
        return operationService.getIssuanceOfTheBookByUserId(userId).size();
    }

    @Override
    public Integer getBookReturnQuantityByUserId(final Long userId) {
        return operationService.getBookReturnByUserId(userId).size();
    }

    @Override
    public Integer getAllOperationsQuantityByBookId(final Long bookId) {
        return operationService.getOperationsByBookId(bookId).size();
    }

    @Override
    public Integer getIssuanceOfTheBookQuantityByBookId(final Long bookId) {
        return operationService.getIssuanceOfTheBookByBookId(bookId).size();
    }

    @Override
    public Integer getBookReturnQuantityByBookId(final Long bookId) {
        return operationService.getBookReturnByBookId(bookId).size();
    }

    @Override
    public List<String> addAchievementsByUserId(final Long userId) {
        userService.getUserById(userId);

        if (!savedAchievements.containsKey(userId)) {
            savedAchievements.put(userId, new ArrayList<>(Collections.singletonList(Achievement.STRANGER.toString())));
        }

        if (getBookReturnQuantityByUserId(userId) >= 1 &&
        !savedAchievements.get(userId).contains(Achievement.BEGINNER.toString())) {
            savedAchievements.get(userId).add(Achievement.BEGINNER.toString());
        }

        if (getBookReturnQuantityByUserId(userId) >= 3 &&
                !savedAchievements.get(userId).contains(Achievement.MIDDLE.toString())) {
            savedAchievements.get(userId).add(Achievement.MIDDLE.toString());
        }

        if (getBookReturnQuantityByUserId(userId) >= 7 &&
                !savedAchievements.get(userId).contains(Achievement.EXPERT.toString())) {
            savedAchievements.get(userId).add(Achievement.EXPERT.toString());
        }

        if (getBookReturnQuantityByUserId(userId) >= 10 &&
                !savedAchievements.get(userId).contains(Achievement.KING.toString())) {
            savedAchievements.get(userId).add(Achievement.KING.toString());
        }

        savedAchievements.replace(userId, savedAchievements.get(userId));

        return savedAchievements.get(userId);
    }
}
