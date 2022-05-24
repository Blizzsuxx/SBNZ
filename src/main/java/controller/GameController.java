package controller;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class GameController {
	
	private static GameController instance = null;
	
	private KieServices ks;
	private KieContainer kContainer;
	private KieSession kSession;
	public static GameController getInstance() {
		if(instance == null) {
			instance = new GameController();
		}
		return instance;
	}
	
	
	private GameController() {
		this.ks = KieServices.Factory.get();
		this.kContainer = ks.getKieClasspathContainer();
		this.kSession = kContainer.newKieSession("ksession-rules");
	}
	
	public void initRules() {
		
	}
	
	public void addToRules(Object o) {
		this.kSession.insert(o);
	}
	
	public void fireAllRules() {
		this.kSession.fireAllRules();
	}

}
