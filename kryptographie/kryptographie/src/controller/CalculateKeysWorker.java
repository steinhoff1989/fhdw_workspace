package controller;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

import javax.swing.SwingWorker;

import model.Encoding;
import model.ModArith;
import model.TrustCenter;
import view.RSAView;

public class CalculateKeysWorker extends SwingWorker<String, Object> {
	private RSAView view;
	private List<BigInteger> keys;
	long elapsedTime, startTime;
	
	public CalculateKeysWorker(RSAView view) {
		this.view = view;
	}

	@Override
	protected String doInBackground() throws Exception {
		view.activateProgressbar();
		startTime = System.currentTimeMillis();
		int binaryLengthPerPrime = Integer.parseInt(view.getTxt_keys_binaryLength().getText());
		double probabilityPerPrime = Double.parseDouble(view.getTxt_keys_probability().getText());

		if (probabilityPerPrime > 1) {
			probabilityPerPrime = probabilityPerPrime / 100;
		}

		String selectedCharset = (String) view.getCb_keys_charset().getSelectedItem();
		view.getCb_encrypt_charset1().setSelectedItem(selectedCharset);
		view.getCb_encrypt_charset2().setSelectedItem(selectedCharset);
		view.getCb_decrypt_charset().setSelectedItem(selectedCharset);

		keys = TrustCenter.getKeys(binaryLengthPerPrime, probabilityPerPrime);
		return "done";
	}

	@Override
	protected void done() {
		view.deactivateProgressbar();
		elapsedTime = System.currentTimeMillis() - startTime;
		double seconds = elapsedTime / 1000.0;
		view.setLbl_primes_time(Objects.toString(seconds, null) + "s");

		view.getTxt_keys_p().setText(keys.get(0).toString());
		view.getTxt_keys_q().setText(keys.get(1).toString());
		view.getTxt_keys_n().setText(keys.get(2).toString());
		view.getTxt_keys_e().setText(keys.get(3).toString());
		view.getTxt_keys_d().setText(keys.get(4).toString());
		view.getTxt_keys_phiN().setText(keys.get(5).toString());

		view.getLbl_keys_pNEq().setText(String.valueOf(!keys.get(0).equals(keys.get(1))));
		view.getLbl_keys_gcd().setText(ModArith.euklid(keys.get(3), keys.get(5)).toString());
		view.getLbl_keys_eMulD()
				.setText(keys.get(3).multiply(keys.get(4)).mod(keys.get(5)).toString() + " (mod phi(n))");

		view.getLbl_keys_time().setText(Objects.toString(seconds, null) + "s");

		view.getTxt_encrypt_e().setText(keys.get(3).toString());
		view.getTxt_encrypt_n().setText(keys.get(2).toString());

		view.getTxt_decrypt_d().setText(keys.get(4).toString());
		view.getTxt_decrypt_n().setText(keys.get(2).toString());
		
		view.getTxt_sign_d().setText(keys.get(4).toString());
		view.getTxt_sign_n().setText(keys.get(2).toString());
		
		view.getTxt_verifySig_e().setText(keys.get(3).toString());
		view.getTxt_verifySig_n().setText(keys.get(2).toString());

		// Charset charset =
		// Charset.availableCharsets().get(kryptographie.getCb_keys_charset().getSelectedItem());

		int maximumK = Encoding.getMaximumK(
				Encoding.getCharsetRange(view.getCb_keys_charset().getSelectedItem().toString()),
				keys.get(2).toString());
		int minimumL = Encoding.getMinimumL(
				Encoding.getCharsetRange(view.getCb_keys_charset().getSelectedItem().toString()),
				keys.get(2).toString());
		view.getLblMaximumK().setText("Maximum k: " + maximumK);
		view.getLblMinimumL().setText("Minimum l: " + minimumL);

		view.getTxt_encrypt_k1().setText("" + maximumK);
		view.getTxt_encrypt_l1().setText("" + minimumL);

		view.getTxt_encrypt_k2().setText("" + maximumK);
		view.getTxt_encrypt_l2().setText("" + minimumL);
		
		view.getTxt_decrypt_blockSize().setText("" + maximumK);
		view.getTxt_decrypt_l().setText("" + minimumL);

		view.getTxt_decrypt_k2().setText("" + maximumK);
		view.getTxt_decrypt_l2().setText("" + minimumL);
		
		// kryptographie.getTxt_decrypt_blockSize().setToolTipText("" +
		// minimumL);

		super.done();
	}

}