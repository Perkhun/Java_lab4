package lpnu.dto;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class UserDTO {
    private Long id;
    @NotNull
    @NotBlank
    @Size(min = 3, max = 30)
    private String fullname;
    @NotNull
    @Past
    private LocalDate birth;
    @NotNull
    private String phoneNumber;
    @Email
    private String email;

    public UserDTO() {

    }

    public UserDTO(final Long id, final String fullname, final LocalDate birth, final String phoneNumber, final String email) {
        this.id = id;
        this.fullname = fullname;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.email = email;
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
}
