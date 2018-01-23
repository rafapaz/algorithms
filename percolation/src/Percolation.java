<<<<<<< HEAD
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation implements AssertionConcern {

	int gridSize;
	Boolean opened[][];
	WeightedQuickUnionUF algorithm;
	
	public Percolation(int n) {
		assertArgument(n > 0, "Size of the grid must be greater than zero");
		this.gridSize = n;
		this.opened = new Boolean[n][n];
		this.reset();		
	}
	
	public void reset() {
		
		this.algorithm = new WeightedQuickUnionUF(this.gridSize * this.gridSize + 2);
		
		for (int i = 0; i < this.gridSize; i++)
			for (int j = 0; j < this.gridSize; j++)
				opened[i][j] = false;
		
		// The virtual sites will be the last two
		for (int i = 0; i < this.gridSize; i++)
			algorithm.union(this.gridSize * this.gridSize, i);
		
		for (int i = (this.gridSize * this.gridSize - this.gridSize); i < this.gridSize * this.gridSize; i++)
			algorithm.union(this.gridSize * this.gridSize + 1, i);
	}
	
	public void open(int row, int col) {
		int nrow, ncol;
		Boolean neighborUp, neighborDown, neighborLeft, neighborRight;
		assertArgument((row >= 1 && row <= this.gridSize), "Trying to open site out of the range");
		assertArgument((col >= 1 && col <= this.gridSize), "Trying to open site out of the range");
		
		nrow = row - 1;
		ncol = col - 1;
		
		opened[nrow][ncol] = true;		
		neighborUp = (nrow - 1 >= 0);
		neighborDown = (nrow + 1 <= this.gridSize-1);
		neighborLeft = (ncol - 1 >= 0);
		neighborRight = (ncol + 1 <= this.gridSize-1);
	
		if (neighborUp && opened[nrow-1][ncol])
			this.algorithm.union(nrow*this.gridSize + ncol, (nrow-1)*this.gridSize + ncol);
		if (neighborDown && opened[nrow+1][ncol])
			this.algorithm.union(nrow*this.gridSize + ncol, (nrow+1)*this.gridSize + ncol);
		if (neighborLeft && opened[nrow][ncol-1])
			this.algorithm.union(nrow*this.gridSize + ncol, nrow*this.gridSize + (ncol-1));
		if (neighborRight && opened[nrow][ncol+1])
			this.algorithm.union(nrow*this.gridSize + ncol, nrow*this.gridSize + (ncol+1));	
				
	}
	
	public Boolean isOpen(int row, int col) {
		int nrow, ncol;
		assertArgument((row >= 1 && row <= this.gridSize), "Trying to check if a site is open: grid values out of the range");
		assertArgument((col >= 1 && col <= this.gridSize), "Trying to check if a site is open: grid values out of the range");
		
		nrow = row - 1;
		ncol = col - 1;
		
		return opened[nrow][ncol];
	}
	
	public Boolean isFull(int row, int col) {
		int nrow, ncol;
		assertArgument((row >= 1 && row <= this.gridSize), "Trying to check if a site is full: grid values out of the range");
		assertArgument((col >= 1 && col <= this.gridSize), "Trying to check if a site is full: grid values out of the range");
				
		nrow = row - 1;
		ncol = col - 1;
		
		return opened[nrow][ncol] && algorithm.connected(this.gridSize * this.gridSize, nrow*this.gridSize + ncol);
	}
	
	public int numberOfOpenSites() {
		int count=0;
		
		for (int i = 0; i < this.gridSize; i++)
			for (int j = 0; j < this.gridSize; j++)
				count = count + ((opened[i][j]) ? 1 : 0);
		
		return count;
	}
	
	public Boolean percolates() {
		return algorithm.connected(this.gridSize * this.gridSize, (this.gridSize * this.gridSize) + 1);
	}
	
	public static void main(String[] args) {
		
	}
}
=======
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation implements AssertionConcern {

	int gridSize;
	Boolean opened[][];
	WeightedQuickUnionUF algorithm;
	
	public Percolation(int n) {
		assertArgument(n > 0, "Size of the grid must be greater than zero");
		this.gridSize = n;
		this.opened = new Boolean[n][n];
		this.reset();		
	}
	
	public void reset() {
		
		this.algorithm = new WeightedQuickUnionUF(this.gridSize * this.gridSize + 2);
		
		for (int i = 0; i < this.gridSize; i++)
			for (int j = 0; j < this.gridSize; j++)
				opened[i][j] = false;
		
		// The virtual sites will be the last two
		for (int i = 0; i < this.gridSize; i++)
			algorithm.union(this.gridSize * this.gridSize, i);
		
		for (int i = (this.gridSize * this.gridSize - this.gridSize); i < this.gridSize * this.gridSize; i++)
			algorithm.union(this.gridSize * this.gridSize + 1, i);
	}
	
	public void open(int row, int col) {
		int nrow, ncol;
		Boolean neighborUp, neighborDown, neighborLeft, neighborRight;
		assertArgument((row >= 1 && row <= this.gridSize), "Trying to open site out of the range");
		assertArgument((col >= 1 && col <= this.gridSize), "Trying to open site out of the range");
		
		nrow = row - 1;
		ncol = col - 1;
		
		opened[nrow][ncol] = true;		
		neighborUp = (nrow - 1 >= 0);
		neighborDown = (nrow + 1 <= this.gridSize-1);
		neighborLeft = (ncol - 1 >= 0);
		neighborRight = (ncol + 1 <= this.gridSize-1);
	
		if (neighborUp && opened[nrow-1][ncol])
			this.algorithm.union(nrow*this.gridSize + ncol, (nrow-1)*this.gridSize + ncol);
		if (neighborDown && opened[nrow+1][ncol])
			this.algorithm.union(nrow*this.gridSize + ncol, (nrow+1)*this.gridSize + ncol);
		if (neighborLeft && opened[nrow][ncol-1])
			this.algorithm.union(nrow*this.gridSize + ncol, nrow*this.gridSize + (ncol-1));
		if (neighborRight && opened[nrow][ncol+1])
			this.algorithm.union(nrow*this.gridSize + ncol, nrow*this.gridSize + (ncol+1));	
				
	}
	
	public Boolean isOpen(int row, int col) {
		int nrow, ncol;
		assertArgument((row >= 1 && row <= this.gridSize), "Trying to check if a site is open: grid values out of the range");
		assertArgument((col >= 1 && col <= this.gridSize), "Trying to check if a site is open: grid values out of the range");
		
		nrow = row - 1;
		ncol = col - 1;
		
		return opened[nrow][ncol];
	}
	
	public Boolean isFull(int row, int col) {
		int nrow, ncol;
		assertArgument((row >= 1 && row <= this.gridSize), "Trying to check if a site is full: grid values out of the range");
		assertArgument((col >= 1 && col <= this.gridSize), "Trying to check if a site is full: grid values out of the range");
				
		nrow = row - 1;
		ncol = col - 1;
		
		return opened[nrow][ncol] && algorithm.connected(this.gridSize * this.gridSize, nrow*this.gridSize + ncol);
	}
	
	public int numberOfOpenSites() {
		int count=0;
		
		for (int i = 0; i < this.gridSize; i++)
			for (int j = 0; j < this.gridSize; j++)
				count = count + ((opened[i][j]) ? 1 : 0);
		
		return count;
	}
	
	public Boolean percolates() {
		return algorithm.connected(this.gridSize * this.gridSize, (this.gridSize * this.gridSize) + 1);
	}
	
	public static void main(String[] args) {
		
	}
}
>>>>>>> 093657dea6420a6fc3ffe42e51947c8389bc64e1
