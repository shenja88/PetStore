package com.example.petstore.entity;

import com.example.petstore.utils.ErrorMessageManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetCategory {
    private long id;
    @NotEmpty(message = ErrorMessageManager.BLANK_FIELD_ERROR)
    @NotBlank(message = ErrorMessageManager.BLANK_FIELD_ERROR)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetCategory that = (PetCategory) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
