package com.eldar.NewHireDilemma;

import java.awt.Graphics;

public class Corp {
    public static final int capacity = 10;
    public static final int vacant = 1;
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
	
	//public abstract void Simulate(); 
	
	public void Draw(Graphics g, int width, int height) {
	  final int step = height / capacity; //Math.min(width / teams.length, (height - Team.margin * 2) / capacity);
	  for  (int i=0; i < teams.length; ++i) {
		  teams[i].Draw(g, i * step, 0, step, height);
	  }
	}
}
