package com.etrade.bcts.service;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.etrade.bcts.model.BCTSAlert;
import com.etrade.bcts.model.PermitCondition;
import com.etrade.bcts.util.BctsConstants;

@Service
public class EmailService {
	private static final String EMAIL_BCTS_ALERT_TEMPLATE = "html/bcts-alert";

    private static final String BACKGROUND_IMAGE = "mail/html/images/background.png";
    private static final String LOGO_BACKGROUND_IMAGE = "mail/html/images/logo-background.png";
    private static final String BANNER_IMAGE = "mail/html/images/etrade_logo_small.jpg";
    private static final String LOGO_IMAGE = "mail/html/images/gets-logo.jpg";

    private static final String PNG_MIME = "image/png";
    private static final String JPG_MIME = "image/jpg";

    private static final String EMAIL_SUBJECT = "BCTS Alert";
    private static final String RECIPIENT_NAME = "Valued Customer";
    
    private static final String BCTS_DOMAIN = "http://wwww.vietnamnet.vn";   
    private static final String BCTS_DOMAIN2 = "http://localhost:8082";   
    	
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine htmlTemplateEngine;

    public void sendBCTSAlertMail(BCTSAlert bctsAlert,
            final Locale locale)
            throws MessagingException {

        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message
                = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
        message.setSubject(EMAIL_SUBJECT);
        message.setTo(bctsAlert.getEmailRecipients());

        // Prepare the evaluation context
        final Context ctx = new Context();
        ctx.setVariable("domainName", BCTS_DOMAIN);
        ctx.setVariable("name", RECIPIENT_NAME);
        ctx.setVariable("case", bctsAlert);
        if(bctsAlert.getCategory().equals(BctsConstants.CASETYPE_PERMIT_CONDITION)) {
        	ctx.setVariable("pc", new PermitCondition(bctsAlert));
        }
        ctx.setVariable("domainName2", BCTS_DOMAIN2);

        // Create the HTML body using Thymeleaf
        final String output = htmlTemplateEngine.process(EMAIL_BCTS_ALERT_TEMPLATE, ctx);
        message.setText(output, true /* isHtml */);

        // Add the inline images, referenced from the HTML code as "cid:image-name"
        message.addInline("background", new ClassPathResource(BACKGROUND_IMAGE), PNG_MIME);
        message.addInline("logo-background", new ClassPathResource(LOGO_BACKGROUND_IMAGE), PNG_MIME);
        message.addInline("banner", new ClassPathResource(BANNER_IMAGE), JPG_MIME);
        message.addInline("logo", new ClassPathResource(LOGO_IMAGE), JPG_MIME);

        // Send mail
        this.mailSender.send(mimeMessage);
    }
 }
