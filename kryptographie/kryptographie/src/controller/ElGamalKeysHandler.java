package controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.elgamal.ElGamal_privateKey;
import model.elgamal.ElGamal_publicKey;
import model.elgamal.EllipticCurvePoint;
import view.RSAView;

public class ElGamalKeysHandler {

	RSAView view;

	public ElGamalKeysHandler(final RSAView view) {
		super();
		this.view = view;
	}

	public void btn_elGamalKeys_savePrivateKey(final ActionEvent e) {
		if (this.view.getTxt_elGamalKeys_x().getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No private key available!");
		} else {
			final BigInteger x = new BigInteger(this.view.getTxt_elGamalKeys_x().getText());
			final ElGamal_privateKey privateKey = new ElGamal_privateKey(x);

			final JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
			fileChooser.setSelectedFile(new File("private_key.txt"));
			if (fileChooser.showSaveDialog(this.view) == JFileChooser.APPROVE_OPTION) {
				final File file = fileChooser.getSelectedFile();

				try {
					privateKey.saveToFile(file.getAbsolutePath(), true);
					JOptionPane.showMessageDialog(null, "Private key successfully saved in:\n "+file.getAbsolutePath());
				} catch (final IOException e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong. Maybe a wrong path?");
				}
			}
		}
	}

	public void btn_elGamalKeys_savePublicKey(final ActionEvent e) {
		if (this.view.getTxt_elGamalKeys_p().getText().equals("") ||
				this.view.getTxt_elGamalKeys_q().getText().equals("") ||
				this.view.getTxt_elGamalKeys_gx().getText().equals("") ||
				this.view.getTxt_elGamalKeys_gy().getText().equals("") ||
				this.view.getTxt_elGamalKeys_yx().getText().equals("") ||
				this.view.getTxt_elGamalKeys_yy().getText().equals("")){
			JOptionPane.showMessageDialog(null, "Not all parts of the public key available!");
		} else {
			final BigInteger p = new BigInteger(this.view.getTxt_elGamalKeys_p().getText());
			final BigInteger q = new BigInteger(this.view.getTxt_elGamalKeys_q().getText());
			final BigInteger gx = new BigInteger(this.view.getTxt_elGamalKeys_gx().getText());
			final BigInteger gy = new BigInteger(this.view.getTxt_elGamalKeys_gy().getText());
			final BigInteger yx = new BigInteger(this.view.getTxt_elGamalKeys_yx().getText());
			final BigInteger yy = new BigInteger(this.view.getTxt_elGamalKeys_yy().getText());
			
			final EllipticCurvePoint g = new EllipticCurvePoint(gx, gy);
			final EllipticCurvePoint y = new EllipticCurvePoint(yx, yy);
			
			final ElGamal_publicKey publicKey = new ElGamal_publicKey(p, q, g, y);

			final JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
			fileChooser.setSelectedFile(new File("public_key.txt"));
			if (fileChooser.showSaveDialog(this.view) == JFileChooser.APPROVE_OPTION) {
				final File file = fileChooser.getSelectedFile();

				try {
					publicKey.saveToFile(file.getAbsolutePath(), true);
					JOptionPane.showMessageDialog(null, "Public key successfully saved in:\n "+file.getAbsolutePath());
				} catch (final IOException e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong. Maybe a wrong path?");
				}
			}
		}
	}

}
