package com.jogogourmet;

import javax.swing.JOptionPane;

public class JogoGourmet {

	public static void main(String[] args) {
		StringBuilder mensagem = new StringBuilder();
		mensagem.append("<html><u>Primeira linha.</u>");
		mensagem.append("<br/>");
		mensagem.append("<i>Primeiro commit.</i></html>");
        JOptionPane.showMessageDialog (null, mensagem);
	}
}
