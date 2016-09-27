import static org.junit.Assert.*;

import org.junit.Test;

import model.ElementaryExpression;
import model.RegularExpression;
import model.automat.Automaton;

public class sequence2 {

	@Test
	public void testSequenze1() {
		ElementaryExpression b1 = new ElementaryExpression('a');
		ElementaryExpression b2 = new ElementaryExpression('b');
		
		Automaton autB1 = b1.toAutomat();
		Automaton autB2 = b2.toAutomat();
		
		autB1.sequence(autB2);

		assertTrue(autB1.recognizes("ab"));
	}

	@Test
	public void testSequenze2() {
		ElementaryExpression b1 = new ElementaryExpression('t');
		ElementaryExpression b2 = new ElementaryExpression('e');
		ElementaryExpression b3 = new ElementaryExpression('s');
		ElementaryExpression b4 = new ElementaryExpression('t');
		
		Automaton autB1 = b1.toAutomat();
		Automaton autB2 = b2.toAutomat();
		Automaton autB3 = b3.toAutomat();
		Automaton autB4 = b4.toAutomat();
		
		autB1.sequence(autB2);
		autB1.sequence(autB3);
		autB1.sequence(autB4);

		assertTrue(autB1.recognizes("test"));
	}
	
	@Test
	public void testSequenze3() {
		ElementaryExpression b1 = new ElementaryExpression('t');
		ElementaryExpression b2 = new ElementaryExpression('e');
		
		Automaton autB1 = b1.toAutomat();
		Automaton autB2 = b2.toAutomat();
		
		autB1.sequence(autB2);

		assertFalse(autB1.recognizes(""));
	}
	
	@Test
	public void testSequenze4() {
		RegularExpression b1 = new ElementaryExpression('t');
		RegularExpression b2 = new ElementaryExpression('e');
		RegularExpression b3 = new ElementaryExpression('s');
		
		b1 = b1.concatenate(b2);
		b1 = b1.concatenate(b3);

		assertTrue(b1.recognize("tes"));
	}

	@Test
	public void testSequenze5() {
		RegularExpression b1 = new ElementaryExpression('t');
		RegularExpression b2 = new ElementaryExpression('e');
		RegularExpression b3 = new ElementaryExpression('s');
		
		b1 = b1.concatenate(b2);
		b1 = b1.concatenate(b3);

		b1.setIterated(true);
		
		assertTrue(b1.recognize("testes"));
	}

}
