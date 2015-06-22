package pucrs.alpro2;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void test() {
		LinkedList lista = new LinkedList();
<<<<<<< HEAD
		lista.add("b","c");
		lista.add("y","z");
		lista.add("nome", "tipo");
=======
		lista.add("a","b","c");
		lista.add("x","y","z");
		lista.add("e", "nome", "tipo");
>>>>>>> b4c74579e44656a1230a71519cc468b4f9ba36b7
		String s = lista.toString();
		assertEquals("{c:b, z:y, tipo:nome}",s);
	}

}
