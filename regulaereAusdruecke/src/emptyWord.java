import static org.junit.Assert.*;

import org.junit.Test;

import model.CompositeExpression;
import model.ConcatenationExpression;
import model.ElementaryExpression;
import model.RegularExpression;
import model.automat.Automaton;

public class emptyWord {

	@Test
	public void optionalTest() {

		ElementaryExpression b11 = new ElementaryExpression('t');
		ElementaryExpression b12 = new ElementaryExpression('e');
		ElementaryExpression b13 = new ElementaryExpression('s');
		ElementaryExpression b14 = new ElementaryExpression('t');
		
		ConcatenationExpression concat = new ConcatenationExpression(b11, b12);
		concat = new ConcatenationExpression(concat, b13);
		concat = new ConcatenationExpression(concat, b14);
		
		assertTrue(concat.recognize("test"));
		assertTrue(concat.recognize(""));
		assertFalse(concat.recognize("testtest"));
		
		concat.iterate();
		assertTrue(concat.recognize("test"));
		assertTrue(concat.recognize(""));
		assertTrue(concat.recognize("testtest"));
	}

}
