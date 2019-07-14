package controller;

import model.Game;
import model.Location;
import model.NotImplementedException;
import model.Player;

/**
 * A DumbAI is a Controller that always chooses the blank space with the
 * smallest column number from the row with the smallest row number.
 */
public class DumbAI extends Controller {

	public DumbAI(Player me) {
		super(me);
		
		// TODO Auto-generated constructor stub
		//throw new NotImplementedException();
	}

	protected @Override Location nextMove(Game g) {
		// Note: Calling delay here will make the CLUI work a little more
		// nicely when competing different AIs against each other.
		 int h=0;
		 int hstore =100;
		 int col=0;
		 int row=1;
		for(int r=0; r<g.getBoard().NUM_ROWS; r++) {
			for(int c=0; c<g.getBoard().NUM_COLS; c++) {
				if(g.getBoard().get(r, c)==null) {
					h=h-1;
				}
			}
			if(h<hstore) {
				row=r;
				hstore=h;
				h=0;
				
			}
		}
		h=0;
		for(int c=0; c<g.getBoard().NUM_COLS; c++) {
			for(int r=0; r<g.getBoard().NUM_ROWS; r++) {
				if(g.getBoard().get(r, c)==null) {
					h=h-1;
				}
			}
			if(h<hstore) {
				col=c;
				hstore=h;
				h=0;
				
			}
		}
	System.out.println(row+" "+col);
		delay();
		if(g.getBoard().get(row, col)==null) {
		Location l= new Location(row,col);
		return l;
		}
		
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}
}
