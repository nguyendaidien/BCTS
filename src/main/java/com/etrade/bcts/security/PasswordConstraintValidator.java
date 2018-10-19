/**
 * NAME		:PasswordConstraintValidator.java
 * DATE		:11/10/2018
 * AUTHOR	:Ajaya Samanta
 * 
 * 
 * Modified By			Modified On				Reason
 * -----------			------------			--------------
 * 
 */
package com.etrade.bcts.security;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.AlphabeticalSequenceRule;
import org.passay.DigitCharacterRule;
import org.passay.LengthRule;
import org.passay.NumericalSequenceRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.QwertySequenceRule;
import org.passay.RuleResult;
import org.passay.SpecialCharacterRule;
import org.passay.UppercaseCharacterRule;
import org.passay.WhitespaceRule;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
	 
	
	@Override
    public void initialize(ValidPassword arg0) {
    }
 
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
           new LengthRule(8, 30), 
           new UppercaseCharacterRule(1), 
           new DigitCharacterRule(1), 
           new SpecialCharacterRule(1), 
           new NumericalSequenceRule(3,false), 
           new AlphabeticalSequenceRule(3,false), 
           new QwertySequenceRule(3,false),
           new WhitespaceRule()));
 
        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        /*context.buildConstraintViolationWithTemplate(
          Joiner.on(",").join(validator.getMessages(result)))
          .addConstraintViolation();*/
        List<String> lst=validator.getMessages(result);
        StringBuffer errors=new StringBuffer();
        if(null!=lst) {
        	for(String error:lst) {
        		errors.append(error).append(",");
        	}
        	if(errors.length()>0) {
        		String err=errors.substring(0,errors.length()-1);
        		System.out.println(err);
        		errors=new StringBuffer(err);
        	}
        }
        context.buildConstraintViolationWithTemplate(errors.toString()).addConstraintViolation();
        return false;
    }
}
