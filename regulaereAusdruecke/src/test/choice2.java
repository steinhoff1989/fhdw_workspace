package test;
import static org.junit.Assert.*;

import org.junit.Test;

import model.ElementaryExpression;
import model.automat.Automaton;

public class choice2 {

	@Test
	public void testChoice1() {
		ElementaryExpression b11 = new ElementaryExpression('t');
		ElementaryExpression b12 = new ElementaryExpression('e');
		ElementaryExpression b13 = new ElementaryExpression('s');
		ElementaryExpression b14 = new ElementaryExpression('t');
		
		Automaton autB11 = b11.toAutomat();
		Automaton autB12 = b12.toAutomat();
		Automaton autB13 = b13.toAutomat();
		Automaton autB14 = b14.toAutomat();
		
		autB11.sequence(autB12);
		autB11.sequence(autB13);
		autB11.sequence(autB14);
		
		
		ElementaryExpression b21 = new ElementaryExpression('a');
		ElementaryExpression b22 = new ElementaryExpression('f');
		ElementaryExpression b23 = new ElementaryExpression('f');
		ElementaryExpression b24 = new ElementaryExpression('e');
		
		Automaton autB21 = b21.toAutomat();
		Automaton autB22 = b22.toAutomat();
		Automaton autB23 = b23.toAutomat();
		Automaton autB24 = b24.toAutomat();
		
		autB21.sequence(autB22);
		autB21.sequence(autB23);
		autB21.sequence(autB24);
		
		
		autB11.choice(autB21);

		assertTrue(autB11.recognizes("test"));
		assertTrue(autB11.recognizes("affe"));
		assertFalse(autB11.recognizes("testaffe"));
		
	}

}
