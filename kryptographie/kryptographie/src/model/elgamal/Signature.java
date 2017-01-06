package model.elgamal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

public class Signature {

	private final BigInteger r;
	private final BigInteger s;
	
	/**
	 * Creates a Signature object with a given <r> and <s>
	 */
	public Signature(final BigInteger r, final BigInteger s) {
		super();
		this.r = r;
		this.s = s;
	}
	
	/**
	 * Generates a Signature object from a file, where the given file
	 * as to be well structured
	 * 
	 * A valid structure of a Signature object
	 * signature=(r, s)
	 * looks like this:
	 * length of r
	 * data of r
	 * length of s
	 * data of s
	 * 
	 * This method is the reverse of the method saveToFile which will generate
	 * the above defined structure and saves it to a file.
	 * @param pathToFile: The path to the file which will be used to generate
	 * the Signature object.
	 * @throws IOException
	 * @throws ArrayIndexOutOfBoundsException: will get thrown if the file has 
	 * less bytes than it need to generate the Signature object 
	 * » wrong file / wrong file structure?
	 */
	public Signature(final String pathToFile) throws IOException, ArrayIndexOutOfBoundsException{
		int bytePosition = 0;
		final FileInputStream fin = new FileInputStream(pathToFile);
		final byte[] buffer = new byte[fin.available()];
		fin.read(buffer);
		fin.close();

		final int byteLengthOfR = buffer[bytePosition];
		bytePosition += 1;
		final byte[] rByteArray = new byte[byteLengthOfR];
		System.arraycopy(buffer, bytePosition, rByteArray, 0, byteLengthOfR);
		this.r = new BigInteger(rByteArray);
		bytePosition += byteLengthOfR;

		final int byteLengthOfS = buffer[bytePosition];
		bytePosition += 1;
		final byte[] sByteArray = new byte[byteLengthOfS];
		System.arraycopy(buffer, bytePosition, sByteArray, 0, byteLengthOfS);
		this.s = new BigInteger(sByteArray);
		bytePosition += byteLengthOfS;
	}
	
	/**
	 * Creates a well structured file that contains this Signature object
	 * 
	 * The generated structured file of a Signature object
	 * signature=(r, s)
	 * looks like this:
	 * length of r
	 * data of r
	 * length of s
	 * data of s
	 * 
	 * This method is the complement of the constructor that will read a file
	 * to generate a Signature object. You can use this function to store a 
	 * Signature object to a file and use the constructor to regenerate 
	 * the Signature object at a later time.
	 * @param pathToOutputFile: The path to the output file
	 * @throws IOException
	 */
	public void saveToFile(final String pathToOutputFile, final boolean clearFile) throws IOException{
		final FileOutputStream fout = new FileOutputStream(pathToOutputFile, !clearFile);

		byte[] array = this.r.toByteArray();
		fout.write(array.length);
		fout.write(array);

		array = this.s.toByteArray();
		fout.write(array.length);
		fout.write(array);

		fout.close();
	}

	@Override
	public String toString() {
		return "("+this.r+","+this.s+")";
	}

	public BigInteger getR() {
		return this.r;
	}
	public BigInteger getS() {
		return this.s;
	}
}
