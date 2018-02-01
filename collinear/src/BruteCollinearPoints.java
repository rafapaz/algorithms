
import java.util.ArrayList;

import javax.sound.sampled.Line;

import com.sun.tools.javac.util.List;

import edu.princeton.cs.algs4.*;

public class BruteCollinearPoints {
	private Point[] points;
	private LineSegment[] segments;
	
	public BruteCollinearPoints(Point[] points) {
		this.points = points;
	}
	
	public int numberOfSegments() {
		return segments.length;
	}
	
	public LineSegment[] segments() {
		
		LineSegment[] segs;
		ArrayList<LineSegment> segAux = new ArrayList<LineSegment>();			
		
		for (int i=0; i < points.length; i++) {
			for (int j=i+1; j < points.length; j++) {
				for (int k=j+1; k < points.length; k++) {
					for (int l=k+1; l < points.length; l++) {				
						//if (i==j && j==k && k==l) continue;
				
						if (points[i].slopeTo(points[j]) == points[j].slopeTo(points[k]) &&
								points[j].slopeTo(points[k]) ==	points[k].slopeTo(points[l]))
						{
								segAux.add(new LineSegment(points[i], points[l]));
						}
				
					}
				}
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
	    
	    Thread.sleep(5000);
	    
	    // print and draw the line segments
	    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	}
}
