import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	private Point[] points;
	private LineSegment[] segments;
	
	public FastCollinearPoints(Point[] points) {
		this.points = points;
	}
	
	public int numberOfSegments() {
		return segments.length;
	}
	
	public LineSegment[] segments() {
		
		LineSegment[] segs;
		//ArrayList<Point> pointsList = new ArrayList<Point>(Arrays.asList(this.points));
		Point[] pointsCopy = new Point[this.points.length];
		ArrayList<LineSegment> segAux = new ArrayList<LineSegment>();			
		Point pFirst, pLast;
		double slope, slope_ant = Double.NEGATIVE_INFINITY;
		boolean inSegment = false;
		int pointsInSegment = 0;
				
		if (points == null)
            throw new NullPointerException();
		

		pFirst = new Point(0,0);
		pLast = new Point(0,0);
		pointsCopy = points.clone();
		
		/*
		System.out.println("Desordenado: ");
		for (int i=0; i < pointsList.size()-1; i++)
			System.out.println(pointsList.get(i).slopeTo(pointsList.get(i+1)));
		pointsList.sort(this.points[0].slopeOrder());
		System.out.println("Ordenado: ");		
		for (int i=0; i < pointsList.size()-1; i++)
			System.out.println(pointsList.get(i).slopeTo(pointsList.get(i+1)));
		*/
		for (int k=0; k < pointsCopy.length; k++) {
			Arrays.sort(pointsCopy);
			Arrays.sort(pointsCopy, this.points[k].slopeOrder());
						
			slope_ant = pointsCopy[pointsCopy.length - 2].slopeTo(pointsCopy[pointsCopy.length - 1]);
			inSegment = false;
			pointsInSegment = 0;
					
			for (int i=0; i < pointsCopy.length-1; i++) {			
				slope = pointsCopy[i].slopeTo(pointsCopy[i+1]);
				
				if (!inSegment && Double.compare(slope_ant, slope) == 0  && i > 0) {				
					pFirst = pointsCopy[i-1];
					inSegment = true;
					pointsInSegment++;
				
				} else if (inSegment && (Double.compare(slope_ant, slope) != 0 || (i == pointsCopy.length - 2)) ) {				
					pLast = pointsCopy[i];
					pointsInSegment = pointsInSegment + 2;
					inSegment = false;
					if (pointsInSegment >= 4)				
						segAux.add(new LineSegment(pFirst, pLast));
					pointsInSegment = 0;
					
				} else if (inSegment && Double.compare(slope_ant, slope) == 0) {			
					pointsInSegment++;
				
				}
				
				slope_ant = slope;
			}
		}
		
		segs = new LineSegment[segAux.size()];
		int i = 0;
		for (LineSegment l : segAux) {
			segs[i] = l;
			i++;
		}
		
		return segs;
	}
	
	private boolean hasDuplicate(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                return true;
            }
        }
        return false;
    }
	
	public static void main(String[] args) throws InterruptedException {

	    // read the n points from a file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();
	    
	    //Thread.sleep(5000);
	    
	    // print and draw the line segments
	    FastCollinearPoints collinear = new FastCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	}
}
