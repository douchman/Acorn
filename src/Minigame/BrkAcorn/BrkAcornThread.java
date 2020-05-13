package Minigame.BrkAcorn;

import javafx.application.Platform;

public class BrkAcornThread extends Thread{
	
	BrkAcorn window;
	private int millSec,Sec, Min;
	private boolean flag;
	private long time=0l, preTime=0l;
	
	
	public BrkAcornThread(BrkAcorn window) {
		
		this.flag = true;
		this.window = window;
		
		if(time !=0)
			preTime = System.currentTimeMillis()-time;
		else {
			preTime = System.currentTimeMillis();
		}
		
	}
	
	public void setStop(boolean flag) {
		this.flag = flag;
		window.gameSet();
	}
	
	Runnable timer = new Runnable() {
		
		@Override
		public void run() {
			window.setLabel(
					String.format("%02d : %02d : %02d",Min,Sec,millSec));
		}
	};
	@Override
	public void run() {
		while(flag) {
		try {		
			Thread.sleep(10);
			time  = System.currentTimeMillis()-preTime;
			Min = (int)(time / 1000.0 /60.0);
			Sec = (int)(time % (1000.0 *60) / 1000.0);
			millSec = (int)(time % 1000/10.0);
						
			Platform.runLater(timer);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}

	
}
