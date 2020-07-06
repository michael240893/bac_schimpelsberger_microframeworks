package bac.schimpelsberger.micronaut.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Singleton;

import io.micronaut.core.annotation.AnnotationValue;
import io.micronaut.validation.validator.constraints.ConstraintValidator;
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext;

@Singleton
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public void initialize(final ValidEmail constraintAnnotation) {
    }


    private boolean validateEmail(final String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

	@Override
	public boolean isValid(String value, AnnotationValue<ValidEmail> annotationMetadata,
			ConstraintValidatorContext context) {
		return (validateEmail(value));
	}
}
