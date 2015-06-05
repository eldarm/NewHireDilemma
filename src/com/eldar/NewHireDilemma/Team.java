package com.eldar.NewHireDilemma;

import java.awt.Color;
import java.awt.Graphics;

public class Team {
	public static Boolean colorVersion = false;
	public int capacity = 10;
	public int vacancy = 1;
	public int quality = 0;  // 0-100
	
	public static final int margin = 5;
	
	public Team(int capacity, int vacancy, int quality) {
		this.capacity = capacity;
		this.vacancy = vacancy;
		this.quality = quality;
	}
	
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
	
	public void Draw(Graphics g, int x, int y, int width, int heightTotal) {
	  int textSpace = 20;
	  int height = heightTotal - textSpace - 2;
	  int rectX = x + margin;
	  int rectY = y + margin + textSpace;
	  int rectW = Math.min(width - 2 * margin, (int)((double)height / capacity)) ;
	  int rectH = rectW * capacity;

	  int newColor = (int)(255.0 * quality / 100.0);
	  if (colorVersion) {
		  g.setColor(new Color(255 - newColor, newColor, 0));  
	  } else {
		  g.setColor(new Color(newColor, newColor, newColor));  
	  }
	  g.fillRect(rectX, rectY, rectW, rectH);
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
	  g.setColor(Color.WHITE);
	  for (int i=occupied; i < capacity; ++i) {
		  g.fillOval(memX, memY + i * rectW, memW, memW);
	  }
	  g.setColor(Color.BLACK);
	  for (int i=0; i < capacity; ++i) {
		  g.drawOval(memX, memY + i * rectW, memW, memW);
	  }
	  g.drawString(String.format("%02d", quality), memX, y + textSpace - 3);
	}
}
