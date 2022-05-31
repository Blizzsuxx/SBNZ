package controller;

import model.MinMaxNode;

public class Data {

	private Object packet;

	// True if receiver should wait
	// False if sender should wait
	private boolean transfer = true;

	public synchronized Object receive() {
		while (transfer) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Thread Interrupted");
			}
		}
		transfer = true;

		Object returnPacket = packet;
		notifyAll();
		return returnPacket;
	}

	public synchronized void send(Object packet) {
		while (!transfer) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Thread Interrupted");
			}
		}
		transfer = false;

		this.packet = packet;
		notifyAll();
	}

}
