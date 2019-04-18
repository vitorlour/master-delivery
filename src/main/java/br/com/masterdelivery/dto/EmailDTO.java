package br.com.masterdelivery.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Por favor preencher o E-mail !")
	@Email(message = "E-mail precisa ser valido !")
	private String email;
}
