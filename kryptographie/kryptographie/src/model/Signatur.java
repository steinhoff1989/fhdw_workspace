package model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Signatur {
	
	private static final String SHA = "SHA1";
	
	public static BigInteger signaturErstellenChiffrat(String chiffrat, BigInteger d_sender, BigInteger n_sender, String algorithmus) throws NoSuchAlgorithmException {
		System.out.println("Signatur erstellen");
		MessageDigest mDigest = MessageDigest.getInstance(algorithmus);
        byte[] hashBytes = mDigest.digest(chiffrat.getBytes());

        BigInteger hash = new BigInteger(1,hashBytes).mod(n_sender);
        System.out.println("Hash(von " + chiffrat + "): " + hash);
        BigInteger signatur = ModArith.powerModulo(hash, d_sender, n_sender);
        System.out.println("Signatur (hash^d (mod n)) = " + signatur +"\n\n");
        return signatur;
	}
	
	public static BigInteger signaturErstellenChiffratSHA(String chiffrat, BigInteger d_sender, BigInteger n_sender) throws NoSuchAlgorithmException {
		return signaturErstellenChiffrat(chiffrat,d_sender,n_sender,SHA);
	}
	
	public static boolean signaturVerifizierenChiffrat(String chiffrat, BigInteger signatur, BigInteger e_sender, BigInteger n_sender, String algorithmus) throws NoSuchAlgorithmException{
		System.out.println("Signatur verifizieren");
		MessageDigest mDigest = MessageDigest.getInstance(algorithmus);
        byte[] hashBytes = mDigest.digest(chiffrat.getBytes());
        BigInteger hash = new BigInteger(1,hashBytes).mod(n_sender);
        System.out.println("Hash(von " + chiffrat + "): " + hash);
        BigInteger erwarteterHash = ModArith.powerModulo(signatur, e_sender, n_sender);
        System.out.println("Erwarteter hash (signatur^e (mod n)): " + erwarteterHash);
        return hash.equals(erwarteterHash);
	};
	
	public static boolean signaturVerifizierenChiffratSHA(String chiffrat, BigInteger signatur, BigInteger e_sender, BigInteger n_sender) throws NoSuchAlgorithmException{
		return signaturVerifizierenChiffrat(chiffrat, signatur,e_sender,n_sender,SHA);
	};

}
