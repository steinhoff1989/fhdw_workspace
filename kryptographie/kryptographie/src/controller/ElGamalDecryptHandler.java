package controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.elgamal.CipherList;
import model.elgamal.ElGamalDecrypt;
import model.elgamal.ElGamal_privateKey;
import model.elgamal.ElGamal_publicKey;
import view.RSAView;

public class ElGamalDecryptHandler {

	private final RSAView view;
	private ElGamal_publicKey publicKey;
	private ElGamal_privateKey privateKey;
	private CipherList ciphers;

	public ElGamalDecryptHandler(final RSAView view) {
		super();
		this.view = view;
	}

	public void btn_elGamalDecrypt_selectPrivateKey(final ActionEvent e) {
		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fileChooser.setSelectedFile(new File("private_key.txt"));
		if (fileChooser.showOpenDialog(this.view) == JFileChooser.APPROVE_OPTION) {
			final File file = fileChooser.getSelectedFile();
			this.view.getLbl_elGamalDecrypt_selectPrivateKey().setText(file.getAbsolutePath());
			try {
				this.privateKey = new ElGamal_privateKey(file.getAbsolutePath());

			} catch (final ArrayIndexOutOfBoundsException e1) {
				JOptionPane.showMessageDialog(null, "Something went wrong. The private key is in a wrong format!");
			} catch (final IOException e1) {
				JOptionPane.showMessageDialog(null, "Something went wrong. Maybe you have chosen the wrong file?");
			}
		}
	}

	public void btn_elGamalDecrypt_selectPublicKey(final ActionEvent e) {
		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fileChooser.setSelectedFile(new File("public_key.txt"));
		if (fileChooser.showOpenDialog(this.view) == JFileChooser.APPROVE_OPTION) {
			final File file = fileChooser.getSelectedFile();
			this.view.getLbl_elGamalDecrypt_selectPublicKey().setText(file.getAbsolutePath());
			try {
				this.publicKey = new ElGamal_publicKey(file.getAbsolutePath());

			} catch (final ArrayIndexOutOfBoundsException e1) {
				JOptionPane.showMessageDialog(null, "Something went wrong. The public key is in a wrong format!");
			} catch (final IOException e1) {
				JOptionPane.showMessageDialog(null, "Something went wrong. Maybe you have chosen the wrong file?");
			}
		}
	}

	public void btn_elGamalDecrypt_selectCipherList(final ActionEvent e) {
		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fileChooser.setSelectedFile(new File("ciphers.txt"));
		if (fileChooser.showOpenDialog(this.view) == JFileChooser.APPROVE_OPTION) {
			final File file = fileChooser.getSelectedFile();
			this.view.getLbl_elGamalDecrypt_selectCipherList().setText(file.getAbsolutePath());
			try {
				this.ciphers = new CipherList(file.getAbsolutePath());

			} catch (final ArrayIndexOutOfBoundsException e1) {
				JOptionPane.showMessageDialog(null, "Something went wrong. The cipherlist is in a wrong format!");
			} catch (final IOException e1) {
				JOptionPane.showMessageDialog(null, "Something went wrong. Maybe you have chosen the wrong file?");
			}
		}
	}

	public void btn_elGamalDecrypt_decrypt(final ActionEvent e) {
		if (this.privateKey == null) {
			JOptionPane.showMessageDialog(null, "No private key selected!");
			return;
		}
		if (this.publicKey == null) {
			JOptionPane.showMessageDialog(null, "No public key selected!");
			return;
		}
		if (this.ciphers == null) {
			JOptionPane.showMessageDialog(null, "No ciphers selected!");
			return;
		}
		
		final ElGamalDecrypt elGamalDecrypt = new ElGamalDecrypt(this.privateKey, this.publicKey, this.ciphers);
		final String decryptedText = elGamalDecrypt.decrypt();
		
		this.view.getTxt_elGamalDecrypt_decryptedText().setText(decryptedText);
		
	}
}
