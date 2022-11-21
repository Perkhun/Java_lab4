package lpnu.repository;

import lpnu.entity.User;
import lpnu.exception.ServiceException;
import lpnu.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    @Lazy
    private AchievementService achievementService;

    @PostConstruct
    public void init() {
        savedUsers = new ArrayList<>();
    }

    private static Long lastId = 0L;
    private List<User> savedUsers;

    public User getUserById(final Long id) {
        return savedUsers.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "User with id: " + id + " not found ", null));
    }

    public List<User> getAllUsers() {
        return savedUsers;
    }

    public User createUser(final User user) {
        if (user.getId() != null) {
            throw new ServiceException(400, "User should not have an id ", null);
        } else if (savedUsers.stream()
                .filter(e -> e.getFullname().equals(user.getFullname()))
                .findFirst()
                .orElse(null) != null) {
            throw new ServiceException(400, "User with fullname " + user.getFullname() + " already exists ", null);
        } else if (user.getFullname() == null) {
            throw new ServiceException(400, "Fullname must not be empty", null);
        } else if (user.getBirth() == null) {
            throw new ServiceException(400, "Birth must not be empty", null);
        } else if (user.getPhoneNumber() == null) {
            throw new ServiceException(400, "Phone number must not be empty", null);
        } else if (savedUsers.stream()
                .filter(e -> e.getEmail().equals(user.getEmail()))
                .findFirst()
                .orElse(null) != null) {
            throw new ServiceException(400, "User with email " + user.getEmail() + " already exists ", null);
        } else if (user.getEmail() == null) {
            throw new ServiceException(400, "Email must not be empty", null);
        }

        ++lastId;
        user.setId(lastId);

        savedUsers.add(user);

        return user;
    }

    public User updateUser(final User user) {
        if (user.getId() == null) {
            throw new ServiceException(400, "User should have an id ", null);
        } else if (savedUsers.stream()
                .filter(e -> e.getFullname().equals(user.getFullname()) && !e.getId().equals(user.getId()))
                .findFirst()
                .orElse(null) != null) {
            throw new ServiceException(400, "User with fullname " + user.getFullname() + " already exists ", null);
        } else if (user.getFullname() == null) {
            throw new ServiceException(400, "Fullname must not be empty ", null);
        } else if (user.getBirth() == null) {
            throw new ServiceException(400, "Birth must not be empty ", null);
        } else if (user.getPhoneNumber() == null) {
            throw new ServiceException(400, "Phone number must not be empty ", null);
        } else if (savedUsers.stream()
                .filter(e -> e.getEmail().equals(user.getEmail()))
                .findFirst()
                .orElse(null) != null) {
            throw new ServiceException(400, "User with email " + user.getEmail() + " already exists ", null);
        } else if (user.getEmail() == null) {
            throw new ServiceException(400, "Email must not be empty ", null);
        }

        final User savedUser = getUserById(user.getId());

        savedUser.setFullname(user.getFullname());
        savedUser.setBirth(user.getBirth());
        savedUser.setPhoneNumber(user.getPhoneNumber());
        savedUser.setEmail(user.getEmail());
        savedUser.setOperations(user.getOperations());

        savedUsers.stream()
                .filter(e -> e.getId().equals(user.getId()))
                .forEach(e -> e = savedUser);

        return savedUser;
    }

    public void deleteUserById(final Long id) {
        if (id == null) {
            throw new ServiceException(400, "Id is not specified", null);
        }

        savedUsers.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "User with id: " + id + " not found ", null));

        savedUsers = savedUsers.stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList());

        if (achievementService.getAllOperationsQuantityByUserId(id) != 0) {
            operationRepository.deleteOperationsByUserId(id);
        }
    }
}
