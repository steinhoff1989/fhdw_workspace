package controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.elgamal.CipherList;
import model.elgamal.ElGamalEncrypt;
import model.elgamal.ElGamal_publicKey;
import view.RSAView;

public class ElGamalEncryptHandler {

	private final RSAView view;
	private ElGamal_publicKey publicKey;
	private CipherList cipherList;

	public ElGamalEncryptHandler(final RSAView view) {
		super();
		this.view = view;
	}

	public void btn_elGamalEncrypt_selectPublicKeyFile(final ActionEvent e) {
		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fileChooser.setSelectedFile(new File("public_key.txt"));
		if (fileChooser.showOpenDialog(this.view) == JFileChooser.APPROVE_OPTION) {
			final File file = fileChooser.getSelectedFile();
			this.view.getLbl_elGamalEncrypt_selectPublicKeyFile().setText(file.getAbsolutePath());
			try {
				this.publicKey = new ElGamal_publicKey(file.getAbsolutePath());

			} catch (final ArrayIndexOutOfBoundsException e1) {
				JOptionPane.showMessageDialog(null, "Something went wrong. The public key is in a wrong format!");
			} catch (final IOException e1) {
				JOptionPane.showMessageDialog(null, "Something went wrong. Maybe you have chosen the wrong file?");
			}
		}
	}

	public void btn_elGamalEncrypt_selectTextFile(final ActionEvent e) {
		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fileChooser.setSelectedFile(new File("text.txt"));
		if (fileChooser.showOpenDialog(this.view) == JFileChooser.APPROVE_OPTION) {
			try {
//				final StringBuffer buffer = new StringBuffer();
				final File file = fileChooser.getSelectedFile();
				this.view.getLbl_elGamalEncrypt_selectTextFile().setText(file.getAbsolutePath());
				//				final FileInputStream fis = new FileInputStream(file);
//		        final InputStreamReader isr = new InputStreamReader(fis, "Windows-1252");
//		        final Reader in = new BufferedReader(isr);
//		        int ch;
//		        while ((ch = in.read()) > -1) {
//		            buffer.append((char)ch);
//		        }
//		        in.close();
//		        final String text = buffer.toString();
				
				final FileInputStream fis = new FileInputStream(file);
				final byte[] byteArray = new byte[fis.available()];
				fis.read(byteArray);
				fis.close();
				final String text = new String(byteArray);

				this.view.getTxt_elGamalEncrypt_text().setText(text);
			} catch (final IOException e1) {
				JOptionPane.showMessageDialog(null, "Something went wrong. Maybe you have chosen the wrong file?");
			}
		}

	}

	public void btn_elGamalEncrypt_saveCiphers(final ActionEvent e) {
		

	}

	public void btn_elGamalEncrypt_encrypt(final ActionEvent e) {
		if (this.view.getTxt_elGamalEncrypt_text().getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No text available!");
			return;
		}
		if (this.publicKey == null) {
			JOptionPane.showMessageDialog(null, "No public key available!");
			return;
		}

		final String text = this.view.getTxt_elGamalEncrypt_text().getText();
		final ElGamalEncrypt elGamalEncrypt = new ElGamalEncrypt(text, this.publicKey);
		this.cipherList = elGamalEncrypt.encrypt();

		this.view.getTxt_elGamalEncrypt_ciphers().setText(this.cipherList.toString());
		
		if (this.cipherList == null) {
			JOptionPane.showMessageDialog(null, "The text is not encrypted. Please encrypt first!");
		} else {
			final JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
			fileChooser.setSelectedFile(new File("ciphers.txt"));
			if (fileChooser.showSaveDialog(this.view) == JFileChooser.APPROVE_OPTION) {
				final File file = fileChooser.getSelectedFile();

				try {
					this.cipherList.saveToFile(file.getAbsolutePath(), true);
					JOptionPane.showMessageDialog(null,
							"Encrypted cipherList successfully saved in:\n " + file.getAbsolutePath());
				} catch (final IOException e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong. Maybe a wrong path?");
				}
			}
		}
	}

}
