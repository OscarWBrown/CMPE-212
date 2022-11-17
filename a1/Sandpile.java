package a1;

public class Sandpile {

	/**
	 * Print a 2-dimensional array of cells using least 3 spaces for each value.
	 * Values for each row of the array appear on a single line, and each row
	 * appears on its own line.
	 * 
	 * @param cells a two-dimensional array
	 * @throws IllegalArgumentException if the specified array has a dimension equal
	 *                                  to zero
	 */
	public static void printCells(int[][] cells) {
		int rows = cells.length;
		if (rows <= 0) {
			throw new IllegalArgumentException("rows <= 0");
		}

		int cols = cells[0].length;
		if (cols <= 0) {
			throw new IllegalArgumentException("cols <= 0");
		}
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				int val = cells[r][c];
				System.out.printf("%3d", val);
			}
			System.out.println();
		}
	}

	// TRANSLATE THE REMAINING C FUNCTIONS INTO JAVA METHODS HERE
	// function to determine cell degree
	public static int degree(Index2 i, int[][] cells) {
		
		int rows=cells.length;
		int cols=cells[0].length;
		
		if (rows <= 0) {
			throw new IllegalArgumentException("rows <= 0");
		}
		if (cols <= 0) {
			throw new IllegalArgumentException("cols <=0");
		}
		
		if (i.row > rows ) {
			throw new IllegalArgumentException("Index, i, is not valid for array dimensions");
		}
		if (i.col > cols ) {
			throw new IllegalArgumentException("Index, i, is not valid for array dimensions");
		}
		
		int deg = 4;
		//check top side
		if (i.row == 0) {
			deg -= 1;
		}
		//check right side
		if (i.col == cols) {
			deg -= 1;
		}
		//check bottom side
		if (i.row == rows ) {
			deg -= 1;
		}
		//check left side
		if (i.col == 0) {
			deg -= 1;
			
		}
		System.out.println("Degree of cell is: " +deg);
		return deg;
		
	}

	
	
	public static void init(int grains, int[][] cells) {
		int rows=cells.length;
		int cols=cells[0].length;
		if (rows <= 0) {
			throw new IllegalArgumentException("rows <= 0");
		}
		if (cols <= 0) {
			throw new IllegalArgumentException("cols <=0");
		}
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				cells[r][c] = 0;
			}
		}
		cells[rows / 2][cols / 2] = grains;
	}
	
	public static Index2 firstToTopple(int[][] cells) {
		int rows=cells.length;
		int cols=cells[0].length;
		if (rows <= 0) {
			throw new IllegalArgumentException("rows <= 0");
		}
		if (cols <= 0) {
			throw new IllegalArgumentException("cols <=0");
		}
		Index2 idx = new Index2();
		for (int r = 0; r < rows; r++) {
			idx.row = r;
			for (int c = 0; c < cols; c++) {
				idx.col = c;
				int val = cells[r][c];
				if (val >= 4) {
					return idx;
				}
			}
			
		}
		idx.row = -1;
		idx.col = -1;
		return idx;
	}
	
	public static void topple(Index2 i, int[][] cells) {
		
		int rows=cells.length;
		int cols=cells[0].length;
		if (rows <= 0) {
			throw new IllegalArgumentException("rows <= 0");
		}
		if (cols <= 0) {
			throw new IllegalArgumentException("cols <=0");
		}
		int grains = cells[i.row][i.col];
		if (grains >= 4) {
			cells[i.row][i.col] -= 4;
		}
		if (i.row > 0) {
			cells[i.row - 1][i.col]++;
		}
		
		if (i.col < cols - 1) {
			cells[i.row][i.col + 1]++;
		}
		
		if (i.row < rows - 1) {
			cells[i.row + 1][i.col]++;
		}
		
		if (i.col > 0) {
			cells[i.row][i.col - 1]++;
		}
	}

	/**
	 * Creates a 15x15 sandpile simulation starting with 2 to-the-power 8 grains of
	 * sand on the center cell. The starting configuration of cells is printed to
	 * standard output and then the simulation is run until all cells reach a stable
	 * state (have fewer than 4 grains of sand). The stable configuration of cells
	 * is printed to standard output.
	 * 
	 * <p>
	 * Finally, an image of the stable configuration is shown.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		int[][] cells = new int[15][15];
		// FINISH TRANSLATING THE main FUNCTION HERE
		
		init((int) Math.pow(2, 8), cells);
		System.out.println("Original cells");
		printCells(cells);
		Index2 i = firstToTopple( cells);
		while(i.row >= 0) {
			topple(i,cells);
			i = firstToTopple(cells);
		}
		System.out.println("Final cells");
		printCells(cells);
		
		//input index
		Index2 idx = new Index2(1, 1);
		
		//call function to find degree
		degree(idx, cells);
		
		
		// THE NEXT TWO LINES SHOULD BE THE LAST LINES OF THE METHOD 
		// show an image of the stable configuration
		SandpileViewer.draw(cells);
	}
	
	
}



