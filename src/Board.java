/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/
package root;

import java.util.ArrayList;


public class Board {
	private ArrayList<ArrayList<Tile>> tiles;
	private int rowCount;
	private int columnCount;
	public ArrayList<ArrayList<Tile>> getTiles() {
		return tiles;
	}
	public int getRowCount() {
		return rowCount;
	}
	public int getColumnCount() {
		return columnCount;
	}
	public Board setTiles(ArrayList<ArrayList<Tile>> tiles) {
		this.tiles = tiles;
		return this;
	}
	public Board setRowCount(int rowCount) {
		this.rowCount = rowCount;
		return this;
	}
	public Board setColumnCount(int columnCount) {
		this.columnCount = columnCount;
		return this;
	}
}