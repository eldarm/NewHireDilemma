package com.eldar.NewHireDilemma;

import java.awt.Color;
import java.awt.Graphics;
//import java.util.Random;

public class Team {
	//protected static Random rand = new Random();
	
	public int capacity = 10;
	public int vacancy = 1;
	public int quality = 0;  // 0-100
	
	public static final int margin = 5;
	
	public Team() {}
	
	public Team setCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}
	
	public Team setVacancy(int vacancy) {
		this.vacancy = vacancy;
		return this;
	}
	
	public Team setQuality(int quality) {
		this.quality = quality;
		return this;
	}
	
	public void Draw(Graphics g, int x, int y, int width, int height) {
	  int rectX = x + margin;
	  int rectY = y + margin;
	  int rectW = Math.min(width - 2 * margin, (int)((double)height / capacity)) ;
	  int rectH = rectW * capacity;

	  g.setColor(Color.BLACK);
      g.drawRect(rectX, rectY, rectW, rectH);
      
	  int memX = rectX + margin;
	  int memW = rectW - 2* margin;
	  int memY = rectY + margin;
	  
	  int occupied = capacity - vacancy;
	  g.setColor(Color.BLUE);
	  for (int i=0; i < occupied; ++i) {
		  g.fillOval(memX, memY + i * rectW, memW, memW);
	  }
	  g.setColor(Color.BLACK);
	  for (int i=occupied; i < capacity; ++i) {
		  g.drawOval(memX, memY + i * rectW, memW, memW);
	  }
	}
}
