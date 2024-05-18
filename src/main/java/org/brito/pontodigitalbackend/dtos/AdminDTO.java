package org.brito.pontodigitalbackend.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDTO {

    @NotBlank
    String password;
}
