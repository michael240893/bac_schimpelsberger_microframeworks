package bac.schimpelsberger.micronaut.validation;


import javax.inject.Singleton;

import bac.schimpelsberger.micronaut.dto.AddUserDto;
import io.micronaut.core.annotation.AnnotationValue;
import io.micronaut.core.util.StringUtils;
import io.micronaut.validation.validator.constraints.ConstraintValidator;
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext;



@Singleton
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        //
    }


	@Override
	public boolean isValid(Object obj, AnnotationValue<PasswordMatches> annotationMetadata,
			ConstraintValidatorContext context) {
        String password="";
        String matchingPassword="";
    	if (obj instanceof AddUserDto) {
        	AddUserDto user=(AddUserDto) obj;
        	password=user.getPassword();
        	matchingPassword=user.getMatchingPassword();
        }

    	boolean result=false;
        if (!StringUtils.isEmpty(password)&&!StringUtils.isEmpty(matchingPassword)) {
        	result= password.equals(matchingPassword);
        }
       return result;
	}

}
