<<<<<<< HEAD


public class QuickUnionAlgorithm {

	private int[] id;
	
	public QuickUnionAlgorithm(int N) {
		this.id = new int[N];
		resetStructure();
	}
	
	private int root(int i) {
		while (i != id[i]) i = id[i];
		return i;
	}
	
	private void resetStructure() {
		for (int i = 0; i < this.id.length; i++) id[i] = i;
	}
	
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		id[i] = j;
	}
}
=======


public class QuickUnionAlgorithm {

	private int[] id;
	
	public QuickUnionAlgorithm(int N) {
		this.id = new int[N];
		resetStructure();
	}
	
	private int root(int i) {
		while (i != id[i]) i = id[i];
		return i;
	}
	
	private void resetStructure() {
		for (int i = 0; i < this.id.length; i++) id[i] = i;
	}
	
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		id[i] = j;
	}
}
>>>>>>> 093657dea6420a6fc3ffe42e51947c8389bc64e1
