package com.etrade.bcts.service;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.etrade.bcts.model.BCTSAlert;

import oracle.sql.converter.I18CharacterConvertersWrapper;

@Service
public class EmailService {
	private static final String EMAIL_BCTS_ALERT_TEMPLATE = "html/bcts-alert";

    private static final String BACKGROUND_IMAGE = "mail/html/images/background.png";
    private static final String LOGO_BACKGROUND_IMAGE = "mail/html/images/logo-background.png";
    private static final String BANNER_IMAGE = "mail/html/images/etrade_logo_small.jpg";
    private static final String LOGO_IMAGE = "mail/html/images/thymeleaf-logo.png";

    private static final String PNG_MIME = "image/png";
    private static final String JPG_MIME = "image/jpg";

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine htmlTemplateEngine;

    public void sendBCTSAlertMail(
            final String recipientName, final String recipientEmail, List<BCTSAlert> cases,
            final Locale locale)
            throws MessagingException {

        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message
                = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
        message.setSubject("BCTS Alert");
        message.setFrom("bctsadmin@crimsonlogic.com");
        message.setTo(recipientEmail);

        // Prepare the evaluation context
        final Context ctx = new Context();
        ctx.setVariable("name", recipientName);
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("cases", cases);

        // Create the HTML body using Thymeleaf
        final String output = htmlTemplateEngine.process(EMAIL_BCTS_ALERT_TEMPLATE, ctx);
        message.setText(output, true /* isHtml */);

        // Add the inline images, referenced from the HTML code as "cid:image-name"
        message.addInline("background", new ClassPathResource(BACKGROUND_IMAGE), PNG_MIME);
        message.addInline("logo-background", new ClassPathResource(LOGO_BACKGROUND_IMAGE), PNG_MIME);
        message.addInline("banner", new ClassPathResource(BANNER_IMAGE), JPG_MIME);
        message.addInline("logo", new ClassPathResource(LOGO_IMAGE), PNG_MIME);

        // Send mail
        this.mailSender.send(mimeMessage);
    }
 }
