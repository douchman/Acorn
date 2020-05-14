package Minigame.Service;

import Minigame.BrkAcorn.BrkAcorn;
import Minigame.ServiceImpl.BrkacornService;

public class BrkacornServiceImpl implements BrkacornService {
	BrkAcorn brkAcorn;
	public BrkacornServiceImpl() {
		brkAcorn = new BrkAcorn();
	} 
	
	@Override
	public void runBrkacorn() {
		brkAcorn.runBrkAcorn();
	}
}
