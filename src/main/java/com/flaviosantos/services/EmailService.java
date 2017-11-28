package com.flaviosantos.services;

import org.springframework.mail.SimpleMailMessage;

import com.flaviosantos.domain.Cliente;
import com.flaviosantos.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);

	void sendNewPasswordEmail(Cliente cliente, String newPass);
}
