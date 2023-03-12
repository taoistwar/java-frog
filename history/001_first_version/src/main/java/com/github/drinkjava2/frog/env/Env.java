package com.github.drinkjava2.frog.env;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import com.github.drinkjava2.frog.Frog;
import com.github.drinkjava2.frog.egg.Egg;
import com.github.drinkjava2.frog.util.EggTool;

/**
 * Env is the living space of frog. draw it on JPanel
 */
@SuppressWarnings("serial")
public class Env extends JPanel {
	/** Speed of test */
	public static int SHOW_SPEED = 1;

	/** Steps of one test round */
	public static int STEPS_PER_ROUND = 3000;

	/** Delete eggs at beginning of each run */
	public static final boolean DELETE_EGGS = true;// 每次运行是否先删除保存的蛋

	/** Virtual environment x size is 500 pixels */
	public int ENV_X_SIZE = 300;

	/** Virtual environment y size is 500 pixels */
	public int ENV_Y_SIZE = 300;

	public byte[][] foods = new byte[ENV_X_SIZE][ENV_Y_SIZE];

	public int FOOD_QTY = 2000; // as name

	public int EGG_QTY = 80; // as name

	public List<Frog> frogs = new ArrayList<Frog>();
	public List<Egg> eggs;

	static {
		if (DELETE_EGGS) {
			for (int i = 0; i < 1000000; i++) {
				// 杀生前先打印往生咒，见码云issue#IW4H8
				System.out.println("唵缚悉波罗摩尼莎诃!");
			}
			EggTool.deleteEggs();
		}
	}

	public Env() {
		super();
		this.setLayout(null);// 空布局
		this.setBounds(100, 100, ENV_X_SIZE, ENV_Y_SIZE);
	}

	private void rebuildFrogAndFood() {
		frogs.clear();
		for (int i = 0; i < ENV_X_SIZE; i++) {// clear foods
			for (int j = 0; j < ENV_Y_SIZE; j++) {
				foods[i][j] = 0;
			}
		}
		Random rand = new Random();
		for (int i = 0; i < eggs.size(); i++) { // 1个Egg生出4个Frog
			for (int j = 0; j < 4; j++) {
				frogs.add(new Frog(ENV_X_SIZE / 2 + rand.nextInt(90), ENV_Y_SIZE / 2 + rand.nextInt(90), eggs.get(i)));
			}
		}
		System.out.println("Created " + 4 * eggs.size() + " frogs");
		for (int i = 0; i < FOOD_QTY; i++)
			foods[rand.nextInt(ENV_X_SIZE - 3)][rand.nextInt(ENV_Y_SIZE - 3)] = 1;
	}

	private void drawFood(Graphics g) {
		for (int x = 0; x < ENV_X_SIZE; x++)
			for (int y = 0; y < ENV_Y_SIZE; y++)
				if (foods[x][y] > 0) {
					g.fillOval(x, y, 4, 4);
				}
	}

	public void run() throws InterruptedException {
		EggTool.loadEggs(this); // 从磁盘加载egg，或新建一批egg
		int round = 1;
		Image buffImg = createImage(this.getWidth(), this.getHeight());
		Graphics g = buffImg.getGraphics();
		long t1, t2;
		do {
			t1 = System.currentTimeMillis();
			rebuildFrogAndFood();
			boolean allDead = false;
			for (int i = 0; i < STEPS_PER_ROUND; i++) {
				if (allDead)
					break;
				allDead = true;
				for (Frog frog : frogs) {
					if (frog.active(this))
						allDead = false;
					if (frog.alive && frog.moveCount == 0 && i > 100) {// 如果不移动就死!
						frog.alive = false;
					}
				}
				if (i % SHOW_SPEED != 0) // 画青蛙会拖慢速度
					continue;
				// 开始画青蛙
				g.setColor(Color.white);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				g.setColor(Color.BLACK);
				for (Frog frog : frogs)
					frog.show(g);

				drawFood(g);
				Graphics g2 = this.getGraphics();
				g2.drawImage(buffImg, 0, 0, this);
				Thread.sleep(10);
			}
			EggTool.layEggs(this);
			t2 = System.currentTimeMillis();
			Application.mainFrame.setTitle("Frog test round: " + round++ + ", time used: " + (t2 - t1) + " ms");
		} while (true);
	}
}
