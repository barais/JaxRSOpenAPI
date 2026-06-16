package fr.istic.taa.jaxrs.dto;

import jakarta.validation.constraints.NotNull;

public class PersonUpdateDto extends PersonBaseDto{
    @NotNull
    private Long id;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }
}
