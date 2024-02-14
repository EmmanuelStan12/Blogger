package com.codemage.blogplatform.validators.annotations;

import com.codemage.blogplatform.validators.EmailValidator;
import com.codemage.blogplatform.validators.ImageValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ImageValidator.class)
@Documented
public @interface ValidFile {
    String message() default "File is invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
