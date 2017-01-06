package model.elgamal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

public class ElGamal_privateKey {

	private BigInteger x;

	public ElGamal_privateKey(final BigInteger x) {
		super();
		this.x = x;
	}
	
	public ElGamal_privateKey(final String pathToFile) throws IOException {
		super();
		this.readFromFile(pathToFile);
	}

	public BigInteger getX() {
		return this.x;
	}
	
	public void saveToFile(final String pathToFile, final boolean clearFile) throws IOException{
		final FileOutputStream fos = new FileOutputStream(pathToFile, clearFile);
		
		final byte[] array = this.x.toByteArray();
		fos.write(array.length);
		fos.write(array);
		fos.close();
	}
	
	private void readFromFile(final String pathToFile) throws IOException{
		final FileInputStream fis = new FileInputStream(pathToFile);
		final byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		
		final int byteLengthOfX = buffer[0];
		final byte[] xByteArray = new byte[byteLengthOfX];
		System.arraycopy(buffer, 1, xByteArray, 0, byteLengthOfX);
		this.x = new BigInteger(xByteArray);
	}
}
