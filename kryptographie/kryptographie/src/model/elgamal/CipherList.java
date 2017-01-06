package model.elgamal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CipherList {

	List<Cipher> ciphers;

	/**
	 * Represents the list of given cipher elements
	 * @param cipherList: A List of cipher objects
	 */
	public CipherList(final List<Cipher> cipherList) {
		super();
		this.ciphers = cipherList;
	}

	/**
	 * Generates the cipherList object from a given file that must include 
	 * a well structured list of cipher elements
	 * 
	 * A valid structure of two cipher objects 
	 * c1=(a1, b11, b12) where a1 := (x1, y1)
	 * c2=(a2, b21, b22) where a2 := (x2, y2)
	 * looks like this:
	 * length of x1
	 * data of x1
	 * length of y1
	 * data of y1
	 * length of b11
	 * data of b11
	 * length of b12
	 * data of b12
	 * length of x2
	 * data of x2
	 * length of y2
	 * data of y2
	 * length of b21
	 * data of b21
	 * length of b22
	 * data of b22
	 * 
	 * This method is the reverse of the method saveToFile which will generate
	 * the above defined structure.
	 * 
	 * @param pathToCipherListFile: The path to the file which will be used to generate
	 * the cipherList object.
	 * @throws IOException
	 */
	public CipherList(final String pathToCipherListFile) throws IOException{
		super();
		this.ciphers = new ArrayList<Cipher>();
		this.readFromFile(pathToCipherListFile);
	}
	
	/**
	 * Creates a well structured file that contains all <ciphers>
	 * 
	 * A valid structure of two cipher objects 
	 * c1=(a1, b11, b12) where a1 := (x1, y1)
	 * c2=(a2, b21, b22) where a2 := (x2, y2)
	 * looks like this:
	 * length of x1
	 * data of x1
	 * length of y1
	 * data of y1
	 * length of b11
	 * data of b11
	 * length of b12
	 * data of b12
	 * length of x2
	 * data of x2
	 * length of y2
	 * data of y2
	 * length of b21
	 * data of b21
	 * length of b22
	 * data of b22
	 * 
	 * This method is the complement of the constructor that will read a file
	 * to generate a CipherList object. You can use this function to store a 
	 * cipherList object to a file and use the constructor to regenerate 
	 * the cipherList object at a later time.
	 * @param pathToOutputFile: The path to the output file
	 * @param clearFile: If true, the given file will be cleared if it is not
	 * already empty.
	 * @throws IOException
	 */
	public void saveToFile(final String pathToOutputFile, final boolean clearFile) throws IOException {
		if (clearFile) {
			final FileOutputStream fos = new FileOutputStream(pathToOutputFile, false);
			fos.write("".getBytes());
			fos.close();
		}
		for (int i = 0; i < this.ciphers.size(); i++) {
			this.ciphers.get(i).saveToFile(pathToOutputFile);
		}
	}
	
	/**
	 * Sets <ciphers> from a given file that must include 
	 * a well structured list of cipher elements
	 * 
	 * A valid structure of two cipher objects 
	 * c1=(a1, b11, b12) where a1 := (x1, y1)
	 * c2=(a2, b21, b22) where a2 := (x2, y2)
	 * looks like this:
	 * length of x1
	 * data of x1
	 * length of y1
	 * data of y1
	 * length of b11
	 * data of b11
	 * length of b12
	 * data of b12
	 * length of x2
	 * data of x2
	 * length of y2
	 * data of y2
	 * length of b21
	 * data of b21
	 * length of b22
	 * data of b22
	 * 
	 * This method is the reverse of the method saveToFile which will generate
	 * the above defined structure.
	 * 
	 * @param pathToCipherListFile: The path to the file which will be used to generate
	 * the cipherList object.
	 * @throws IOException
	 */
	private void readFromFile(final String pathToInputFile) throws IOException{
		int bytePosition = 0;
		final FileInputStream fin = new FileInputStream(pathToInputFile);
		final byte[] buffer = new byte[fin.available()];
		fin.read(buffer);
		fin.close();
		
		while(bytePosition<buffer.length){
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
			
			final EllipticCurvePoint a = new EllipticCurvePoint(ax, ay);
			
			final int byteLengthOfB1 = buffer[bytePosition];
			bytePosition += 1;
			final byte[] b1ByteArray = new byte[byteLengthOfB1];
			System.arraycopy(buffer, bytePosition, b1ByteArray, 0, byteLengthOfB1);
			final BigInteger b1 = new BigInteger(b1ByteArray);
			bytePosition += byteLengthOfB1;
			
			final int byteLengthOfB2 = buffer[bytePosition];
			bytePosition += 1;
			final byte[] b2ByteArray = new byte[byteLengthOfB2];
			System.arraycopy(buffer, bytePosition, b2ByteArray, 0, byteLengthOfB2);
			final BigInteger b2 = new BigInteger(b2ByteArray);
			bytePosition += byteLengthOfB2;
			
			final Cipher chiffrat = new Cipher(a, b1, b2);
			this.ciphers.add(chiffrat);
		}
	}

	@Override
	public String toString() {
		String result = "[";
		final Iterator<Cipher> iterator = this.ciphers.iterator();
		while(iterator.hasNext()){
			final Cipher current = iterator.next();
			result += current.toString();
			if(iterator.hasNext()){
				result += ",";
			}
		}
		result += "]";
		return result;
	}
	
	public List<Cipher> getChiffrats() {
		return this.ciphers;
	}
}
