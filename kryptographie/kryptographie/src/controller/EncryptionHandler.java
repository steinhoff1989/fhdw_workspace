package controller;

import java.math.BigInteger;
import java.util.List;

import model.Encoding;
import view.RSAView;

public class EncryptionHandler {

	private RSAView view;

	public EncryptionHandler(RSAView view) {
		this.view = view;
	}

	public void onClick(Boolean specialRSA) {
		String text = view.getTxt_encrypt_txt().getText();
		BigInteger e = new BigInteger(view.getTxt_encrypt_e().getText());
		BigInteger n = new BigInteger(view.getTxt_encrypt_n().getText());
		String charset1 = (String) view.getCb_encrypt_charset1().getSelectedItem();
		int k1 = Integer.parseInt(view.getTxt_encrypt_k1().getText());
		int l1 = Integer.parseInt(view.getTxt_encrypt_l1().getText());

		String charset2 = (String) view.getCb_encrypt_charset2().getSelectedItem();
		// int k2 =
		// Integer.parseInt(kryptographie.getTxt_encrypt_k2().getText());
		int l2 = Integer.parseInt(view.getTxt_encrypt_l2().getText());

		List<BigInteger> chiffreNumbers = Encoding.encrypt(text, k1, e, n, charset1);

		if (chiffreNumbers.size() > 0) {
			view.getTxt_encrypt_chiffreNumber().setText("" + chiffreNumbers.get(0));
		}

		for (int i = 1; i < chiffreNumbers.size(); i++) {
			String oldText = view.getTxt_encrypt_chiffreNumber().getText();
			view.getTxt_encrypt_chiffreNumber().setText(oldText + "; \n\r" + chiffreNumbers.get(i));
		}

		String encryptedText = "";

		if (specialRSA) {
			encryptedText = Encoding.numbersToTextBlockChiffre(chiffreNumbers, l2, charset2);
		} else {
			encryptedText = Encoding.numbersToTextBlockChiffre(chiffreNumbers, l1, charset1);
		}

		view.getTxt_encrypt_chiffreTxt().setText(encryptedText);
		view.getTxt_decrypt_encryptedTxt().setText(encryptedText);
		view.getTxt_verifySig_signedChiffrat().setText(encryptedText);
		view.getTxt_sign_text().setText(encryptedText);
	}

	public void onCharset1Action() {
		if (!view.getTxt_encrypt_n().getText().equals("")) {
			BigInteger n = new BigInteger(view.getTxt_encrypt_n().getText());
			String charset1 = (String) view.getCb_encrypt_charset1().getSelectedItem();

			view.getTxt_encrypt_k1()
					.setText("" + Encoding.getMaximumK(Encoding.getCharsetRange(charset1), n.toString()));
			view.getTxt_encrypt_l1()
					.setText("" + Encoding.getMinimumL(Encoding.getCharsetRange(charset1), n.toString()));
		}
	}

	public void onCharset2Action() {
		// if n eq "" --> error!
		if (!view.getTxt_encrypt_n().getText().equals("")) {
			BigInteger n = new BigInteger(view.getTxt_encrypt_n().getText());
			String charset2 = (String) view.getCb_encrypt_charset2().getSelectedItem();

			view.getTxt_encrypt_k2()
					.setText("" + Encoding.getMaximumK(Encoding.getCharsetRange(charset2), n.toString()));
			view.getTxt_encrypt_l2()
					.setText("" + Encoding.getMinimumL(Encoding.getCharsetRange(charset2), n.toString()));
			view.getCb_decrypt_charset2().setSelectedItem(charset2);
		}
	}
}
