package org.brito.pontodigitalbackend.domain;

import jakarta.persistence.*;
import lombok.*;
import org.brito.pontodigitalbackend.domain.user.Usuario;

import java.time.LocalDate;
import java.time.LocalTime;

@Table(name = "funcionario")
@Entity(name = "funcionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    private Usuario user;
    private String nome;
    private String endereco;
    private String email;
    private String cargo;
    private String sexo;
    private String telefone;
    private LocalDate dataNascimento;
    private LocalTime horarioEntrada;
    private LocalTime horarioSaida;
    private boolean primeiroAcesso;

}
