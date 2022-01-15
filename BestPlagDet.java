import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BestPlagDet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		// list to store result
		ArrayList<Tuple> list = new ArrayList<Tuple>();

		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			String name = sc.next();

			int copysuspected = sc.nextInt();
			int totsus = sc.nextInt();
			int totcop = sc.nextInt();

			int hour = sc.nextInt();
			int min = sc.nextInt();
			int sec = sc.nextInt();

			// fscore
			double precision = copysuspected * 1.0 / totsus;
			double recall = copysuspected * 1.0 / totcop;
			double fscore = (2 * precision * recall) / (precision + recall);

			// execution time
			long executiontime = hour * 3600 + min * 60 + sec;

			// add to list
			list.add(new Tuple(name, fscore, executiontime, i));
		}
		
		// sort
		Collections.sort(list);
		
		for (int i = 0; i < n; i++) {
			System.out.println(list.get(i).name);
			// System.out.println(list.get(i).name + " " + list.get(i).fscore + " " + list.get(i).executiontime + " " + list.get(i).inputorder);
		}

		sc.close();
	}

}

class Tuple implements Comparable<Tuple> {
	String name;
	double fscore;
	long executiontime;
	int inputorder;

	// constructor
	public Tuple(String name, double fscore, long executiontime, int inputorder) {
		this.name = name;
		this.fscore = fscore;
		this.executiontime = executiontime;
		this.inputorder = inputorder;
	}

	// to compare
	@Override
	public int compareTo(Tuple o) {
		// TODO Auto-generated method stub
		double rawresult = 0;

		if (Math.abs(o.fscore - this.fscore) >= 0.01) {
			rawresult = o.fscore - this.fscore;
		} else {
			// if fscore diff is less than 0.1, sort based on time
			if (this.executiontime != o.executiontime) {
				rawresult = this.executiontime - o.executiontime; // swapped since we prefer low value
			} else {
				// if execution time is the same, return the order
				rawresult = this.inputorder - o.inputorder; // swapped since we prefer low value
			}
		}

		// normalise the result regardless of the comparison
		if (rawresult > 0)
			return 1;
		else if (rawresult < 0)
			return -1;
		else
			return 0;
	}
}
