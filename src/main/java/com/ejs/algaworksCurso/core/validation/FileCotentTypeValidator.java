package com.ejs.algaworksCurso.core.validation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class FileCotentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile>{

	private List<String> allowedContentType;

	@Override
	public void initialize(FileContentType constraintAnnotation) {
		this.allowedContentType = Arrays.asList(constraintAnnotation.allowed());
	}
	
	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		return value == null || allowedContentType.contains(value.getContentType());
	}
}
