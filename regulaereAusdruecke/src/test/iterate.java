package test;
import static org.junit.Assert.*;

import org.junit.Test;

import model.ElementaryExpression;
import model.automat.Automaton;

public class iterate {

	@Test
	public void testIterate1() {
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
	
	assertTrue(autB11.recognizes("test"));
	
	autB11.iterate();
	
	assertTrue(autB11.recognizes("testtest"));
	
	}

}
