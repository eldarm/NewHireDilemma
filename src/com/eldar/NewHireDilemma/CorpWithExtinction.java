package com.eldar.NewHireDilemma;

import java.util.Random;

public class CorpWithExtinction extends Corp {
	private abstract static class QualityGenerator {
		protected static final Random rand = new Random();
		abstract int nextQuality();
	}
	
	private static class QGenGood extends QualityGenerator {
		int nextQuality() {
			return rand.nextInt(10) < 8 ? 90 : 0;
		}
	}
	
	private static class QGenMixed extends QualityGenerator {
		int nextQuality() {
			return rand.nextInt(91);
		}
	}
	
	private int generation = 0;
	private static QualityGenerator qgen; 

	protected CorpWithExtinction(int teamsCount, int capacity, int vacancy, int quality) {
		super(teamsCount, capacity, vacancy, quality);
	}
	
	public static Corp getGoodCompany() {
		qgen = new QGenGood();
		return initGoodCompany(new CorpWithExtinction(10, 10, 1, 90));
	}
	
	public static Corp getMixedCompany() {
		qgen = new QGenMixed();
		return initMixedCompany(new CorpWithExtinction(10, 10, 1, 0));
	}
	
	public void Mutate() {
		if (++generation % 10 == 0) {
			int worstIndex = 0;
			int maxVacancy = 0;
			for (int i=0; i < teams.length; i++) {
				if (teams[i].vacancy > maxVacancy) {
					worstIndex = i;
					maxVacancy = teams[i].vacancy;
				}
			}
			teams[worstIndex].setQuality(qgen.nextQuality());
		}
		super.Mutate();
	}
	
}
