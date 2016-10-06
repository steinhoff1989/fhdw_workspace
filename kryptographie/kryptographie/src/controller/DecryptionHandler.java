package controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import model.Encoding;
import view.RSAView;

public class DecryptionHandler {

	private RSAView view;
	public DecryptionHandler(RSAView view) {
		this.view = view;
	}
	
	public void onClick(Boolean specialRSA) {
		String text = view.getTxt_decrypt_encryptedTxt().getText();
		BigInteger d = new BigInteger(view.getTxt_decrypt_d().getText());
		BigInteger n = new BigInteger(view.getTxt_decrypt_n().getText());
		String charset1 = (String) view.getCb_decrypt_charset().getSelectedItem();
		int k1 = Integer.parseInt(view.getTxt_decrypt_blockSize().getText());
		int l1 = Integer.parseInt(view.getTxt_decrypt_l().getText());

		String charset2 = (String) view.getCb_decrypt_charset2().getSelectedItem();
//		int k2 = Integer.parseInt(view.getTxt_decrypt_k2().getText());
		int l2 = Integer.parseInt(view.getTxt_decrypt_l2().getText());

		view.getTxt_decrypt_decryptedNumbers().setText("");

		List<BigInteger> encryptedNumbers = new ArrayList<BigInteger>();

		if (specialRSA) {
			encryptedNumbers = Encoding.textToNumbersBlockChiffre(text, l2, charset2);
		} else {
			encryptedNumbers = Encoding.textToNumbersBlockChiffre(text, l1, charset1);
		}

		String decryptedText = "";

		decryptedText = Encoding.decrypt(encryptedNumbers, k1, d, n, charset1);

		for (int i = 0; i < encryptedNumbers.size(); i++) {
			view.getTxt_decrypt_decryptedNumbers()
					.setText(view.getTxt_decrypt_decryptedNumbers().getText() + "\n"
							+ encryptedNumbers.get(i).toString());
		}

		view.getTxt_decrypt_decryptedTxt().setText(decryptedText);

	}
	
	public void onCharset1Action() {
		if (!view.getTxt_decrypt_n().getText().equals("")) {
			BigInteger n = new BigInteger(view.getTxt_decrypt_n().getText());
			String charset1 = (String) view.getCb_decrypt_charset1().getSelectedItem();

			view.getTxt_decrypt_k1()
					.setText("" + Encoding.getMaximumK(Encoding.getCharsetRange(charset1), n.toString()));
			view.getTxt_decrypt_l1()
					.setText("" + Encoding.getMinimumL(Encoding.getCharsetRange(charset1), n.toString()));
		}
	}
	
	public void onCharset2Action() {
		if (!view.getTxt_decrypt_n().getText().equals("")) {
			BigInteger n = new BigInteger(view.getTxt_decrypt_n().getText());
			String charset2 = (String) view.getCb_decrypt_charset2().getSelectedItem();

			view.getTxt_decrypt_k2()
					.setText("" + Encoding.getMaximumK(Encoding.getCharsetRange(charset2), n.toString()));
			view.getTxt_decrypt_l2()
					.setText("" + Encoding.getMinimumL(Encoding.getCharsetRange(charset2), n.toString()));
		}
	}
}
