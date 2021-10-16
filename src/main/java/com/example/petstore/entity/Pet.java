package com.example.petstore.entity;

import com.example.petstore.utils.ErrorMessageManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private long id;
    @NotEmpty
    @NotBlank(message = ErrorMessageManager.BLANK_FIELD_ERROR)
    private PetCategory petCategory;
    @NotBlank(message = ErrorMessageManager.BLANK_FIELD_ERROR)
    @Size(min = 3, max = 30, message = ErrorMessageManager.NAME_USER_ERROR)
    private String name;
    @NotEmpty
    @NotBlank(message = ErrorMessageManager.BLANK_FIELD_ERROR)
    private List<Tag> tags;
    private PetSaleStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return getId() == pet.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
