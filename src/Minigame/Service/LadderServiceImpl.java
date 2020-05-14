package Minigame.Service;

import Minigame.Ladder.Ladder;
import Minigame.ServiceImpl.LadderService;

public class LadderServiceImpl implements LadderService{
	private Ladder ladder;
	
	public LadderServiceImpl() {
		ladder = new Ladder();
	}
	
	@Override
	public void runLadder() {
		ladder.startLadder();
	}
}
