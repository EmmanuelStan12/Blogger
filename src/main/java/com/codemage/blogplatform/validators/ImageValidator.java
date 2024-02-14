package com.codemage.blogplatform.validators;

import com.codemage.blogplatform.validators.annotations.ValidEmail;
import com.codemage.blogplatform.validators.annotations.ValidFile;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageValidator implements ConstraintValidator<ValidFile, MultipartFile> {

    private static final long MAX_FILE_SIZE = 100 * 1024;

    @Override
    public void initialize(ValidFile constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        if (file == null || file.isEmpty()) {
            return false;
        }
        String contentType = file.getContentType();

        boolean isValidContentType = contentType != null && (contentType.equalsIgnoreCase("image/jpeg")
                || contentType.equalsIgnoreCase("image/png")  || contentType.equalsIgnoreCase("image/gif"));
        boolean isValidSize = file.getSize() <= MAX_FILE_SIZE;
        return isValidContentType && isValidSize;
    }
}
