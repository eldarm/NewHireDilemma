package com.eldar.NewHireDilemma;

import java.awt.Graphics;
import java.util.Random;
import java.util.ArrayDeque;

public class Corp {
    public int capacity = 10;
    public int vacancy = 1;
	private static Random rand = new Random();
	private static final int MovingAverageWindows = 100;
	private ArrayDeque<Integer> values = new ArrayDeque(MovingAverageWindows);
	private int expectation = 0;
	private int averageExpectation = 0;

    public Team[] teams;

	protected Corp(int teamsCount, int capacity, int vacancy, int quality) {
		teams = new Team[teamsCount];
		this.capacity = capacity;
		this.vacancy = vacancy;
		for (int i=0; i < teams.length; ++i) {
			teams[i] = new Team(capacity, vacancy, quality);
		}
	}
	
	public static Corp getGoodCompany() {
		return initGoodCompany(new Corp(10, 10, 1, 90));
	}
	
	public static Corp getMixedCompany() {
		return initMixedCompany(new Corp(10, 10, 1, 0));
	}
	
	public static Corp getPoorCompany() {
		return new Corp(10, 10, 1, 50);
	}
	
	protected static Corp initGoodCompany(Corp corp) {
		for (int i=0; i < 2; ++i) {
			corp.teams[i].setQuality(0);
		}
		return corp;
	}
	
	protected static Corp initMixedCompany(Corp corp) {
		for (int i=0; i < corp.teams.length; ++i) {
			corp.teams[i].setQuality(i * 10);
		}
		return corp;
	}
	
	private void MoveOne(Team oldTeam) {
		final int compensatorForStupidity = 50;
		int total = 0;
		for (int i=0; i < teams.length; i++) {
			if (teams[i].vacancy > 0) {
			  total += teams[i].quality + compensatorForStupidity;
			}
		}
		int slotNumber = rand.nextInt(total);
		for (int i=0; i < teams.length; i++) {
			Team newTeam = teams[i];
			if (newTeam.vacancy > 0) {
				slotNumber -= newTeam.quality + compensatorForStupidity;
				if (slotNumber < 0) {
					newTeam.vacancy--;
					break;
				}
			}
		}
		oldTeam.vacancy++;
	}
	
	public void Mutate() {
		for (int i=0; i < teams.length; i++) {
			MoveOne(teams[i]);
		}
	}
	
	private void updateExpectations() {
	  // Calculate the expectation for a new hire:
	  int total = 0;
	  int vacancy = 0;
      for (Team team : teams) {
    	  total += team.quality * team.vacancy;
    	  vacancy += team.vacancy;
      }
      expectation = total / vacancy;
      if (values.size() > MovingAverageWindows) values.poll();
      values.add(expectation);
      int sum = 0;
      for (Integer e : values) sum += e;
      averageExpectation = sum/values.size();
 	}
	
	public void Draw(Graphics g, int width, int heightTotal) {
	  g.clearRect(0, 0, width, heightTotal);
	  final int textSpace = 20;
	  int height = heightTotal - textSpace;
	  final int step = Math.min(width / teams.length, (height - 2) / capacity + Team.margin * 2);
	  for  (int i=0; i < teams.length; ++i) {
		  teams[i].Draw(g, i * step, textSpace, step, height);
	  }
      updateExpectations();
      g.drawString(String.format("Expected quality: %d (average %d)", expectation, averageExpectation), 7, textSpace - 3);
	  Mutate();
	}
}
