package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import org.drools.core.ClockType;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.conf.EventProcessingOption;

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


	
	DroolsController(Data data, ThreadController threadController) {
		
		this.load = data;
		this.threadController = threadController;
		
		try {
			this.init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("restriction")
	public void init() throws IOException {
		if(this.kSession != null) {
			this.kSession.dispose();
		}
		Collection<ParamSet> paramSets = new ArrayList<ParamSet>();

		// populate paramSets

		paramSets.add( new ParamSet( "Necromobile.class" ) );
		paramSets.add( new ParamSet( "Reporter.class" ) );
		
		ObjectDataCompiler converter = new ObjectDataCompiler();
		InputStream templateStream =

		    new FileInputStream(new File( "resources/templates/template.drt" ));
		
		String drl = converter.compile( paramSets, templateStream );
		
		paramSets.clear();
		ParamSet set = new ParamSet("Assasin.class");
		paramSets.add(set);
		set = new ParamSet("Millitant.class");
		paramSets.add(set);
		set = new ParamSet("Chief.class");
		paramSets.add(set);
		set = new ParamSet("Diplomat.class");
		paramSets.add(set);
		set = new ParamSet("Reporter.class");
		paramSets.add(set);
		
		templateStream =

			    new FileInputStream( "resources/templates/non_corpse_eaters.drt" );
			
		drl += "\n"+converter.compile( paramSets, templateStream );
			
		
		templateStream = new FileInputStream( "resources/rules/Sample.drl" );
		
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
//        		this.kSession.setGlobal("game", receivedMessage);
        		Game game = (Game) receivedMessage;
        		for(Player p : game.getPlayers()) {
        			this.addToRules(p);
        			for(Piece piece : p.getPieces()) {
        				this.addToRules(piece);
        			}
        		}
        		this.addToRules(game.getRoot());
        		this.addToRules(game);
        	} else {
        		System.out.println(receivedMessage.getClass());
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
		try {
			sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		
		KieBaseConfiguration kbconf = kieServices.newKieBaseConfiguration();
		kbconf.setOption(EventProcessingOption.STREAM);
		
		this.kieBase = kieContainer.newKieBase(kbconf);
		
		
		KieSessionConfiguration ksconf1 = kieServices.newKieSessionConfiguration();
        ksconf1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
        return kieBase.newKieSession(ksconf1, null);
		
    }

}
