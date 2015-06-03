package com.eldar.NewHireDilemma;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Corp {
    public static final int capacity = 10;
    public static final int vacant = 1;
	private static Random rand = new Random();

    public Team[] teams;

	private Corp(int teamCount) {
		teams = new Team[teamCount];
		for (int i=0; i < teams.length; ++i) {
			teams[i] = new Team();
		}
	}
	
	public static Corp getGoodCompany() {
		Corp corp = new Corp(10);
		for (int i=0; i < corp.teams.length; ++i) {
			corp.teams[i].setQuality(i < corp.teams.length - 2 ? 90 : 0);
		}
		return corp;
	}
	
	public void Mutate() {
		// TODO: something real.
		int i = rand.nextInt(teams.length);
		if (teams[i].capacity - teams[i].vacancy > 0) teams[i].vacancy++; 
	}
	
	public void Draw(Graphics g, int width, int height) {
	  g.clearRect(0, 0, width, height);
	  final int step = Math.min(width / teams.length, (height - 2) / capacity + Team.margin * 2);
	  for  (int i=0; i < teams.length; ++i) {
		  teams[i].Draw(g, i * step, 0, step, height);
	  }
	  Mutate();
	}
}
