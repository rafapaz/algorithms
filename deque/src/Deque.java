import java.util.Iterator;

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

public class Deque<E> implements Iterable<E> {
	
	List<E> array = new LinkedList<E>();
	int current;
	
	public Deque() {
		this.current = 0;
	}
	
	public boolean isEmpty() {
		return (this.array.size() == 0);
	}
	
	public int size() {
		return this.array.size();
	}
	
	public void addFirst(E item) {
		if (item == null) throw new IllegalArgumentException("Item to add is null");
		this.array.add(0, item);		
	}
	
	public void addLast(E item) {
		if (item == null) throw new IllegalArgumentException("Item to add is null");
		this.array.add(this.array.size(), item);		
	}
	
	public E removeFirst() {
		if (this.array.size() == 0) throw new NoSuchElementException("No itens to remove");
		E value = this.array.get(0);
		this.array.remove(0);
		return value;
	}
	
	public E removeLast() {
		if (this.array.size() == 0) throw new NoSuchElementException("No itens to remove");
		E value = this.array.get(this.array.size() - 1);
		this.array.remove(this.array.size() - 1);
		return value;
	}
	
	public Iterator<E> iterator() {
		return this.array.iterator();
	}
	
	public E next() {
		if (this.current >= this.array.size()) throw new NoSuchElementException("No itens anymore");		
		return this.array.get(this.current++);
	}
	
	public boolean hasNext() {
		return (this.current < this.array.size());
	}
		
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		if (args.length == 0) throw new NoSuchElementException("No file to read");
		
		Reader reader = new Reader(args[0]);		
		ArrayList<String> data = reader.readFromFileToListSpaceSeparated();
		PrintWriter writer = new PrintWriter("log.txt", "UTF-8");
				
		// Stack behavior /////////
		Deque<String> mydeque = new Deque<String>();
		System.out.println("Stack behavior: ");
		Timestamp time_i, time_f;
		long dif, dif_ant;
		
		dif_ant = -1;
		for (String s : data) {
			time_i = new Timestamp(System.currentTimeMillis());
			mydeque.addLast(s);
			time_f = new Timestamp(System.currentTimeMillis());
			
			dif = time_f.getTime() - time_i.getTime();
			
			if (dif_ant != -1 && dif != dif_ant)
				writer.println("dif time: " + (dif - dif_ant));
			
			dif_ant = dif;
		}		
		writer.close();
		
		while (mydeque.size() > 0) {
			System.out.print(mydeque.removeLast() + " ");
		}
		///////////////////////////
		//System.out.println("time: " + );
		System.out.println();
		
		// Queue behavior /////////
		mydeque = new Deque<String>();
		System.out.println("Queue behavior: ");
		for (String s : data) {
			mydeque.addLast(s);
		}		
		while (mydeque.size() > 0) {
			System.out.print(mydeque.removeFirst() + " ");
		}
		///////////////////////////
	    
	}
}
