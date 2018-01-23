<<<<<<< HEAD

import java.util.*;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {
	
	int size;
	List<Double> thresholds;
	Percolation perc;
	
	public PercolationStats(int n, int trials) {		
		this.size = n;		
		this.thresholds = new ArrayList<Double>();
		this.perc = new Percolation(n);
		
		for (int i = 0; i < trials; i++) {			
			thresholds.add(((double) openSitesUntilPercolates() / (this.size * this.size)));				
			perc.reset();
		}
	}
		
	private int openSitesUntilPercolates()
	{
		int row, col, count;
		
		count = 0;
		while (!perc.percolates()) {
			row = StdRandom.uniform(this.size)+1;
			col = StdRandom.uniform(this.size)+1;				
			perc.open(row, col);				
			count++;				
		}
		
		return count;
	}
	
	public double mean() {
		//return sitesToPerc.get(0)/sitesToPerc.size();
		
		return thresholds
				.stream()
				.mapToDouble(e -> e.doubleValue())
	            .average()
	            .getAsDouble();
	          
	}
	public double stddev() {
		return StdStats.stddev(
				thresholds
				.stream()
				.mapToDouble(e -> e.doubleValue())
				.toArray()
			);
								
	}
	public double confidenceLo() {
		return 0;
	}
	public double confidenceHi() {
		return 0;
	}

	public static void main(String[] args) {
		
		PercolationStats stats = new PercolationStats(200, 100);		
		System.out.println("mean					= " + stats.mean() );
		System.out.println("stddev					= " + stats.stddev() );
		System.out.println("95% confidence interval	= [" + (stats.mean() - stats.stddev()) + ", " + (stats.mean() + stats.stddev()) + "]");
		
	}
}
=======

import java.util.*;
import edu.princeton.cs.algs4.StdRandom;


public class PercolationStats {
	
	int size;
	List<Double> sitesToPerc;
	Percolation perc;
	
	public PercolationStats(int n, int trials) {
		int row, col;
		this.size = n;		
		this.sitesToPerc = new ArrayList<Double>();
		this.perc = new Percolation(n);
		
		for (int i = 0; i < trials; i++) {
			sitesToPerc.add(0.0);
			
			while (!perc.percolates()) {
				row = StdRandom.uniform(this.size)+1;
				col = StdRandom.uniform(this.size)+1;				
				perc.open(row, col);				
				sitesToPerc.set(i, sitesToPerc.get(i).doubleValue() + 1);				
			}
			
			sitesToPerc.set(i, sitesToPerc.get(i).doubleValue() / trials);	
			perc.reset();
		}
	}
	
	public double mean() {
		//return sitesToPerc.get(0)/sitesToPerc.size();
		
		return sitesToPerc
				.stream()
				.mapToDouble(e -> e.intValue())
	            .reduce(0, (sum,x) -> (sum + x)) / sitesToPerc.size();
	          
	}
	public double stddev() {
		return 0;
	}
	public double confidenceLo() {
		return 0;
	}
	public double confidenceHi() {
		return 0;
	}

	public static void main(String[] args) {
		PercolationStats stats = new PercolationStats(200, 100);		
		System.out.println("Threshold: " + stats.mean() );
	}
}
>>>>>>> 093657dea6420a6fc3ffe42e51947c8389bc64e1
