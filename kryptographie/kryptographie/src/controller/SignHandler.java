package controller;

import java.awt.event.ActionEvent;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

import model.Signatur;
import view.RSAView;

public class SignHandler {

	RSAView view;

	public SignHandler(RSAView view) {
		super();
		this.view = view;
	}

	public void btn_sign_sign(ActionEvent e) {
		String textToSign = view.getTxt_sign_text().getText();
		BigInteger n = new BigInteger(view.getTxt_sign_n().getText());
		BigInteger d = new BigInteger(view.getTxt_sign_d().getText());
		String hashFunction = (String) view.getCb_sign_hash().getSelectedItem();
		
		try {
			 BigInteger signature = Signatur.signaturErstellenChiffrat(textToSign, d, n, hashFunction);
			 view.getTxt_sign_signature().setText(signature.toString());
			 view.getTxt_verifySig_signature().setText(signature.toString());
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	
}
