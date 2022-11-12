package com.teste.validation;

import static com.teste.utils.Utils.isCpf;
import static java.util.Objects.isNull;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public class CpfValidator implements ConstraintValidator<CpfValid, String> {

	@Override
	public boolean isValid(String cpf, ConstraintValidatorContext context) {

		if (isNull(cpf)) {
			return false;
		}

		return isCpf(cpf);
	}

}