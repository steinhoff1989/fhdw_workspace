package controller;

import java.awt.event.ActionEvent;

import view.RSAView;

public class RSAController {

	private final RSAView view;
	private final EncryptionHandler encryptionHandler;
	private final DecryptionHandler decryptionHandler;
	private CalculateKeysWorker keysWorker;
	private final SignHandler signHandler;
	private final VerifyHandler verifyHandler;
	private final ElGamalKeysHandler elGamalKeysHandler;
	private final ElGamalEncryptHandler elGamalEncryptHandler;
		
	public RSAController(final RSAView view) {
		this.view = view;
		this.encryptionHandler = new EncryptionHandler(this.view);
		this.decryptionHandler = new DecryptionHandler(this.view);
		this.signHandler = new SignHandler(view);
		this.verifyHandler = new VerifyHandler(view);
		this.elGamalKeysHandler = new ElGamalKeysHandler(view);
		this.elGamalEncryptHandler = new ElGamalEncryptHandler(view);
	}

	public void btn_primes_empty_clicked(final ActionEvent e) {
	}

	public void btn_keys_calculate(final ActionEvent e) {
		this.keysWorker = new CalculateKeysWorker(this.view);
		this.keysWorker.execute();
	}

	public void btn_encrypt_calculate(final ActionEvent ae, final Boolean specialRSA) {
		this.encryptionHandler.onClick(specialRSA);
	}

	public void btn_decrypt_calculate(final ActionEvent e, final Boolean specialRSA) {
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

	public void btn_sign_sign(final ActionEvent e) {
		this.signHandler.btn_sign_sign(e);
	}

	public void btn_verifySig_verify(final ActionEvent e) {
		this.verifyHandler.btn_verifySig_verify(e);
	}

	public void btn_elGamalKeys_calculate(final ActionEvent e) {
		final ElGamalKeysWorker elGamalKeysWorker = new ElGamalKeysWorker(this.view);
		elGamalKeysWorker.execute();
	}

	public void btn_elGamalKeys_savePrivateKey(final ActionEvent e) {
		this.elGamalKeysHandler.btn_elGamalKeys_savePrivateKey(e);
		
	}

	public void btn_elGamalKeys_savePublicKey(final ActionEvent e) {
		this.elGamalKeysHandler.btn_elGamalKeys_savePublicKey(e);
	}

	public void btn_elGamalEncrypt_selectPublicKeyFile(final ActionEvent e) {
		this.elGamalEncryptHandler.btn_elGamalEncrypt_selectPublicKeyFile(e);
		
	}

	public void btn_elGamalEncrypt_selectTextFile(final ActionEvent e) {
		this.elGamalEncryptHandler.btn_elGamalEncrypt_selectTextFile(e);
	}

	public void btn_elGamalEncrypt_saveCiphers(final ActionEvent e) {
		this.elGamalEncryptHandler.btn_elGamalEncrypt_saveCiphers(e);
	}

	public void btn_elGamalEncrypt_encrypt(final ActionEvent e) {
		this.elGamalEncryptHandler.btn_elGamalEncrypt_encrypt(e);
		
	}
}
