package model.elgamal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

public class ElGamal_publicKey {

	private final BigInteger p;
	private final BigInteger q;
	private final EllipticCurvePoint g;
	private final EllipticCurvePoint y;

	/**
	 * Creates a ElGamal_publicKey object with the given parameters
	 * @param p: the prime number
	 * @param q: the prime number with q=ord(subgroup H)
	 * @param g: generating element of subgroup H
	 * @param y: one element of subgroup H
	 */
	public ElGamal_publicKey(final BigInteger p, final BigInteger q, final EllipticCurvePoint g,
			final EllipticCurvePoint y) {
		super();
		this.p = p;
		this.q = q;
		this.g = g;
		this.y = y;
	}
	
	/**
	 * Generates a ElGamal_publicKey object from a file, where the given file
	 * as to be well structured
	 * 
	 * A valid structure of a ElGamal_publicKey object
	 * publicKey=(p, q, g, y) where g:=(gx, gy) and y:=(yx, yy)
	 * looks like this:
	 * length of p
	 * data of p
	 * length of q
	 * data of q
	 * length of gx
	 * data of gx
	 * length of gy
	 * data of gy
	 * length of yx
	 * data of yx
	 * length of yy
	 * data of yy
	 * 
	 * This method is the reverse of the method saveToFile which will generate
	 * the above defined structure and saves it to a file.
	 * @param pathToFile: The path to the file which will be used to generate
	 * the ElGamal_publicKey object.
	 * @throws IOException
	 * @throws ArrayIndexOutOfBoundsException: will get thrown if the file has 
	 * less bytes than it need to generate the ElGamal_publicKey object 
	 * » wrong file / wrong file structure?
	 */
	public ElGamal_publicKey(final String pathToFile) throws IOException, ArrayIndexOutOfBoundsException {
		super();
		int bytePosition = 0;
		final FileInputStream fin = new FileInputStream(pathToFile);
		final byte[] buffer = new byte[fin.available()];
		fin.read(buffer);
		fin.close();

		final int byteLengthOfP = buffer[bytePosition];
		bytePosition += 1;
		final byte[] pByteArray = new byte[byteLengthOfP];
		System.arraycopy(buffer, bytePosition, pByteArray, 0, byteLengthOfP);
		this.p = new BigInteger(pByteArray);
		bytePosition += byteLengthOfP;

		final int byteLengthOfQ = buffer[bytePosition];
		bytePosition += 1;
		final byte[] qByteArray = new byte[byteLengthOfQ];
		System.arraycopy(buffer, bytePosition, qByteArray, 0, byteLengthOfQ);
		this.q = new BigInteger(qByteArray);
		bytePosition += byteLengthOfQ;

		final int byteLengthOfGX = buffer[bytePosition];
		bytePosition += 1;
		final byte[] gxByteArray = new byte[byteLengthOfGX];
		System.arraycopy(buffer, bytePosition, gxByteArray, 0, byteLengthOfGX);
		final BigInteger gx = new BigInteger(gxByteArray);
		bytePosition += byteLengthOfGX;

		final int byteLengthOfGY = buffer[bytePosition];
		bytePosition += 1;
		final byte[] gyByteArray = new byte[byteLengthOfGY];
		System.arraycopy(buffer, bytePosition, gyByteArray, 0, byteLengthOfGY);
		final BigInteger gy = new BigInteger(gyByteArray);
		bytePosition += byteLengthOfGY;

		this.g = new EllipticCurvePoint(gx, gy);
		
		final int byteLengthOfYX = buffer[bytePosition];
		bytePosition += 1;
		final byte[] yxByteArray = new byte[byteLengthOfYX];
		System.arraycopy(buffer, bytePosition, yxByteArray, 0, byteLengthOfYX);
		final BigInteger yx = new BigInteger(yxByteArray);
		bytePosition += byteLengthOfYX;

		final int byteLengthOfYY = buffer[bytePosition];
		bytePosition += 1;
		final byte[] yyByteArray = new byte[byteLengthOfYY];
		System.arraycopy(buffer, bytePosition, yyByteArray, 0, byteLengthOfYY);
		final BigInteger yy = new BigInteger(yyByteArray);
		bytePosition += byteLengthOfYY;

		this.y = new EllipticCurvePoint(yx, yy);
	}

	/**
	 * Creates a well structured file that contains this ElGamal_publicKey object
	 * 
	 * The generated structured file of a ElGamal_publicKey object
	 * publicKey=(p, q, g, y) where g:=(gx, gy) and y:=(yx, yy)
	 * looks like this:
	 * length of p
	 * data of p
	 * length of q
	 * data of q
	 * length of gx
	 * data of gx
	 * length of gy
	 * data of gy
	 * length of yx
	 * data of yx
	 * length of yy
	 * data of yy
	 * 
	 * This method is the complement of the constructor that will read a file
	 * to generate a ElGamal_publicKey object. You can use this function to store a 
	 * ElGamal_publicKey object to a file and use the constructor to regenerate 
	 * the ElGamal_publicKey object at a later time.
	 * @param pathToOutputFile: The path to the output file
	 * @throws IOException
	 */
	public void saveToFile(final String pathToFile, final boolean clearFile) throws IOException {
		final FileOutputStream fos = new FileOutputStream(pathToFile, clearFile);

		byte[] array = this.p.toByteArray();
		fos.write(array.length);
		fos.write(array);
		
		array = this.q.toByteArray();
		fos.write(array.length);
		fos.write(array);
		
		array = this.g.getX().toByteArray();
		fos.write(array.length);
		fos.write(array);
		
		array = this.g.getY().toByteArray();
		fos.write(array.length);
		fos.write(array);
		
		array = this.y.getX().toByteArray();
		fos.write(array.length);
		fos.write(array);
		
		array = this.y.getY().toByteArray();
		fos.write(array.length);
		fos.write(array);
		
		fos.close();
	}
	

	public BigInteger getP() {
		return this.p;
	}

	public BigInteger getQ() {
		return this.q;
	}

	public EllipticCurvePoint getG() {
		return this.g;
	}

	public EllipticCurvePoint getY() {
		return this.y;
	}
}
