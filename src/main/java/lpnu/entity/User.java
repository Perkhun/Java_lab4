package lpnu.entity;

import java.time.LocalDate;
import java.util.List;

public class User {
    private Long id;
    private String fullname;
    private LocalDate birth;
    private String phoneNumber;
    private String email;
    private List<Operation> operations;

    public User() {

    }

    public User(final Long id, final String fullname, final LocalDate birth, final String phoneNumber, final String email,
                final List<Operation> operations) {
        this.id = id;
        this.fullname = fullname;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.operations = operations;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(final String fullname) {
        this.fullname = fullname;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(final LocalDate birth) {
        this.birth = birth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(final List<Operation> operations) {
        this.operations = operations;
    }
}
