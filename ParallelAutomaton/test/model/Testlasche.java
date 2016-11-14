package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class Testlasche {

	@Before
	public void setUp(){
		Manager.getTheInstance().clearManager();
	}
	
	@Test
	public void SingleThreadTest() throws NotRecognizedException, InterruptedException {
		
		final Automat a1 = new Automat();
		
		final Transition t1 = new Transition(a1.getAnfangszustand(), a1.getEndzustand(), 'a', 'x');
		
		a1.addTransition(t1);
		assertEquals("x", a1.run("a"));
	}
	
	@Test
	public void DuoThreadTest() throws NotRecognizedException, InterruptedException {
		
		final Automat a1 = new Automat();
		final State z1 = new State(a1);
		
		final Transition t1 = new Transition(a1.getAnfangszustand(), a1.getEndzustand(), 'f', 'y');
		final Transition t2 = new Transition(a1.getAnfangszustand(), z1, 'e', 'x');

		a1.addTransition(t1);
		a1.addTransition(t2);
		assertEquals("y", a1.run("f"));
	}
	
	@Test
	public void NineStatesTest() throws NotRecognizedException, InterruptedException {
		
		final Automat a1 = new Automat();
		final State z1 = new State(a1);
		final State z2 = new State(a1);
		final State z3 = new State(a1);
		final State z4 = new State(a1);
		final State z5 = new State(a1);
		final State z6 = new State(a1);
		final State z7 = new State(a1);
		final State z8 = new State(a1);
		final State z9 = new State(a1);
		
		final Transition t1 = new Transition(a1.getAnfangszustand(), z1, 'a', 'l');
		final Transition t2 = new Transition(a1.getAnfangszustand(), z2, 'h', 'a');
		final Transition t3 = new Transition(z1, z3, 'b', 'o');
		final Transition t4 = new Transition(z3, z5, 'c', 'e');
		final Transition t5 = new Transition(z3, z6, 'f', 'k');
		final Transition t6 = new Transition(z5, z9, 'd', 'w');
		final Transition t7 = new Transition(z6, z9, 'g', 'k');
		final Transition t8 = new Transition(z9, a1.getEndzustand(), 'e', 'e');
		final Transition t9 = new Transition(z1, z4, 'h', 'o');
		final Transition t10 = new Transition(z4, z7, 'i', 'w');
		final Transition t11 = new Transition(z4, z8, 'j', 'g');

		a1.addTransition(t1);
		a1.addTransition(t2);
		a1.addTransition(t3);
		a1.addTransition(t4);
		a1.addTransition(t5);
		a1.addTransition(t6);
		a1.addTransition(t7);
		a1.addTransition(t8);
		a1.addTransition(t9);
		a1.addTransition(t10);
		a1.addTransition(t11);
		assertEquals("loewe", a1.run("abcde"));
		Manager.getTheInstance().clearManager();
		assertEquals("lokke", a1.run("abfge"));
	}
	
	@Test
	public void NotRecognizedTest() throws InterruptedException {
		
		final Automat a1 = new Automat();
		final State z1 = new State(a1);
		final State z2 = new State(a1);
		final State z3 = new State(a1);
		final State z4 = new State(a1);
		final State z5 = new State(a1);
		final State z6 = new State(a1);
		final State z7 = new State(a1);
		final State z8 = new State(a1);
		final State z9 = new State(a1);
		
		final Transition t1 = new Transition(a1.getAnfangszustand(), z1, 'a', 'l');
		final Transition t2 = new Transition(a1.getAnfangszustand(), z2, 'h', 'a');
		final Transition t3 = new Transition(z1, z3, 'b', 'o');
		final Transition t4 = new Transition(z3, z5, 'c', 'e');
		final Transition t5 = new Transition(z3, z6, 'f', 'k');
		final Transition t6 = new Transition(z5, z9, 'd', 'w');
		final Transition t7 = new Transition(z6, z9, 'g', 'k');
		final Transition t8 = new Transition(z9, a1.getEndzustand(), 'e', 'e');
		final Transition t9 = new Transition(z1, z4, 'h', 'o');
		final Transition t10 = new Transition(z4, z7, 'i', 'w');
		final Transition t11 = new Transition(z4, z8, 'j', 'g');

		a1.addTransition(t1);
		a1.addTransition(t2);
		a1.addTransition(t3);
		a1.addTransition(t4);
		a1.addTransition(t5);
		a1.addTransition(t6);
		a1.addTransition(t7);
		a1.addTransition(t8);
		a1.addTransition(t9);
		a1.addTransition(t10);
		a1.addTransition(t11);
		try {
			a1.run("abüde");
			fail();
		} catch (final NotRecognizedException e) {
		}
	}

}
