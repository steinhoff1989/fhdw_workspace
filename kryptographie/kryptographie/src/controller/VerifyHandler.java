package controller;

import java.awt.event.ActionEvent;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

import model.Signatur;
import view.RSAView;

public class VerifyHandler {

	public VerifyHandler(RSAView view) {
		super();
		this.view = view;
	}

	RSAView view;

	public void btn_verifySig_verify(ActionEvent event) {
		BigInteger signature = new BigInteger(view.getTxt_verifySig_signature().getText());
		String signedChiffrat = view.getTxt_verifySig_signedChiffrat().getText();
		BigInteger e = new BigInteger(view.getTxt_verifySig_e().getText());
		BigInteger n = new BigInteger(view.getTxt_verifySig_n().getText());
		String hashFunction = (String) view.getCb_verifySig_hashFunction().getSelectedItem();
		
		try {
			boolean verification = Signatur.signaturVerifizierenChiffrat(signedChiffrat, signature, e, n, hashFunction);
			if(verification){
				view.getLbl_verifySig_result().setText("TRUE");
			}else{
				view.getLbl_verifySig_result().setText("FALSE");
			}
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
}
