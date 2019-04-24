/**
 * 
 */
package br.com.masterdelivery.service.impl;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.masterdelivery.dto.EmailBuilderDTO;

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

	public void enviarEmail(EmailBuilderDTO email) {

		try {
			MimeMessage mail = mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mail);

			helper.setTo(email.getPara());
			helper.setSubject(email.getAssunto());
			helper.setText(String.join(DELIMITER, ABRE_PARAGRAFO, email.getConteudo(), FECHA_PARAGRAFO), true);

			mailSender.send(mail);
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
