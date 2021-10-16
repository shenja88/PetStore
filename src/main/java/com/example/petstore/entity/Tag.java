package com.example.petstore.entity;

import com.example.petstore.utils.ErrorMessageManager;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class Tag {
    private long id;
    @NotEmpty
    @NotBlank(message = ErrorMessageManager.BLANK_FIELD_ERROR)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return id == tag.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
