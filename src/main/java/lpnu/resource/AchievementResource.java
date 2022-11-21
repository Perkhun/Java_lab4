package lpnu.resource;

import lpnu.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AchievementResource {
    @Autowired
    public AchievementService achievementService;

    @GetMapping("/achievements/{id}")
    public List<String> getAllAchievementsByUserId(final @PathVariable Long id) {
        return achievementService.getAllAchievementsByUserId(id);
    }

    @GetMapping("/achievements/all-operations/user/{userId}")
    public Integer getAllOperationsQuantityByUserId(final @PathVariable Long userId) {
        return achievementService.getAllOperationsQuantityByUserId(userId);
    }

    @GetMapping("/achievements/issuance-of-the-book/user/{userId}")
    public Integer getIssuanceOfTheBookQuantityByUserId(final @PathVariable Long userId) {
        return achievementService.getIssuanceOfTheBookQuantityByUserId(userId);
    }

    @GetMapping("/achievements/book-return/user/{userId}")
    public Integer getBookReturnQuantityByUserId(final @PathVariable Long userId) {
        return achievementService.getBookReturnQuantityByUserId(userId);
    }

    @GetMapping("/achievements/all-operations/book/{bookId}")
    public Integer getAllOperationsQuantityByBookId(final @PathVariable Long bookId) {
        return achievementService.getAllOperationsQuantityByBookId(bookId);
    }

    @GetMapping("/achievements/issuance-of-the-book/book/{bookId}")
    public Integer getIssuanceOfTheBookQuantityByBookId(final @PathVariable Long bookId) {
        return achievementService.getIssuanceOfTheBookQuantityByBookId(bookId);
    }

    @GetMapping("/achievements/book-return/book/{bookId}")
    public Integer getBookReturnQuantityByBookId(final @PathVariable Long bookId) {
        return achievementService.getBookReturnQuantityByBookId(bookId);
    }

    @PostMapping("/achievements/{id}")
    public List<String> addAchievementsByUserId(final @PathVariable Long id) {
        return achievementService.addAchievementsByUserId(id);
    }
}
