import java.util.*;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import static org.junit.jupiter.api.Assertions.assertTrue;


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
		assertTrue((args.length == 2), "Error: Please inform 2 parameters: size N for a grid with NxN and number of trials");
		assertTrue((Integer.parseInt(args[0]) > 0 && Integer.parseInt(args[1]) > 0), "Error: Parameters must be greater than zero");
				
		PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));		
		System.out.println("mean					= " + stats.mean() );
		System.out.println("stddev					= " + stats.stddev() );
		System.out.println("95% confidence interval	= [" + (stats.mean() - stats.stddev()) + ", " + (stats.mean() + stats.stddev()) + "]");
		
	}
}