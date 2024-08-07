package tugas;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*Yohanes Babtista David R.	/ 185314012
Tiansi Miranda Sitinjak		/  185314096
Agustina Budi Stevani		/ 185314114
Laurensius Ferdinan Putra N.	/ 185314122
Dyline Melynea Fernandez	/ 185314125	
*/

public class TekaTeki {
	
	public int dimensi = 3;
	
	// bawah, kiri, atas, kanan
	int[] baris = { 1, 0, -1, 0 };
	int[] kolom = { 0, -1, 0, 1 };
	
	public int hitungCost(int[][] awal, int[][] goal) {
		int count = 0;
		int n = awal.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (awal[i][j] != 0 && awal[i][j] != goal[i][j]) {
					count++;
				}
			}
		}
		return count;
	}
	
	public void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public boolean isSafe(int x, int y) {
		return (x >= 0 && x < dimensi && y >= 0 && y < dimensi);
	}
	
	public void printPath(Node root) {
		if (root == null) {
			return;
		}
		printPath(root.parent);
		printMatrix(root.matrix);
		System.out.println();
	}
	
	public boolean isSolvable(int[][] matrix) {
		int count = 0;
		List<Integer> array = new ArrayList<Integer>();
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				array.add(matrix[i][j]);
			}
		}
		
		Integer[] anotherArray = new Integer[array.size()];
		array.toArray(anotherArray);
		
		for (int i = 0; i < anotherArray.length - 1; i++) {
			for (int j = i + 1; j < anotherArray.length; j++) {
				if (anotherArray[i] != 0 && anotherArray[j] != 0 && anotherArray[i] > anotherArray[j]) {
					count++;
				}
			}
		}
		
		return count % 2 == 0;
	}
	
	public void solve(int[][] awal, int[][] goal, int x, int y) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>(1000, (a, b) -> (a.cost + a.level) - (b.cost + b.level));
		Node root = new Node(awal, x, y, x, y, 0, null);
		root.cost = hitungCost(awal, goal);
		pq.add(root);
		
		while (!pq.isEmpty()) {
			Node min = pq.poll();
			if (min.cost == 0) {
				printPath(min);
				return;
			}
			
			for (int i = 0; i < 4; i++) {
	            if (isSafe(min.x + baris[i], min.y + kolom[i])) {
	            	Node child = new Node(min.matrix, min.x, min.y, min.x + baris[i], min.y + kolom[i], min.level + 1, min);
	            	child.cost = hitungCost(child.matrix, goal);
	            	pq.add(child);
	            }
	        }
		}
	}
	
	public static void main(String[] args) {
		int[][] awal = { {1, 8, 2}, {0, 4, 3}, {7, 6, 5} };
		int[][] goal    = { {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };
		
		// koordinat kotak putih
		int x = 1, y = 0;
		
		TekaTeki puzzle = new TekaTeki();
		if (puzzle.isSolvable(awal)) {
			puzzle.solve(awal, goal, x, y);
		} 
		else {
			System.out.println("Matriks awalan tidak memungkinkan untuk dipecahkan");
		}
	}
}
