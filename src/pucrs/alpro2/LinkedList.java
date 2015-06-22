package pucrs.alpro2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author marco.mangan@pucrs.br
 * 
 * @param <E>
 */
public class LinkedList<E> {

	public class DoubleLinkedIterator implements Iterator<E> {
		private Node<E> corrente = header.next;

		public boolean hasNext() {
			return corrente != trailer;
		}

		public E next() {
			if (corrente == trailer) {
				throw new NoSuchElementException();
			}
			E valor = corrente.nome;
			corrente = corrente.next;
			return valor;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	private static class Node<E> {
		public E nome;
		public E tipo;
<<<<<<< HEAD
		public double coordX;
		public double coordY;
		public Node<E> next;
		public Node<E> prev;

		public Node(E name, E type, double cX, double cY) {
=======
		public String coordX;
		public String coordY;
		public Node<E> next;
		public Node<E> prev;

		public Node(E name, E type, String cX, String cY) {
>>>>>>> f21521d6f0453a7c4a479d6fdcf4cb95e9d1f642
			nome = name;
			tipo = type;
			coordX = cX;
			coordY = cY;
			prev = next = null;
		}
	}

	private Node<E> header;
	private Node<E> trailer;
	private int count;

	/**
	 * Cria uma lista vazia.
	 */
	public LinkedList() {
<<<<<<< HEAD
		header = new Node<E>(null, null, 0, 0); // cria sentinela de
														// início
		trailer = new Node<E>(null, null, 0, 0);// cria sentinela de fim
=======
		header = new Node<E>(null, null, null, null); // cria sentinela de
														// início
		trailer = new Node<E>(null, null, null, null);// cria sentinela de fim
>>>>>>> f21521d6f0453a7c4a479d6fdcf4cb95e9d1f642
		header.next = trailer; // conecta sentinela de início no sentinela de
								// fim
		trailer.prev = header; // conecta sentinela de fim no sentinela de
								// início
		count = 0; // indica que a lista está vazia
	}

	// CÓDIGO ORACLE
	private Node<E> entry(int index) {
		if (index < 0 || index >= count)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
					+ count);
		Node e;
		if (index < (count >> 1)) {
			e = header.next;
			for (int i = 0; i <= index; i++)
				e = e.next;
		} else {
			e = trailer.prev;
			for (int i = count; i > index; i--)
				e = e.prev;
		}
		return e;
	}

	//

	public E getNome(int index) {
		return entry(index).nome;
	}

	public E getTipo(int index) {
		return entry(index).tipo;
	}

<<<<<<< HEAD
	public double getCoordX(int index) {
		return entry(index).coordX;
	}

	public double getCoordY(int index) {
		return entry(index).coordY;
	}

	public void add(E nome, E tipo, double coordX, double coordY) {
=======
	public String getCoordX(int index) {
		return entry(index).coordX;
	}

	public String getCoordY(int index) {
		return entry(index).coordY;
	}

	public void add(E nome, E tipo, String coordX, String coordY) {
>>>>>>> f21521d6f0453a7c4a479d6fdcf4cb95e9d1f642
		Node<E> n = new Node<E>(nome, tipo, coordX, coordY); // novo nodo que
																// será
																// adicionado à
		// lista
		Node<E> last = trailer.prev;// nodo anterior ao novo nodo, após a
									// inserção

		n.prev = last; // conecta o novo com o atual último elemento
		n.next = trailer; // conecta o novo com o sentinela de fim
		last.next = n; // conecta o último elemento atual com o novo
		trailer.prev = n; // conecta o sentinela de fim com o novo
		count++; // registra que a lista recebeu mais um nodo
	}

	/*
	 * public void add(int index, E nome, E tipo) { if (index < 0 || index >
	 * count) { throw new IndexOutOfBoundsException("Pos. invalida!"); }
	 * 
	 * // Node<E> aux = getNodeAt(index); Node<E> n = new Node<E>(nome, tipo,
	 * coordX, coordY); // Node<E> ant = aux.prev;
	 * 
	 * // n.next = aux; // n.prev = ant; // ant.next = n; // aux.prev = n;
	 * count++; }
	 */
	public void clear() {
		header.next = trailer; // conecta sentinela de início no sentinela de
								// fim
		trailer.prev = header; // conecta sentinela de fim no sentinela de
								// início
		count = 0; // indica que a lista está vazia
	}

	public boolean isEmpty() {
		return count == 0;
	}

	public int size() {
		return count;
	}

	/*
	 * public void addFirst(E e) { add(0, e, null); }
	 */
	public String toString() {
		// ver
		// http://www.docjar.com/html/api/java/util/AbstractCollection.java.html
		String s = "{";
		Node<E> aux = header.next;
		if (aux != trailer) {
			s += "" + aux.tipo + "." + aux.nome;
			aux = aux.next;
			while (aux != trailer) {
				s += ", " + aux.tipo + ":" + aux.nome;
				aux = aux.next;
			}
		}
		s += "}";
		return s;
	}

	public Iterator<E> iterator() {
		return new DoubleLinkedIterator();
	}

}