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
	private final RSAView view;
	private List<BigInteger> keys;
	long elapsedTime, startTime;
	
	public CalculateKeysWorker(final RSAView view) {
		this.view = view;
	}

	@Override
	protected String doInBackground() throws Exception {
		this.view.activateProgressbar();
		this.startTime = System.currentTimeMillis();
		final int binaryLengthPerPrime = Integer.parseInt(this.view.getTxt_keys_binaryLength().getText());
		double probabilityPerPrime = Double.parseDouble(this.view.getTxt_keys_probability().getText());

		if (probabilityPerPrime > 1) {
			probabilityPerPrime = probabilityPerPrime / 100;
		}

		final String selectedCharset = (String) this.view.getCb_keys_charset().getSelectedItem();
		this.view.getCb_encrypt_charset1().setSelectedItem(selectedCharset);
		this.view.getCb_encrypt_charset2().setSelectedItem(selectedCharset);
		this.view.getCb_decrypt_charset().setSelectedItem(selectedCharset);

		this.keys = TrustCenter.getKeys(binaryLengthPerPrime, probabilityPerPrime);
		return "done";
	}

	@Override
	protected void done() {
		this.view.deactivateProgressbar();
		this.elapsedTime = System.currentTimeMillis() - this.startTime;
		final double seconds = this.elapsedTime / 1000.0;

		this.view.getTxt_keys_p().setText(this.keys.get(0).toString());
		this.view.getTxt_keys_q().setText(this.keys.get(1).toString());
		this.view.getTxt_keys_n().setText(this.keys.get(2).toString());
		this.view.getTxt_keys_e().setText(this.keys.get(3).toString());
		this.view.getTxt_keys_d().setText(this.keys.get(4).toString());
		this.view.getTxt_keys_phiN().setText(this.keys.get(5).toString());

		this.view.getLbl_keys_pNEq().setText(String.valueOf(!this.keys.get(0).equals(this.keys.get(1))));
		this.view.getLbl_keys_gcd().setText(ModArith.euklid(this.keys.get(3), this.keys.get(5)).toString());
		this.view.getLbl_keys_eMulD()
				.setText(this.keys.get(3).multiply(this.keys.get(4)).mod(this.keys.get(5)).toString() + " (mod phi(n))");

		this.view.getLbl_keys_time().setText(Objects.toString(seconds, null) + "s");

		this.view.getTxt_encrypt_e().setText(this.keys.get(3).toString());
		this.view.getTxt_encrypt_n().setText(this.keys.get(2).toString());

		this.view.getTxt_decrypt_d().setText(this.keys.get(4).toString());
		this.view.getTxt_decrypt_n().setText(this.keys.get(2).toString());
		
		this.view.getTxt_sign_d().setText(this.keys.get(4).toString());
		this.view.getTxt_sign_n().setText(this.keys.get(2).toString());
		
		this.view.getTxt_verifySig_e().setText(this.keys.get(3).toString());
		this.view.getTxt_verifySig_n().setText(this.keys.get(2).toString());

		// Charset charset =
		// Charset.availableCharsets().get(kryptographie.getCb_keys_charset().getSelectedItem());

		final int maximumK = Encoding.getMaximumK(
				Encoding.getCharsetRange(this.view.getCb_keys_charset().getSelectedItem().toString()),
				this.keys.get(2).toString());
		final int minimumL = Encoding.getMinimumL(
				Encoding.getCharsetRange(this.view.getCb_keys_charset().getSelectedItem().toString()),
				this.keys.get(2).toString());
		this.view.getLblMaximumK().setText("Maximum k: " + maximumK);
		this.view.getLblMinimumL().setText("Minimum l: " + minimumL);

		this.view.getTxt_encrypt_k1().setText("" + maximumK);
		this.view.getTxt_encrypt_l1().setText("" + minimumL);

		this.view.getTxt_encrypt_k2().setText("" + maximumK);
		this.view.getTxt_encrypt_l2().setText("" + minimumL);
		
		this.view.getTxt_decrypt_blockSize().setText("" + maximumK);
		this.view.getTxt_decrypt_l().setText("" + minimumL);

		this.view.getTxt_decrypt_k2().setText("" + maximumK);
		this.view.getTxt_decrypt_l2().setText("" + minimumL);
		
		// kryptographie.getTxt_decrypt_blockSize().setToolTipText("" +
		// minimumL);

		super.done();
	}

}