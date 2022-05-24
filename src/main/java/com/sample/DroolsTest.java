package com.sample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import model.Piece;
import model.Tile;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");

            // go !
            Piece piece= new Piece();
            piece.setX(4);
            piece.setY(4);
            
            Tile tile = new Tile();
            tile.setX(4);
            tile.setY(4);
            
            Tile tile2 = new Tile();
            tile2.setX(5);
            tile2.setY(4);
            
            
            kSession.insert(piece);
            kSession.insert(tile);
            kSession.insert(tile2);
            kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    

}
