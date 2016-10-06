package controller;

import java.awt.event.ActionEvent;

import view.RSAView;

public class RSAController {

	private RSAView view;
	private EncryptionHandler encryptionHandler;
	private DecryptionHandler decryptionHandler;
	private CalculatePrimeWorker primeWorker;
	private CalculateKeysWorker keysWorker;
	private SignHandler signHandler;
	private VerifyHandler verifyHandler;
		
	public RSAController(RSAView view) {
		this.view = view;
		this.encryptionHandler = new EncryptionHandler(this.view);
		this.decryptionHandler = new DecryptionHandler(this.view);
		this.signHandler = new SignHandler(view);
		this.verifyHandler = new VerifyHandler(view);
	}

	public void btn_primes_empty_clicked(ActionEvent e) {
	}

	public void btn_primes_calculate_clicked(ActionEvent e) {
		this.primeWorker = new CalculatePrimeWorker(this.view);
		this.primeWorker.execute();
	}	

	public void btn_keys_calculate(ActionEvent e) {
		this.keysWorker = new CalculateKeysWorker(this.view);
		this.keysWorker.execute();
	}

	public void btn_encrypt_calculate(ActionEvent ae, Boolean specialRSA) {
		this.encryptionHandler.onClick(specialRSA);
	}

	public void btn_decrypt_calculate(ActionEvent e, Boolean specialRSA) {
		this.decryptionHandler.onClick(specialRSA);
	}

	public void cb_encrypt_charset2_action() {
		this.encryptionHandler.onCharset2Action();
	}

	public void cb_encrypt_charset1_action() {
		this.encryptionHandler.onCharset1Action();
	}

	public void cb_decrypt_charset1_action() {
		this.decryptionHandler.onCharset1Action();
	}

	public void cb_decrypt_charset2_action() {
		this.decryptionHandler.onCharset2Action();
	}

	public void cb_sign_hash_action() {
		// TODO Auto-generated method stub
		
	}

	public void btn_sign_sign(ActionEvent e) {
		this.signHandler.btn_sign_sign(e);
	}

	public void btn_verifySig_verify(ActionEvent e) {
		this.verifyHandler.btn_verifySig_verify(e);
	}
}
