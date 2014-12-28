package com.adv.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.adv.entities.Client;

public class ClientFormValidation implements Validator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	String ID_PATTERN = "[0-9]+";
	String STRING_PATTERN = "[a-zA-Z]+";
	String MOBILE_PATTERN = "^(?=.*?[1-9])[0-9+-]+$";

	@Override
	public void validate(Object target, Errors errors) {

		Client client = (Client) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name",
				"Name cannot be empty.");

		// email validation in spring
		if (!(client.getEmail() != null && client.getEmail().isEmpty())) {
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(client.getEmail());
			if (!matcher.matches()) {
				errors.rejectValue("email", "email.incorrect",
						"Enter a correct email.");
			}
		}

		// First contact number validation
		if (!(client.getContactNo1() != null && client.getContactNo1().isEmpty())) {
			pattern = Pattern.compile(MOBILE_PATTERN);
			matcher = pattern.matcher(client.getContactNo1());
			if (!matcher.matches()) {
				errors.rejectValue("contactNo1", "contactNo1.incorrect",
						"Enter a correct phone number.");
			}
		}
		
		// Second contact number validation
		if (!(client.getContactNo2() != null && client.getContactNo2().isEmpty())) {
			pattern = Pattern.compile(MOBILE_PATTERN);
			matcher = pattern.matcher(client.getContactNo2());
			if (!matcher.matches()) {
				errors.rejectValue("contactNo2", "contactNo2.incorrect",
						"Enter a correct phone number.");
			}
		}
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return false;
	}
}
