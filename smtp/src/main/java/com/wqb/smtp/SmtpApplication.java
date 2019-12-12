package com.wqb.smtp;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmtpApplication {

    public static void main(String[] args) {

        Email email = EmailBuilder.startingBlank()
                .from("3444869367@qq.com")
                .to("wangqiben@supcon.com")
                .withSubject("邮箱绑定验证")
                .withPlainText("验证码：9876541").buildEmail();


        Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.qq.com", 587, "3444869367@qq.com", "fqogdbyhtrhhcife")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withSessionTimeout(10 * 1000)
                .clearEmailAddressCriteria() // turns off email validation
                .withDebugLogging(true)
                .buildMailer();

        mailer.sendMail(email);

//        Email email2 = EmailBuilder.startingBlank()
//                .from("3444869367@qq.com")
//                .to("wangqiben@supcon.com")
//                .withSubject("邮箱绑定验证")
//                .withPlainText("验证码：123456").buildEmail();
//        mailer.sendMail(email2);


        SpringApplication.run(SmtpApplication.class, args);
    }

}
