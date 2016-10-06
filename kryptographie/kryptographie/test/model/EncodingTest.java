package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class EncodingTest {
	@Test
	public void encrypt() throws UnsupportedEncodingException{
//		List<BigInteger> chiffres = Functions.encrypt("àô2♦╚iTEST", 3, new BigInteger("22347337044147934289087340559622853328351975328422691685478728068100397267398285251417920434272618215718298185605992414818710501984591172954339199907202406599802969883901186485125875262382508886255794149045789901469622331668727148289683756691939923866159774449179686985356019930081615053632952560687459108691"), new BigInteger("62070547960735694406943388263344314220180020053078088609756323634406504565775023793012682240412052911584553951248381595973886682815574307178659916736515772220715271473276203728215066175205119448474942018302637082186882217416548664918589095396604150099244805500852946074245714543133373469454757056578368663469"));
//		System.out.println(chiffres);
	}
	
	@Test
	public void decrypt() throws UnsupportedEncodingException{
		List<BigInteger> encryptedBlockNumbers = new ArrayList<BigInteger>();
		
		encryptedBlockNumbers.add(new BigInteger("14139394768815128989244220310909446228832751724858931426828056357380121580913359607226028385820821890825846462136654648162476428272280102670707575495224231153564987056441205308028965776088133961911439957890160742685386493448201615399484269479565100388670676540944570280737710540114447281514503200586793825471"));
		encryptedBlockNumbers.add(new BigInteger("40037444703812040221372123846270662875026946590169646841305503793756884745655024317524529979200507231303914698672604120843755792556152891173912705350820904601800387272651161718476998805335276772589578309296989207188733315529292144931330939526984813144245238154289811755841165447922852865469368824184433646395"));
//		encryptedBlockNumbers.add(new BigInteger("90320211539359679629323009452598397407018870389971115504595483374539513044116810552769690568643049057012371596309084004556507319140966688195135672533801154265794629499781760139255440474759070435420647752063610733535791235753780814330429312337318462201924768397138206682685959423209925195936145680539147144470"));
		
//		String text = Functions.decrypt(encryptedBlockNumbers, 3, new BigInteger("63520341886503908092565584079953914979816558477420999970990952923672401236917676112166292723031443104807659622471042923469266498370080881490586227986267340570110960215195998256728335806151281207558075604036301910164884677479243385493343869825238831303202240988879392875364949285794768060741948581563932209723"), new BigInteger("95494881814251790951975594295533006376498523040970515303395062764106117328148237772800791779961615102972032383378514607762935731582353400807713185398325498374156236333148778056901508347485050395751545531232546318021916177640385515568006456670107207726523185377807356513360830898362734196615592492002250059869"));
//		System.out.println(text);
	}
	
	@Test
	public void testAScii(){
		System.out.println((char)187);
		 System.out.println("Default: " + new InputStreamReader(System.in).getEncoding());
	}
	
	@Test
	public void testStringToByteArray() throws UnsupportedEncodingException{
		String text = "🎧";
		System.out.println(text);
		
		byte[] textBytesOrig = text.getBytes("UTF-8");
		
		for(int i=0; i<textBytesOrig.length;i++){
			String s1 = String.format("%8s", Integer.toBinaryString(textBytesOrig[i] & 0xFF)).replace(' ', '0');
			System.out.println(s1);
		}
		
		BigInteger value = new BigInteger(1,textBytesOrig);
		
//		BigInteger value2 = new BigInteger(1, new byte[]{(byte)f4, (byte) bf, (byte) bf, (byte) bf})
		
		System.out.println(value);
		byte[] textBytes = value.toByteArray();
		if(textBytes[0]==0){
			textBytes = Arrays.copyOfRange(textBytes, 1, textBytes.length);
		}
		System.out.println(new String(textBytes, "UTF-8"));
	}
	
	@Test
	public void textToNumbersTest() throws UnsupportedEncodingException{
		String textOrig = "1譜號F¯▲3ZöU=╗▒¡☻g▼\\ñ│ ø☻╗d&  .";
		
		System.out.println(textOrig);

		List<BigInteger> numbers = Encoding.textToNumbers(textOrig, 3);
		
		System.out.println(numbers);
		
		String text = Encoding.numbersToText(numbers);
		
		System.out.println(text);
	}
	
	@Test
	public void textToNumbersTest3() throws UnsupportedEncodingException{
		String textOrig = "€";
		
		System.out.println(textOrig);

		List<BigInteger> numbers = Encoding.textToNumbers(textOrig, 1);
		
		System.out.println(numbers);
		
		String text = Encoding.numbersToText(numbers);
		
		System.out.println(text);
	}
	
	@Test
	public void textToNumbersTest2() throws UnsupportedEncodingException{
		String textOrig = "s";
		
		System.out.println(textOrig);

		List<BigInteger> numbers = Encoding.textToNumbers(textOrig, 1);
		
		System.out.println(numbers);
		
		String text = Encoding.numbersToText(numbers);
		
		System.out.println(text);
	}
	
	@Test
	public void numbersToTextTest() throws UnsupportedEncodingException{
		ArrayList<BigInteger> numbers = new ArrayList<BigInteger>();
		numbers.add(new BigInteger("214490386087"));
		numbers.add(new BigInteger("3350560"));
		System.out.println(Encoding.numbersToText(numbers));
	}
	
	@Test
	public void numbersToTextTest2() throws UnsupportedEncodingException{
		ArrayList<BigInteger> numbers = new ArrayList<BigInteger>();
		numbers.add(new BigInteger("6"));
		numbers.add(new BigInteger("57"));
		numbers.add(new BigInteger("42"));
		System.out.println(Encoding.numbersToText(numbers));
	}

	@Test
	public void numbersToTextTest3() throws IOException{
		BigInteger t2 = new BigInteger("129123123546");
		
		byte[] b3 = t2.toByteArray();
		
		System.out.println(new String(b3, "UTF-32"));
		
		BigInteger t = new BigInteger("129");
		
		FileOutputStream fos = new FileOutputStream("C:\\temp\\encrypt.steinhoff");
		fos.write(t.toByteArray());
		fos.close();
		
		FileInputStream fileInputStream = new FileInputStream("C:\\temp\\encrypt.steinhoff");
		int byteLength = fileInputStream.available();
		byte[] b2 = new byte[byteLength];
		fileInputStream.read(b2);
		fileInputStream.close();
		
		System.out.println(new BigInteger(b2).toString());
		
		System.out.println();
		
	}
	
	@Test
	public void textToNumberUnicodeTest(){
		BigInteger integerValue = Encoding.textToNumberUnicode("t");
		System.out.println(integerValue.toString());
	}

	@Test
	public void numberToTextUnicodeTest(){
		String text = Encoding.numberToTextUnicode(new BigInteger("116"));
		System.out.println(text);
	}
	
	@Test
	public void textToNumbersBlockChiffre(){
		List<BigInteger> integers = new ArrayList<BigInteger>();
		integers.add(new BigInteger("144"));
		String temp = Encoding.numbersToTextBlockChiffre(integers, 1, "windows-1250");
		System.out.println(temp);
//		
		System.out.println(Encoding.textToNumbersBlockChiffre(temp, 1, "windows-1250"));
		
		
//		System.out.println(Functions.textToNumbersBlockChiffre("a‘[ä9Ą‡>ŁTC", 5, "windows-1250"));
	}
	
	
	@Test
	public void numberToTextBlockChiffre(){
		List<BigInteger> integers = new ArrayList<BigInteger>();
//		integers.add(new BigInteger("30195774141837528999928201300"));
//		integers.add(new BigInteger("20906334024306834870122342453"));

		integers.add(new BigInteger("33597049468413535090382866951271879015014327117023802626225139294274772639791084908692214884604155062412591315243024850156936378620538974924143174175853204183497850340299484024969122516228015863815515783608953681800159550026658193259305603572695853203645939688443001352510477355315534223427908082043552914520"));
//		integers.add(new BigInteger("4933293861645673041566001"));
//		integers.add(new BigInteger("7614109313175479160148000"));
//		integers.add(new BigInteger("3437934693897193106510342460"));
		System.out.println(Encoding.numbersToTextBlockChiffre(integers, 128, "windows-1250"));
	}
	
	@Test
	public void checkN(){
		int k = 31;
		int l = 32;
		
		BigInteger n = new BigInteger("120092849648928410562383473832493722833712518201847423537633229592131916919584288941341466996455494951173469754132453761745524428047946312780615170525345321319279724864662615225432814143086769722592163064380422190981070533114156289819974010972146488089183667603376616283302703816180542687125303758602085861941");
		
		BigInteger min = new BigInteger("2").pow(32).pow(k);
		BigInteger max = new BigInteger("2").pow(32).pow(l);
		
		System.out.println("Min <= n : " + String.valueOf(min.compareTo(n) <= 0));
		System.out.println("n <= max : " + String.valueOf(n.compareTo(max) <= 0));
		
		System.out.println(n.subtract(min));
		System.out.println(max.subtract(n));
	}
	
	@Test
	public void compareBigInts(){
		BigInteger n = new BigInteger("120092849648928410562383473832493722833712518201847423537633229592131916919584288941341466996455494951173469754132453761745524428047946312780615170525345321319279724864662615225432814143086769722592163064380422190981070533114156289819974010972146488089183667603376616283302703816180542687125303758602085861941");
		BigInteger chiffNumber = new BigInteger("70595558016437887755583155582019545451247979180586920242815016330873049793540284552997999635435114672605203756327770305354642840317604507499124203838665016174022681569671413079703344498566750328328164665374816869975231355984236358384047161962817341863838370909407912972986367573220862378760625234404071603535");
		BigInteger maxChar = new BigInteger("2").pow(32).pow(5);
		
		System.out.println("chiffNumber<=n : " + String.valueOf(chiffNumber.compareTo(n) <= 0));
		System.out.println("maxChar<=n : " + String.valueOf(maxChar.compareTo(n) <= 0));
	}
}
