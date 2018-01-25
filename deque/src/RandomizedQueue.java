import javax.print.attribute.standard.DateTimeAtCompleted;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.*;

public class RandomizedQueue<E> implements Iterable<E> {
	
	List<E> lista = new LinkedList<E>();
	int current;
	
	public RandomizedQueue() {
		this.current = 0;
	}
	
	public boolean isEmpty() {
		return (this.lista.size() == 0);
	}
	
	public int size() {
		return this.lista.size();
	}
	
	public void enqueue(E item) {
		if (item == null) throw new IllegalArgumentException("Item to add is null");
		this.lista.add(item);		
	}
			
	public E dequeue() {
		if (this.lista.size() == 0) throw new NoSuchElementException("No itens to dequeue");
		
		int index = (new Random()).nextInt(lista.size());
		E value = this.lista.get(index);
		this.lista.remove(index);
		return value;
	}
	
	public E sample() {
		if (this.lista.size() == 0) throw new NoSuchElementException("No itens to sample");
		
		int index = (new Random()).nextInt(lista.size());
		E value = this.lista.get(index);		
		return value;
	}
	
	public Iterator<E> iterator() {
		return this.lista.iterator();
	}
	
	public E next() {
		if (this.current >= this.lista.size()) throw new NoSuchElementException("No itens anymore");		
		return this.lista.get(this.current++);
	}
	
	public boolean hasNext() {
		return (this.current < this.lista.size());
	}
		
	
	public static void main(String[] args) {
		if (args.length == 0) throw new NoSuchElementException("No file to read");
		
		Reader reader = new Reader(args[0]);		
		ArrayList<String> data = reader.readFromFileToListSpaceSeparated();
		RandomizedQueue<String> mydeque = new RandomizedQueue<String>();
		
		mydeque = new RandomizedQueue<String>();
		
		for (String s : data) {
			mydeque.enqueue(s);
		}		
		while (mydeque.size() > 0) {
			System.out.print(mydeque.dequeue() + " ");
		}
	}
}
