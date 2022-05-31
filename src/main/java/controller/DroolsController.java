package controller;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import model.Game;
import model.MinMaxNode;
import model.Player;

public class DroolsController extends Thread {
	
	private static DroolsController instance = null;
	
	private KieServices ks;
	private KieContainer kContainer;
	private KieSession kSession;
	private Data load;
	private ThreadController threadController;
	
	public static DroolsController getInstance() {
		if(instance == null) {
			instance = ThreadController.getInstance().getDroolsControllerInstance();
		}
		return instance;
	}
	
	
	DroolsController(Data data, ThreadController threadController) {
		this.ks = KieServices.Factory.get();
		this.kContainer = ks.getKieClasspathContainer();
		this.kSession = kContainer.newKieSession("ksession-rules");
		this.load = data;
		this.threadController = threadController;
	}
	
	public void run() {
        for(Object receivedMessage = load.receive();
          !"Game Over".equals(receivedMessage);
          receivedMessage = load.receive()) {
            
        	if(receivedMessage instanceof Game) {
        		this.kSession.setGlobal("game", receivedMessage);
        		Game game = (Game) receivedMessage;
        		for(Player p : game.getPlayers()) {
        			this.addToRules(p);
        		}
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

}
