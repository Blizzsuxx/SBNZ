package controller;

public class ThreadController {

	private static ThreadController instance = null;

	private DroolsController droolsConctroller;
	private GameController gameController;
	private Data data;

	public static ThreadController getInstance() {
		if(instance == null) {
			instance = new ThreadController();
		}
		return instance;
	}

	private ThreadController() {
		this.data = new Data();
		this.gameController = this.getGameControllerInstance();
		this.droolsConctroller = this.getDroolsControllerInstance();
	}

	public DroolsController getDroolsControllerInstance() {

		if(this.droolsConctroller == null) {
			this.droolsConctroller = new DroolsController(this.getData(), this);
			this.droolsConctroller.start();
		}

		return this.droolsConctroller;
	}

	public GameController getGameControllerInstance() {

		if(this.gameController == null) {
			this.gameController = new GameController(this.getData(), this);
			this.gameController.start();
		}

		return this.gameController;
	}

	private Data getData() {
		if(this.data == null) {
			this.data = new Data();
		}
		return this.data;
	}

	public void end() {
		this.gameController.stop();
		this.droolsConctroller.stop();
	}


}
