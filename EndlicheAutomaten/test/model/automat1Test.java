package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class automat1Test {

	@Test
	public void automat1Test() {
		Automat a = new Automat();
		Transition t0 = new Transition(a.getAnfangszustand(), a.getEndzustand(), 'a');
		a.addTransition(t0);
		
		assertEquals(true,a.recognizes("a"));
	}
	
	@Test
	public void automat2Test() {
		Automat a = new Automat();

		State z0 = a.getAnfangszustand();
		State z1 = a.getEndzustand();
		State z2 = new State(a);
		State z3 = new State(a);
		State z4 = new State(a);
		State z5 = new State(a);
		
		Transition t01 = new Transition(z0, z1, 'a');
		Transition t02 = new Transition(z0, z2, 'a');
		Transition t21 = new Transition(z2, z1, 'a');
		Transition t24 = new Transition(z2, z4, 'a');
		Transition t41 = new Transition(z4, z1, 'a');
		Transition t45 = new Transition(z4, z5, 'a');
		Transition t53 = new Transition(z5, z3, 'a');
		Transition t31 = new Transition(z3, z1, 'a');
		
		a.addTransition(t01);
		a.addTransition(t02);
		a.addTransition(t21);
		a.addTransition(t24);
		a.addTransition(t41);
		a.addTransition(t45);
		a.addTransition(t53);
		a.addTransition(t31);
		
		assertFalse(a.recognizes(""));
		assertTrue(a.recognizes("a"));
		assertTrue(a.recognizes("aa"));
		assertTrue(a.recognizes("aaa"));
		assertFalse(a.recognizes("aaaa"));
		assertTrue(a.recognizes("aaaaa"));
		assertFalse(a.recognizes("aaaaaa"));
	}
	
	@Test
	public void automat3Test() {
		Automat a = new Automat();

		State z0 = a.getAnfangszustand();
		State z1 = new State(a);
		State z3 = a.getEndzustand();
		State z2 = new State(a);
		State z4 = new State(a);
		State z5 = new State(a);
		State z6 = new State(a);
		State z7 = new State(a);
		State z8 = new State(a);
		State z9 = new State(a);
		
		Transition t01 = new Transition(z0, z1, 't');
		Transition t02 = new Transition(z0, z2, 'b');
		Transition t12 = new Transition(z1, z2, 'e');
		Transition t13 = new Transition(z1, z3, 't');
		Transition t14 = new Transition(z1, z4, 'e');
		Transition t41 = new Transition(z4, z1, 's');
		Transition t48 = new Transition(z4, z8, 's');
		Transition t25 = new Transition(z2, z5, 's');
		Transition t56 = new Transition(z5, z6, 'a');
		Transition t57 = new Transition(z5, z7, 'u');
		Transition t59 = new Transition(z5, z9, 't');
		Transition t91 = new Transition(z9, z1, 't');
		
		a.addTransition(t01);
		a.addTransition(t02);
		a.addTransition(t12);
		a.addTransition(t13);
		a.addTransition(t14);
		a.addTransition(t41);
		a.addTransition(t48);
		a.addTransition(t25);
		a.addTransition(t56);
		a.addTransition(t57);
		a.addTransition(t59);
		a.addTransition(t91);
		
		assertFalse(a.recognizes(""));
		assertFalse(a.recognizes("a"));
		assertTrue(a.recognizes("tt"));
		assertTrue(a.recognizes("test"));
		assertTrue(a.recognizes("testtest"));
		assertTrue(a.recognizes("testtesttest"));
		assertTrue(a.recognizes("bsttt"));
	}

}
