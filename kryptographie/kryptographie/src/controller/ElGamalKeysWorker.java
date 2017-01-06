package controller;

import java.util.concurrent.TimeUnit;

import javax.swing.SwingWorker;

import model.elgamal.EC_Ypower2EqualsXpower3MinusX;
import model.elgamal.ElGamal;
import view.RSAView;

public class ElGamalKeysWorker extends SwingWorker<String, Object>{

	private final RSAView view;
	private ElGamal elGamal;

	public ElGamalKeysWorker(final RSAView view) {
		this.view = view;
	}

	@Override
	protected String doInBackground() throws Exception {
		this.view.activateProgressbar();
		
		final int binaryLength = Integer.parseInt(this.view.getTxt_elGamalKeys_binaryLength().getText());
		double probabilityPerPrime = Double.parseDouble(this.view.getTxt_elGamalKeys_probability().getText());

		if (probabilityPerPrime > 1) {
			probabilityPerPrime = probabilityPerPrime / 100;
		}
		
		this.elGamal = new ElGamal(binaryLength, probabilityPerPrime);
		
		
		return "done";
	}
	
	@Override
	protected void done() {
		this.view.deactivateProgressbar();
		
		this.view.getTxt_elGamalKeys_p().setText(this.elGamal.getPublicKey().getP().toString());
		this.view.getTxt_elGamalKeys_q().setText(this.elGamal.getPublicKey().getQ().toString());
		this.view.getTxt_elGamalKeys_gx().setText(this.elGamal.getPublicKey().getG().getX().toString());
		this.view.getTxt_elGamalKeys_gy().setText(this.elGamal.getPublicKey().getG().getY().toString());
		this.view.getTxt_elGamalKeys_yx().setText(this.elGamal.getPublicKey().getY().getX().toString());
		this.view.getTxt_elGamalKeys_yy().setText(this.elGamal.getPublicKey().getY().getY().toString());
		this.view.getTxt_elGamalKeys_x().setText(this.elGamal.getPrivateKey().getX().toString());
		final EC_Ypower2EqualsXpower3MinusX ec = this.elGamal.getEllipticCurve();
		final long differenceInSeconds = TimeUnit.MILLISECONDS.toSeconds(ec.getEndTimePrimeFound().getTime() - ec.getStartTimeToFindPrime().getTime());
		this.view.getLbl_elGamalKeys_timeToGenerateKeys().setText(String.valueOf(differenceInSeconds));
		this.view.getLbl_elGamalKeys_countOfTriedRandomNumbers().setText(ec.getCountOfTriedRandomNumbers().toString());
		this.view.getLbl_elGamalKeys_foundPrimesNotRightOrder().setText(ec.getCountOfPrimesFoundWhereNDiv8WasNotAPrime().toString());
		this.view.getLbl_elGamalKeys_ordGEqNdiv8().setText(String.valueOf(ec.isGeneratingElementOfSubgroupHIsOrderOfQ()));
		this.view.getLbl_elGamalKeys_Ndiv8IsPrime().setText(String.valueOf(ec.isPrimeQ()));
		super.done();
	}
}
