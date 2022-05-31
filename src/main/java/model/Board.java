/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/
package model;


import java.util.ArrayList;



public class Board {
	private ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>();
	private int rowCount = 9;
	private int columnCount = 9;
	
	
	public Board() {
		for(int i = 0; i < this.columnCount; i++) {
			tiles.add( new ArrayList<Tile>());
		}
		for(int i = 0; i < this.columnCount; i++) {
			for(int j = 0; j < this.rowCount; j++) {
				tiles.get(i).add(new Tile(i, j));
			}
		}
	}
	
	
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
	
	public Tile getTile(int x, int y) {
		try {
		
			return this.tiles.get(x).get(y);
		}catch(Exception e) {
			return null;
		}
	}
}