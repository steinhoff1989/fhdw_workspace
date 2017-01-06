package model.elgamal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

public class Cipher {

	private final EllipticCurvePoint a;
	private final BigInteger b1;
	private final BigInteger b2;

	/**
	 * Creates a cipher object with the given parameters
	 * @param a: The elliptic curve point object
	 * @param b1: The b1 element of the cipher object
	 * @param b2: The b2 element of the cipher object
	 */
	public Cipher(final EllipticCurvePoint a, final BigInteger b1, final BigInteger b2) {
		this.a = a;
		this.b1 = b1;
		this.b2 = b2;
	}

	/**
	 * Creates a cipher object from a file that includes only a single cipher element. 
	 * @param pathToFileWithSingleChiffratOnly: Path to the file that will be read
	 * @throws IOException
	 */
	public Cipher(final String pathToFileWithSingleChiffratOnly) throws IOException {
		int bytePosition = 0;
		final FileInputStream fin = new FileInputStream(pathToFileWithSingleChiffratOnly);
		final byte[] buffer = new byte[fin.available()];
		fin.read(buffer);
		fin.close();

		final int byteLengthOfAX = buffer[bytePosition];
		bytePosition += 1;
		final byte[] axByteArray = new byte[byteLengthOfAX];
		System.arraycopy(buffer, bytePosition, axByteArray, 0, byteLengthOfAX);
		final BigInteger ax = new BigInteger(axByteArray);
		bytePosition += byteLengthOfAX;

		final int byteLengthOfAY = buffer[bytePosition];
		bytePosition += 1;
		final byte[] ayByteArray = new byte[byteLengthOfAY];
		System.arraycopy(buffer, bytePosition, ayByteArray, 0, byteLengthOfAY);
		final BigInteger ay = new BigInteger(ayByteArray);
		bytePosition += byteLengthOfAY;

		this.a = new EllipticCurvePoint(ax, ay);

		final int byteLengthOfB1 = buffer[bytePosition];
		bytePosition += 1;
		final byte[] b1ByteArray = new byte[byteLengthOfB1];
		System.arraycopy(buffer, bytePosition, b1ByteArray, 0, byteLengthOfB1);
		this.b1 = new BigInteger(b1ByteArray);
		bytePosition += byteLengthOfB1;

		final int byteLengthOfB2 = buffer[bytePosition];
		bytePosition += 1;
		final byte[] b2ByteArray = new byte[byteLengthOfB2];
		System.arraycopy(buffer, bytePosition, b2ByteArray, 0, byteLengthOfB2);
		this.b2 = new BigInteger(b2ByteArray);
		bytePosition += byteLengthOfB2;
	}

	public EllipticCurvePoint getA() {
		return this.a;
	}

	public BigInteger getB1() {
		return this.b1;
	}

	public BigInteger getB2() {
		return this.b2;
	}

	@Override
	public String toString() {
		return "(" + this.a + "," + this.b1 + "," + this.b2 + ")";
	}

	/**
	 * Saves the cipher object to a file. Append the file, if it is not empty.
	 * @param pathToOutputFile: Path to the file that will be appended/generated
	 * @throws IOException
	 */
	public void saveToFile(final String pathToOutputFile) throws IOException {
		final FileOutputStream fout = new FileOutputStream(pathToOutputFile, true);

		byte[] array = this.a.getX().toByteArray();
		fout.write(array.length);
		fout.write(array);

		array = this.a.getY().toByteArray();
		fout.write(array.length);
		fout.write(array);

		array = this.b1.toByteArray();
		fout.write(array.length);
		fout.write(array);

		array = this.b2.toByteArray();
		fout.write(array.length);
		fout.write(array);

		fout.close();
	}
}
