package pucrs.alpro2;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void test() {
		LinkedList lista = new LinkedList();
		lista.add("a","b","c");
		lista.add("x","y","z");
		lista.add("e", "nome", "tipo");
		String s = lista.toString();
		assertEquals("{c:b, z:y, tipo:nome}",s);
	}

}
