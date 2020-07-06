package at.jku.se.bac.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import at.jku.se.bac.dto.AddUserDto;


public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
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
        if (result)
        	return result;
        else {
            context.disableDefaultConstraintViolation();
        	context.buildConstraintViolationWithTemplate("Passwords do not match!")
        	.addPropertyNode("password")
        	.addConstraintViolation();
        	return result;
        }
        	
    }

}
