package ioinsterface;

import static org.junit.Assert.*;
import ioInferface.ModusListener;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class modusListenerTest {
	private String[] inputString = null;
	private static Test _mytest = null;
	private static ModusListener _modusListener;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		_mytest = null;

	}

	@Before
	public void setUp() throws Exception {
		inputString = new String[] { "GUI", "CREATE" };

	}

	@Test
	public void test() {
		// _modusListener = new ModusListener(inputString, null);

	}

}
