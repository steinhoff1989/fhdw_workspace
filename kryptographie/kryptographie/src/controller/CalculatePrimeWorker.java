package controller;

import java.math.BigInteger;
import java.util.Objects;

import javax.swing.SwingWorker;

import model.TrustCenter;
import view.RSAView;

public class CalculatePrimeWorker extends SwingWorker<String, Object> {
	private RSAView view;
	private BigInteger prime;
	long elapsedTime, startTime;
	
	public CalculatePrimeWorker(RSAView view) {
		this.view = view;
	}

	@Override
	protected String doInBackground() throws Exception {
		startTime = System.currentTimeMillis();
		Integer bitLength = Integer.parseInt(view.getTxt_primes_bitLength().getText());
		double probability = Double.parseDouble(view.getTxt_primes_probability().getText());
		if (probability > 1) {
			probability = probability / 100;
		}
		view.activateProgressbar();
		prime = model.TrustCenter.getIndustrialPrime(bitLength, probability);
		return "done";
	}

	@Override
	protected void done() {
		elapsedTime = System.currentTimeMillis() - startTime;
		double seconds = elapsedTime / 1000.0;
		// kryptographie.setLbl_primes_time(Objects.toString(Long.divideUnsigned(elapsedTime,
		// 1000), null) + "s");
		view.setLbl_primes_time(Objects.toString(seconds, null) + "s");
		view.deactivateProgressbar();
		view.getTxt_primes_result().setText(prime.toString());
		int tries = TrustCenter.getRunCountIndustryalPrime();
		view.setLbl_primes_tries(Objects.toString(tries));
		TrustCenter.setRunCountIndustryalPrime(0);
		super.done();
	}

}