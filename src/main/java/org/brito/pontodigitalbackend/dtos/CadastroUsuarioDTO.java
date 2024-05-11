package org.brito.pontodigitalbackend.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class CadastroUsuarioDTO {

    @NotBlank
    private String login;
    @NotBlank
    private String nome;
    @NotBlank
    private String endereco;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String cargo;
    @NotBlank
    private String sexo;
    @NotNull
    private LocalDate dataNascimento;
    @NotNull
    private LocalTime horarioEntrada;
    @NotNull
    private LocalTime horarioSaida;
    private String telefone;

}
