package controller;

import java.awt.event.ActionEvent;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.SwingWorker;

import model.Functions;
import view.Kryptographie;

public class Controler {

	private Kryptographie kryptographie;

	public Controler(Kryptographie kryptographie) {
		this.kryptographie = kryptographie;
	}

	public void btn_primes_empty_clicked(ActionEvent e) {

	}

	public void btn_primes_calculate_clicked(ActionEvent e) {
		CalculatePrimeWorker primeWorker = new CalculatePrimeWorker();
		primeWorker.execute();
	}

	private class CalculatePrimeWorker extends SwingWorker<String, Object> {
		private BigInteger prime;
		long elapsedTime, startTime;

		@Override
		protected String doInBackground() throws Exception {
			startTime = System.currentTimeMillis();
			Integer bitLength = Integer.parseInt(kryptographie.getTxt_primes_bitLength().getText());
			double probability = Double.parseDouble(kryptographie.getTxt_primes_probability().getText());
			if (probability > 1) {
				probability = probability / 100;
			}
			kryptographie.activateProgressbar();
			prime = model.Functions.getIndustrialPrime(bitLength, probability);
			return "done";
		}

		@Override
		protected void done() {
			elapsedTime = System.currentTimeMillis() - startTime;
			double seconds = elapsedTime / 1000.0;
			// kryptographie.setLbl_primes_time(Objects.toString(Long.divideUnsigned(elapsedTime,
			// 1000), null) + "s");
			kryptographie.setLbl_primes_time(Objects.toString(seconds, null) + "s");
			kryptographie.deactivateProgressbar();
			kryptographie.getTxt_primes_result().setText(prime.toString());
			;
			int tries = model.Functions.getRunCountIndustryalPrime();
			kryptographie.setLbl_primes_tries(Objects.toString(tries));
			model.Functions.setRunCountIndustryalPrime(0);
			super.done();
		}

	}

	public void btn_keys_calculate(ActionEvent e) {
		CalculateKeysWorker keysWorker = new CalculateKeysWorker();
		keysWorker.execute();
	}

	private class CalculateKeysWorker extends SwingWorker<String, Object> {
		private List<BigInteger> keys;
		long elapsedTime, startTime;

		@Override
		protected String doInBackground() throws Exception {
			kryptographie.activateProgressbar();
			startTime = System.currentTimeMillis();
			int binaryLengthPerPrime = Integer.parseInt(kryptographie.getTxt_keys_binaryLength().getText());
			double probabilityPerPrime = Double.parseDouble(kryptographie.getTxt_keys_probability().getText());

			if (probabilityPerPrime > 1) {
				probabilityPerPrime = probabilityPerPrime / 100;
			}

			String selectedCharset = (String) kryptographie.getCb_keys_charset().getSelectedItem();
			kryptographie.getCb_encrypt_charset1().setSelectedItem(selectedCharset);
			kryptographie.getCb_encrypt_charset2().setSelectedItem(selectedCharset);
			kryptographie.getCb_decrypt_charset().setSelectedItem(selectedCharset);

			keys = Functions.getKeys(binaryLengthPerPrime, probabilityPerPrime);
			return "done";
		}

		@Override
		protected void done() {
			kryptographie.deactivateProgressbar();
			elapsedTime = System.currentTimeMillis() - startTime;
			double seconds = elapsedTime / 1000.0;
			kryptographie.setLbl_primes_time(Objects.toString(seconds, null) + "s");

			kryptographie.getTxt_keys_p().setText(keys.get(0).toString());
			kryptographie.getTxt_keys_q().setText(keys.get(1).toString());
			kryptographie.getTxt_keys_n().setText(keys.get(2).toString());
			kryptographie.getTxt_keys_e().setText(keys.get(3).toString());
			kryptographie.getTxt_keys_d().setText(keys.get(4).toString());
			kryptographie.getTxt_keys_phiN().setText(keys.get(5).toString());

			kryptographie.getLbl_keys_pNEq().setText(String.valueOf(!keys.get(0).equals(keys.get(1))));
			kryptographie.getLbl_keys_gcd().setText(Functions.euklid(keys.get(3), keys.get(5)).toString());
			kryptographie.getLbl_keys_eMulD()
					.setText(keys.get(3).multiply(keys.get(4)).mod(keys.get(5)).toString() + " (mod phi(n))");

			kryptographie.getLbl_keys_time().setText(Objects.toString(seconds, null) + "s");

			kryptographie.getTxt_encrypt_e().setText(keys.get(3).toString());
			kryptographie.getTxt_encrypt_n().setText(keys.get(2).toString());

			kryptographie.getTxt_decrypt_d().setText(keys.get(4).toString());
			kryptographie.getTxt_decrypt_n().setText(keys.get(2).toString());

			// Charset charset =
			// Charset.availableCharsets().get(kryptographie.getCb_keys_charset().getSelectedItem());

			int maximumK = Functions.getMaximumK(
					Functions.getCharsetRange(kryptographie.getCb_keys_charset().getSelectedItem().toString()),
					keys.get(2).toString());
			int minimumL = Functions.getMinimumL(
					Functions.getCharsetRange(kryptographie.getCb_keys_charset().getSelectedItem().toString()),
					keys.get(2).toString());
			kryptographie.getLblMaximumK().setText("Maximum k: " + maximumK);
			kryptographie.getLblMinimumL().setText("Minimum l: " + minimumL);

			kryptographie.getTxt_encrypt_k1().setText("" + maximumK);
			kryptographie.getTxt_encrypt_l1().setText("" + minimumL);

			kryptographie.getTxt_encrypt_k2().setText("" + maximumK);
			kryptographie.getTxt_encrypt_l2().setText("" + minimumL);
			
			kryptographie.getTxt_decrypt_blockSize().setText("" + maximumK);
			kryptographie.getTxt_decrypt_l().setText("" + minimumL);

			kryptographie.getTxt_decrypt_k2().setText("" + maximumK);
			kryptographie.getTxt_decrypt_l2().setText("" + minimumL);
			
			// kryptographie.getTxt_decrypt_blockSize().setToolTipText("" +
			// minimumL);

			super.done();
		}

	}

	public void btn_encrypt_calculate(ActionEvent ae, Boolean specialRSA) {
		String text = kryptographie.getTxt_encrypt_txt().getText();
		BigInteger e = new BigInteger(kryptographie.getTxt_encrypt_e().getText());
		BigInteger n = new BigInteger(kryptographie.getTxt_encrypt_n().getText());
		String charset1 = (String) kryptographie.getCb_encrypt_charset1().getSelectedItem();
		int k1 = Integer.parseInt(kryptographie.getTxt_encrypt_k1().getText());
		int l1 = Integer.parseInt(kryptographie.getTxt_encrypt_l1().getText());

		String charset2 = (String) kryptographie.getCb_encrypt_charset2().getSelectedItem();
//		int k2 = Integer.parseInt(kryptographie.getTxt_encrypt_k2().getText());
		int l2 = Integer.parseInt(kryptographie.getTxt_encrypt_l2().getText());

		List<BigInteger> chiffreNumbers = Functions.encrypt(text, k1, e, n, charset1);

		if (chiffreNumbers.size() > 0) {
			kryptographie.getTxt_encrypt_chiffreNumber().setText("" + chiffreNumbers.get(0));
		}

		for (int i = 1; i < chiffreNumbers.size(); i++) {
			String oldText = kryptographie.getTxt_encrypt_chiffreNumber().getText();
			kryptographie.getTxt_encrypt_chiffreNumber().setText(oldText + "; \n\r" + chiffreNumbers.get(i));
		}

		String encryptedText = "";

		if (specialRSA) {
			encryptedText = Functions.numbersToTextBlockChiffre(chiffreNumbers, l2, charset2);
		} else {
			encryptedText = Functions.numbersToTextBlockChiffre(chiffreNumbers, l1, charset1);
		}

		kryptographie.getTxt_encrypt_chiffreTxt().setText(encryptedText);
		kryptographie.getTxt_decrypt_encryptedTxt().setText(encryptedText);
	}

	public void btn_decrypt_calculate(ActionEvent e, Boolean specialRSA) {
		String text = kryptographie.getTxt_decrypt_encryptedTxt().getText();
		BigInteger d = new BigInteger(kryptographie.getTxt_decrypt_d().getText());
		BigInteger n = new BigInteger(kryptographie.getTxt_decrypt_n().getText());
		String charset1 = (String) kryptographie.getCb_decrypt_charset().getSelectedItem();
		int k1 = Integer.parseInt(kryptographie.getTxt_decrypt_blockSize().getText());
		int l1 = Integer.parseInt(kryptographie.getTxt_decrypt_l().getText());

		String charset2 = (String) kryptographie.getCb_decrypt_charset2().getSelectedItem();
//		int k2 = Integer.parseInt(kryptographie.getTxt_decrypt_k2().getText());
		int l2 = Integer.parseInt(kryptographie.getTxt_decrypt_l2().getText());

		kryptographie.getTxt_decrypt_decryptedNumbers().setText("");

		List<BigInteger> encryptedNumbers = new ArrayList<BigInteger>();

		if (specialRSA) {
			encryptedNumbers = Functions.textToNumbersBlockChiffre(text, l2, charset2);
		} else {
			encryptedNumbers = Functions.textToNumbersBlockChiffre(text, l1, charset1);
		}

		String decryptedText = "";

		decryptedText = Functions.decrypt(encryptedNumbers, k1, d, n, charset1);

		for (int i = 0; i < encryptedNumbers.size(); i++) {
			kryptographie.getTxt_decrypt_decryptedNumbers()
					.setText(kryptographie.getTxt_decrypt_decryptedNumbers().getText() + "\n"
							+ encryptedNumbers.get(i).toString());
		}

		kryptographie.getTxt_decrypt_decryptedTxt().setText(decryptedText);

	}

	public void cb_encrypt_charset2_action() {
		// if n eq "" --> error!
		if (!kryptographie.getTxt_encrypt_n().getText().equals("")) {
			BigInteger n = new BigInteger(kryptographie.getTxt_encrypt_n().getText());
			String charset2 = (String) kryptographie.getCb_encrypt_charset2().getSelectedItem();

			kryptographie.getTxt_encrypt_k2()
					.setText("" + Functions.getMaximumK(Functions.getCharsetRange(charset2), n.toString()));
			kryptographie.getTxt_encrypt_l2()
					.setText("" + Functions.getMinimumL(Functions.getCharsetRange(charset2), n.toString()));
			kryptographie.getCb_decrypt_charset2().setSelectedItem(charset2);
		}
	}

	public void cb_encrypt_charset1_action() {
		if (!kryptographie.getTxt_encrypt_n().getText().equals("")) {
			BigInteger n = new BigInteger(kryptographie.getTxt_encrypt_n().getText());
			String charset1 = (String) kryptographie.getCb_encrypt_charset1().getSelectedItem();

			kryptographie.getTxt_encrypt_k1()
					.setText("" + Functions.getMaximumK(Functions.getCharsetRange(charset1), n.toString()));
			kryptographie.getTxt_encrypt_l1()
					.setText("" + Functions.getMinimumL(Functions.getCharsetRange(charset1), n.toString()));
		}
	}

	public void cb_decrypt_charset1_action() {
		if (!kryptographie.getTxt_decrypt_n().getText().equals("")) {
			BigInteger n = new BigInteger(kryptographie.getTxt_decrypt_n().getText());
			String charset1 = (String) kryptographie.getCb_decrypt_charset1().getSelectedItem();

			kryptographie.getTxt_decrypt_k1()
					.setText("" + Functions.getMaximumK(Functions.getCharsetRange(charset1), n.toString()));
			kryptographie.getTxt_decrypt_l1()
					.setText("" + Functions.getMinimumL(Functions.getCharsetRange(charset1), n.toString()));
		}
	}

	public void cb_decrypt_charset2_action() {
		if (!kryptographie.getTxt_decrypt_n().getText().equals("")) {
			BigInteger n = new BigInteger(kryptographie.getTxt_decrypt_n().getText());
			String charset2 = (String) kryptographie.getCb_decrypt_charset2().getSelectedItem();

			kryptographie.getTxt_decrypt_k2()
					.setText("" + Functions.getMaximumK(Functions.getCharsetRange(charset2), n.toString()));
			kryptographie.getTxt_decrypt_l2()
					.setText("" + Functions.getMinimumL(Functions.getCharsetRange(charset2), n.toString()));
		}
	}
}
