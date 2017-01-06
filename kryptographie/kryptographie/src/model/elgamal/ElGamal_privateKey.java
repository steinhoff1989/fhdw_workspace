package model.elgamal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

public class ElGamal_privateKey {

	private final BigInteger x;

	/**
	 * Creates a ElGamal_privateKey object with the given parameter <x>
	 * @param x
	 */
	public ElGamal_privateKey(final BigInteger x) {
		super();
		this.x = x;
	}
	
	/**
	 * Generates a ElGamal_privateKey object from a file, 
	 * where the given file as to be well structured
	 * 
	 * A valid structure of a ElGamal_privateKey object
	 * privateKey=x
	 * looks like this:
	 * length of x
	 * data of x
	 * 
	 * This method is the reverse of the method saveToFile which will generate
	 * the above defined structure and saves it to a file.
	 * @param pathToFile: The path to the file which will be used to generate
	 * the ElGamal_privateKey object.
	 * @throws IOException
	 * @throws ArrayIndexOutOfBoundsException: will get thrown if the file has 
	 * less bytes than it need to generate the ElGamal_privateKey object 
	 * » wrong file / wrong file structure?
	 */
	public ElGamal_privateKey(final String pathToFile) throws IOException, ArrayIndexOutOfBoundsException {
		super();
		final FileInputStream fis = new FileInputStream(pathToFile);
		final byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		
		final int byteLengthOfX = buffer[0];
		final byte[] xByteArray = new byte[byteLengthOfX];
		System.arraycopy(buffer, 1, xByteArray, 0, byteLengthOfX);
		this.x = new BigInteger(xByteArray);
	}
	
	/**
	 * Creates a well structured file that contains this ElGamal_privateKey object
	 * 
	 * The generated structured file of a ElGamal_privateKey object
	 * privateKey=x
	 * looks like this:
	 * length of x
	 * data of x
	 * 
	 * This method is the complement of the constructor that will read a file
	 * to generate a ElGamal_privateKey object. You can use this function to store a 
	 * ElGamal_privateKey object to a file and use the constructor to regenerate 
	 * the ElGamal_privateKey object at a later time.
	 * @param pathToOutputFile: The path to the output file
	 * @throws IOException
	 */
	public void saveToFile(final String pathToFile, final boolean clearFile) throws IOException{
		final FileOutputStream fos = new FileOutputStream(pathToFile, clearFile);
		
		final byte[] array = this.x.toByteArray();
		fos.write(array.length);
		fos.write(array);
		fos.close();
	}
	
	public BigInteger getX() {
		return this.x;
	}
}
