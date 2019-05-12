package com.kantin.controller;

public class RunnableThreadImpl implements Runnable{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
		try {
			System.out.print("Proses data ..");
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
