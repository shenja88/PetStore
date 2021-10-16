package com.example.petstore.entity;

import com.example.petstore.utils.ErrorMessageManager;
import com.example.petstore.utils.Patterns;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    @NotBlank(message = ErrorMessageManager.BLANK_FIELD_ERROR)
    @Size(min = 3, max = 30, message = ErrorMessageManager.NAME_USER_ERROR)
    private String userName;
    @NotBlank(message = ErrorMessageManager.BLANK_FIELD_ERROR)
    @Size(min = 3, max = 30, message = ErrorMessageManager.NAME_USER_ERROR)
    private String firstName;
    @NotBlank(message = ErrorMessageManager.BLANK_FIELD_ERROR)
    @Size(min = 3, max = 30, message = ErrorMessageManager.NAME_USER_ERROR)
    private String lastName;
    @NotBlank(message = ErrorMessageManager.BLANK_FIELD_ERROR)
    @Size(min = 6, max = 100,  message = ErrorMessageManager.EMAIL_USER_ERROR)
    @Pattern(regexp = Patterns.EMAIL, message = ErrorMessageManager.EMAIL_USER_ERROR)
    private String email;
    @NotBlank(message = ErrorMessageManager.BLANK_FIELD_ERROR)
    @Pattern(regexp = Patterns.PASSWORD, message = ErrorMessageManager.PASSWORD_USER_ERROR)
    private String password;
    @NotBlank
    @Size(min = 9, max = 12, message = ErrorMessageManager.NAME_USER_ERROR)
    private String telephone;
    private int status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
