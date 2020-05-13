package Minigame.Roulette;

import java.util.List;

import javafx.application.Platform;
import javafx.scene.Group;

public class RouletteThread extends Thread{
	
	private Group gr;
	private boolean stop;
	private double rotateValueStart=0;
	private long sleepValue=0;
	private int increaseNanoValue = 1000;
	private Roulette roulette;
	
	public RouletteThread(Roulette roulette,Group gr) {
		this.gr = gr;
		this.roulette = roulette;
	}
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
			if(!stop) {
				rotateValueStart++;
				Thread.sleep(1);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						//System.out.println("run");
						gr.setRotate(rotateValueStart);				
					}
				});
				}
			
				else if (stop == true) {		
					while(increaseNanoValue<650000) {
						rotateValueStart++;
						//System.out.println();
						Thread.sleep(sleepValue,increaseNanoValue);	
						//Thread.sleep(sleepValue);
						//System.out.println(increaseNanoValue);
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								//System.out.println("run");
								gr.setRotate(rotateValueStart);
								increaseNanoValue+=1000;
								if(increaseNanoValue%100000==0)
									sleepValue+=2;
								else if(increaseNanoValue%50000==0)
									sleepValue+=4;					
							}
							});
				}
								
					//System.out.println(rotateValueStart);
					
					List<RoulettePiece> list = roulette.listrouletteContents2;
					
					for(int i =0; i<list.size();i++) {
						//roulette.listrouletteContents2
						double Degree = Math.toRadians(rotateValueStart);
						double cos = Math.cos(Degree);
						double sin = Math.sin(Degree);
						
						double sx = list.get(i).areaValueX - 200;
						double sy = list.get(i).areaValueY - 200;
						double rx ,ry;
						/*
						if( (sx * cos  - sy*sin)<0 ) {
							rx = -(sx * cos  - sy*sin)+200;
							ry = -(sx * sin  - sy*cos)+200;
						}
						else {
							rx = (sx * cos  - sy*sin)+200;
							ry = (sx * sin  - sy*cos)+200;
						}
						*/
						rx = (sx * cos  - sy*sin)+200;
						ry = (sx * sin  + sy*cos)+200;
						list.get(i).setArea(rx, ry);
					}
					
					
					roulette.result();

				break;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//System.out.println(Thread.currentThread().getName());
	}

}
