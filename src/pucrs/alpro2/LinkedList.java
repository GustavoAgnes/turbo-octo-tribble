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
			E valor = corrente.element;
			corrente = corrente.next;
			return valor;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	private static class Node<E> {
		public E element;
		public E nome;
		public E tipo;
		public Node<E> next;
		public Node<E> prev;

		/*
		 * public Node(E e) { element = e; prev = next = null; }
		 */
		public Node(E e, E name, E type) {
			element = e;
			nome = name;
			tipo = type;
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
		header = new Node<E>(null,null,null); // cria sentinela de in�cio
		trailer = new Node<E>(null,null,null);// cria sentinela de fim
		header.next = trailer; // conecta sentinela de in�cio no sentinela de
								// fim
		trailer.prev = header; // conecta sentinela de fim no sentinela de
								// in�cio
		count = 0; // indica que a lista est� vazia
	}

	public void add(E e, E nome, E tipo) {
		Node<E> n = new Node<E>(e,nome,tipo); // novo nodo que ser� adicionado � lista
		Node<E> last = trailer.prev;// nodo anterior ao novo nodo, ap�s a
									// inser��o
		
		n.prev = last; // conecta o novo com o atual �ltimo elemento
		n.next = trailer; // conecta o novo com o sentinela de fim
		last.next = n; // conecta o �ltimo elemento atual com o novo
		trailer.prev = n; // conecta o sentinela de fim com o novo
		count++; // registra que a lista recebeu mais um nodo
	}


	public void add(int index, E e, E nome, E tipo) {
		if (index < 0 || index > count) {
			throw new IndexOutOfBoundsException("Pos. invalida!");
		}

		Node<E> aux = getNodeAt(index);
		Node<E> n = new Node<E>(e,nome,tipo);
		Node<E> ant = aux.prev;

		n.next = aux;
		n.prev = ant;
		ant.next = n;
		aux.prev = n;
		count++;
	}

	private Node<E> getNodeAt(int index) {
		int m = count / 2;
		Node<E> aux;

		if (index <= m) {
			aux = header.next;
			for (int pos = 0; pos <= m; pos++) {
				aux = aux.next;
			}
		} else {
			aux = trailer.prev;
			for (int pos = count; pos > m; pos--) {
				aux = aux.prev;
			}
		}
		return aux;
	}

	public void clear() {
		header.next = trailer; // conecta sentinela de in�cio no sentinela de
								// fim
		trailer.prev = header; // conecta sentinela de fim no sentinela de
								// in�cio
		count = 0; // indica que a lista est� vazia
	}

	public boolean contains(E e) {
		return indexOf(e) != -1;
	}

	public E getLinha(int index) {
		if ((index < 0) || (index >= count)) {
			throw new IndexOutOfBoundsException("Pos. inv�lida: " + index);
		}
		Node<E> target = getNodeAt(index);
		return target.element;
	}

	public E getTipo(int index) {
		if ((index < 0) || (index >= count)) {
			throw new IndexOutOfBoundsException("Pos. inv�lida: " + index);
		}
		Node<E> target = getNodeAt(index);
		return target.tipo;
	}

	public E getNome(int index) {
		if ((index < 0) || (index >= count)) {
			throw new IndexOutOfBoundsException("Pos. inv�lida: " + index);
		}
		Node<E> target = getNodeAt(index);
		return target.nome;
	}
	
	public int indexOf(E e) {
		Node<E> n = header.next;
		int p = 0;
		while (n != trailer) {
			if (n.element.equals(e)) {
				return p;
			}
			n = n.next;
			p++;
		}
		return -1;
	}

	public boolean isEmpty() {
		return count == 0;
	}

	public boolean remove(E e) {
		int pos = indexOf(e);
		if (pos == -1) {
			return false;
		}
		remove(pos);
		return true;
	}

	public E remove(int index) {
		if ((index < 0) || (index >= count)) {
			throw new IndexOutOfBoundsException("Pos. inv�lida: " + index);
		}
		Node<E> aux = getNodeAt(index);
		E value = aux.element;
		Node<E> beforeAux = aux.prev;
		Node<E> afterAux = aux.next;
		beforeAux.next = afterAux;
		afterAux.prev = beforeAux;
		count--;
		return value;
	}

	public int size() {
		return count;
	}

	public E set(int index, E element) {
		if ((index < 0) || (index >= count)) {
			throw new IndexOutOfBoundsException("Pos. inv�lida: " + index);
		}
		Node<E> target = getNodeAt(index);
		E aux = target.element; // salva antigo
		target.element = element; // novo valor
		return aux; // retorna valor antigo
	}
/*
	public void addFirst(E e) {
		add(0, e, null);
	}
*/
	public E getFirst() {
		if (count == 0) {
			throw new NoSuchElementException("Lista vazia");
		}
		Node<E> first = header.next;
		return first.element;
	}

	public E getLast() {
		if (count == 0) {
			throw new NoSuchElementException("Lista vazia");
		}
		Node<E> last = trailer.prev;
		return last.element;
	}

	public E removeFirst() {
		if (isEmpty()) { // n�o h� elemento para remover
			throw new NoSuchElementException();
		}
		Node<E> target = header.next; // indica o primeiro nodo da lista (first)
		E item = target.element; // coleta o conte�do do primeiro nodo
		Node<E> prox = target.next; // indica o segundo nodo da lista atual
		prox.prev = header; // conecta o segundo nodo com o sentinela de in�cio
		header.next = prox; // conecta o sentinela de in�cio com o segundo nodo
							// da lista original
		target.prev = null; // libera dados do nodo removido
		target.element = null;
		target.next = null;
		count--; // registra que a lista perdeu um nodo
		return item; // retorna a informa��o que havia no nodo destru�do
	}

	public E removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException("Lista vazia!");
		}

		Node<E> aux = trailer.prev; // �ltimo da lista
		Node<E> beforeAux = aux.prev; // pen�ltimo da lista ou sentinela

		E value = aux.element; // recupera valor do nodo a ser removido

		beforeAux.next = trailer; // pen�ltimo passa a ser o �ltimo nodo
		trailer.prev = beforeAux; // .. ou a lista fica vazia

		count--;

		return value;
	}

	public String toString() {
		// ver
		// http://www.docjar.com/html/api/java/util/AbstractCollection.java.html
		String s = "{";
		Node<E> aux = header.next;
		if (aux != trailer) {
			s += "" + aux.tipo + ":" +aux.nome;
			aux = aux.next;
			while (aux != trailer) {
				s += ", " + aux.tipo + ":" +aux.nome;
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