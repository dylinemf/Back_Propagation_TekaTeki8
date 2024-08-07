package tugas;

/*Yohanes Babtista David R.	/ 185314012
Tiansi Miranda Sitinjak		/  185314096
Agustina Budi Stevani		/ 185314114
Laurensius Ferdinan Putra N.	/ 185314122
Dyline Melynea Fernandez	/ 185314125	
*/

public class Node {
    public Node parent;
	public int[][] matrix;
	
	// koordinat kotak kosong
	public int x, y;
	
	// jumlah kotak yang tidak sesuai tempatnya
	public int cost;
	
	// Jumlah gerakan sejauh ini
	public int level;
	
	public Node(int[][] matrix, int x, int y, int newX, int newY, int level, Node parent) {
		this.parent = parent;
		this.matrix = new int[matrix.length][];
		for (int i = 0; i < matrix.length; i++) {
			this.matrix[i] = matrix[i].clone();
		}
		// tukar nilai
		this.matrix[x][y]       = this.matrix[x][y] + this.matrix[newX][newY];
		this.matrix[newX][newY] = this.matrix[x][y] - this.matrix[newX][newY];
		this.matrix[x][y]       = this.matrix[x][y] - this.matrix[newX][newY];
		
		this.cost = Integer.MAX_VALUE;
		this.level = level;
		this.x = newX;
		this.y = newY;
	}
}
