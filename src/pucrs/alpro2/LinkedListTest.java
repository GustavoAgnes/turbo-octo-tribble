package pucrs.alpro2;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void test() {
		LinkedList lista = new LinkedList();
		lista.add("b","c");
		lista.add("y","z");
		lista.add("nome", "tipo");
		String s = lista.toString();
		assertEquals("{c:b, z:y, tipo:nome}",s);
	}

}
