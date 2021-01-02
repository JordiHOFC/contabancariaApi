package com.contabancaria.PersonAPI.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private Long id;

    @NotBlank(message = "Name should be not blank.")
    private String name;

    @NotBlank(message = "E-mail should be not blank.")
    @Email(message = "Invalid E-mail address")
    private String email;

    @NotBlank(message = "CPF should be not blank.")
    @CPF(message = "CPF not valid")
    private String cpf;

    @NotBlank(message = "Birth date should be not blank.")
    private String birthDate;
}
