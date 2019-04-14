/**
 * 
 */
package br.com.masterdelivery.service.impl;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * @author vitorlour
 *
 */
@Service
public class EmailService {

	private static final String FECHA_PARAGRAFO = "</p>";

	private static final String ABRE_PARAGRAFO = "<p>";

	private static final String DELIMITER = "";

	@Autowired
	private JavaMailSender mailSender;

	public void enviarEmail(String email, String assunto, String texto) {

		try {
			MimeMessage mail = mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mail);

			helper.setTo(email);
			helper.setSubject(assunto);
			helper.setText(String.join(DELIMITER, ABRE_PARAGRAFO, texto, FECHA_PARAGRAFO), true);

			mailSender.send(mail);
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
