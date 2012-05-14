package ioInferface;

import static org.junit.Assert.*;

import main.Conf;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Interfaces.console.ModusListener;


public class ModusListenerTest {
	String[] inputString = null;
	ModusListener myListener = null;
	static main.mazeHandler myTest = null;
	static Conf globalconf = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		myTest = new main.mazeHandler();
		globalconf = new Conf();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_RUN() {

		inputString = new String[] { "MODUS", "SET", "RUN" };
		myListener = new ModusListener(inputString, globalconf);
		assertFalse(globalconf.isSTEPMODUS());

//		inputString = new String[] { "MODUS", "SET", "run" };
//		myListener = new ModusListener(inputString, globalconf);
//		assertFalse(globalconf.isSTEPMODUS());

	}

	@Test
	public void test_STEP() {
		inputString = new String[] { "MODUS", "SET", "STEP" };
		myListener = new ModusListener(inputString, globalconf);

		assertTrue(globalconf.isSTEPMODUS());
//
//		inputString = new String[] { "MODUS", "set", "STEP" };
//		myListener = new ModusListener(inputString, globalconf);
//		assertTrue(globalconf.isSTEPMODUS());

	}

	@Test
	public void get() {
		globalconf.setSTEPMODUS(true);
		assertTrue(globalconf.isSTEPMODUS());

		globalconf.setSTEPMODUS(false);
		assertFalse(globalconf.isSTEPMODUS());

	}

}
