package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import org.drools.template.ObjectDataCompiler;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.*;

import model.Game;
import model.Necromobile;
import model.ParamSet;
import model.Piece;
import model.Player;

public class DroolsController extends Thread {

	private static DroolsController instance = null;

	private KieSession kSession;
	private Data load;
	private ThreadController threadController;

	private KieBase kieBase;

	private KieContainer kieContainer;

	public static DroolsController getInstance() {
		if(instance == null) {
			instance = ThreadController.getInstance().getDroolsControllerInstance();
		}
		return instance;
	}


	@SuppressWarnings("restriction")
	DroolsController(Data data, ThreadController threadController) throws IOException {
		
		this.load = data;
		this.threadController = threadController;
		
		Collection<ParamSet> paramSets = new ArrayList<ParamSet>();

		// populate paramSets

		paramSets.add( new ParamSet( "Necromobile.class" ) );


		ObjectDataCompiler converter = new ObjectDataCompiler();
		InputStream templateStream =

		    this.getClass().getResourceAsStream( "/templates/template.drt" );
		
		String drl = converter.compile( paramSets, templateStream );
		
		templateStream = this.getClass().getResourceAsStream( "/rules/Sample.drl" );
		
		String sample = new String(templateStream.readAllBytes());
		
		sample += "\n" + drl;
		this.kSession = this.createKieSessionFromDRL(sample);
	}
	
	

	@Override
	public void run() {
        for(Object receivedMessage = load.receive();
          !"Game Over".equals(receivedMessage);
          receivedMessage = load.receive()) {

        	if(receivedMessage instanceof Game) {
        		this.kSession.setGlobal("game", receivedMessage);
        		Game game = (Game) receivedMessage;
        		for(Player p : game.getPlayers()) {
        			this.addToRules(p);
        			for(Piece piece : p.getPieces()) {
        				this.addToRules(piece);
        			}
        		}
        		this.addToRules(game.getRoot());
        	} else {
        		this.addToRules(receivedMessage);
        	}



        }
    }

	public void initRules() {

	}

	public void addToRules(Object o) {
		this.kSession.insert(o);
	}

	public void fireAllRules() {
		this.kSession.fireAllRules();
	}

	public void clearSession() {
		this.kSession.dispose();
	}


	public Data getLoad() {
		return load;
	}


	public void setLoad(Data load) {
		this.load = load;
	}
	
	
	private KieSession createKieSessionFromDRL(String drl){
		KieServices kieServices = KieServices.Factory.get();
		KieFileSystem kfs = kieServices.newKieFileSystem();
		System.out.println(drl);
		kfs.write( "src/main/resources/simple.drl", drl);
		KieBuilder kieBuilder = kieServices.newKieBuilder( kfs ).buildAll();
		Results results = kieBuilder.getResults();
		if( results.hasMessages( Message.Level.ERROR ) ){
		    System.out.println( results.getMessages() );
		    throw new IllegalStateException( "### errors ###" );
		}
		this.kieContainer =
		    kieServices.newKieContainer( kieServices.getRepository().getDefaultReleaseId() );
		this.kieBase = kieContainer.getKieBase();
		return kieContainer.newKieSession();
    }

}
