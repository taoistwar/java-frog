package com.github.drinkjava2.frog;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.github.drinkjava2.frog.egg.Egg;
import com.github.drinkjava2.frog.egg.FrogEggTool;
import com.github.drinkjava2.frog.egg.SnakeEggTool;
import com.github.drinkjava2.frog.objects.EnvObject;
import com.github.drinkjava2.frog.objects.Food;
import com.github.drinkjava2.frog.objects.Material;
import com.github.drinkjava2.frog.snake.Snake;
import com.github.drinkjava2.frog.util.RandomUtils;

/**
 * Env is the living space of frog. draw it on JPanel
 * 
 * @author Yong Zhu
 * @since 1.0
 */
@SuppressWarnings("all")
public class Env extends JPanel {
	/** Speed of test */
	public static int SHOW_SPEED = 10; // 测试速度，-1000~1000,可调, 数值越小，速度越慢

	/** Delete eggs at beginning of each run */
	public static final boolean DELETE_FROG_EGGS = true;// 每次运行是否先删除保存的青蛙蛋

	public static final int FROG_EGG_QTY = 25; // 每轮下n个青蛙蛋，可调，只有最优秀的前n个青蛙们才允许下蛋

	public static final int FROG_PER_EGG = 4; // 每个青蛙蛋可以孵出几个青蛙

	public static final int SCREEN = 1; // 分几屏测完

	public static final int FROG_PER_SCREEN = FROG_EGG_QTY * FROG_PER_EGG / SCREEN; // 每屏上显示几个青蛙，这个数值由上面三个参数计算得来

	/** Frog's brain size is a 3D array of Cell */ // 脑空间是个三维Cell数组，为节约内存，仅在用到数组元素时才去初始化这维，按需分配内存
	public static final int FROG_BRAIN_XSIZE = 1000; // frog的脑在X方向长度
	public static final int FROG_BRAIN_YSIZE = 1000; // frog的脑在Y方向长度
	public static final int FROG_BRAIN_ZSIZE = 1000; // frog的脑在Z方向长度

	/** SHOW first frog's brain structure */
	public static boolean SHOW_FIRST_FROG_BRAIN = true; // 是否显示脑图在Env区的右侧

	/** Draw first frog's brain after some steps */
	public static int DRAW_BRAIN_AFTER_STEPS = 1; // 以此值为间隔动态画出脑图，设为0则关闭这个动态脑图功能，只显示一个静态、不闪烁的脑图

	/** Environment x width, unit: pixels */
	public static final int ENV_WIDTH = 400; // 虚拟环境的宽度, 可调

	/** Environment y height, unit: pixels */
	public static final int ENV_HEIGHT = ENV_WIDTH; // 虚拟环境高度, 可调，通常取正方形

	/** Frog's brain display width on screen, not important */
	public static final int FROG_BRAIN_DISP_WIDTH = 600; // Frog的脑图在屏幕上的显示大小,可调

	/** Steps of one test round */
	public static final int STEPS_PER_ROUND = 2000;// 每轮测试步数,可调
	public static int step;// 当前测试步数

	public static final int FOOD_QTY = 1500; // 食物数量, 可调
	public static boolean FOOD_CAN_MOVE = false; // 食物是否可以移动，眼花

	// 以下是程序内部变量，不要手工修改它们
	public static boolean pause = false; // 暂停按钮按下将暂停测试

	public static int[][] bricks = new int[ENV_WIDTH][ENV_HEIGHT];// 组成环境的材料，见Material.java

	public static List<Frog> frogs = new ArrayList<>(); // 这里存放所有待测的青蛙，可能分几次测完，由FROG_PER_SCREEN大小来决定

	public static List<Egg> frog_eggs = new ArrayList<>(); // 这里存放新建或从磁盘载入上轮下的蛋，每个蛋可能生成几个青蛙，

	public static EnvObject[] things = new EnvObject[] { new Food() };// 所有外界物体，如食物、字母测试工具都放在这个things里面

	// =========以下是与蛇相关的常量和变量===========

	public static final boolean DELETE_SNAKE_EGGS = true;// 每次运行是否先删除保存的蛇蛋

	public static boolean SNAKE_MODE = true; // 是否加小蛇加进来吃青蛙?

	public static final int SNAKE_EGG_QTY = 10; // 每轮下n个蛇蛋，可调，只有最优秀的前n个蛇们才允许下蛋

	public static final int SNAKE_PER_EGG = 4; // 每个蛇蛋可以孵出几个蛇

	public static final int SNAKE_PER_SCREEN = SNAKE_EGG_QTY * SNAKE_PER_EGG / SCREEN; // 每屏上显示几个蛇，这个数值由上面参数计算得来

	public static List<Snake> snakes = new ArrayList<>(); // 这里存放所有待测的蛇，可能分几次测完，由FROG_PER_SCREEN大小来决定

	public static List<Egg> snake_eggs = new ArrayList<>(); // 这里存放新建或从磁盘载入上轮下的蛋，每个蛋可能生成几个蛇

	static {
		System.out.println("唵缚悉波罗摩尼莎诃!"); // 杀生前先打印往生咒，见码云issue#IW4H8
		System.out.println("脑图快捷键： T:顶视  F：前视  L:左视  R:右视  X:斜视  方向键：剖视  空格:暂停  鼠标：缩放旋转平移");
		if (DELETE_FROG_EGGS)
			FrogEggTool.deleteEggs();
		if (DELETE_SNAKE_EGGS)
			SnakeEggTool.deleteEggs();
	}

	public Env() {
		super();
		this.setLayout(null);// 空布局
		this.setBounds(1, 1, ENV_WIDTH, ENV_HEIGHT);
	}

	public static boolean insideEnv(int x, int y) {// 如果指定点在边界内
		return !(x < 0 || y < 0 || x >= ENV_WIDTH || y >= ENV_HEIGHT);
	}

	public static boolean outsideEnv(int x, int y) {// 如果指定点超出边界
		return x < 0 || y < 0 || x >= ENV_WIDTH || y >= ENV_HEIGHT;
	}

	public static boolean closeToEdge(Frog f) {// 青蛙靠近边界? 离死不远了
		return f.x < 20 || f.y < 20 || f.x > (Env.ENV_WIDTH - 20) || f.y > (Env.ENV_HEIGHT - 20);
	}

	public static boolean foundAnyThing(int x, int y) {// 如果指定点看到任意东西或超出边界
		return x < 0 || y < 0 || x >= ENV_WIDTH || y >= ENV_HEIGHT || Env.bricks[x][y] >= Material.VISIBLE;
	}

	public static boolean foundFrog(int x, int y) {// TODO:优化寻找青蛙的速度，不要用循环 如果指定点看到青蛙或超出边界，返回true
		if (x < 0 || y < 0 || x >= ENV_WIDTH || y >= ENV_HEIGHT)
			return true;
		for (Frog f : frogs)
			if (f.alive && f.x == x && f.y == y) {
				return true;
			}
		return false;
	}

	public static boolean foundAndAteFood(int x, int y) {// 如果x,y有食物，将其清0，返回true
		if (insideEnv(x, y) && Env.bricks[x][y] >= Material.FOOD && Env.bricks[x][y] <= Material.FLY4) {
			Food.food_ated++;
			bricks[x][y] = 0;
			return true;
		}
		return false;
	}

	public static boolean foundAndAteFrog(int x, int y) {// TODO:优化寻找青蛙的速度，不要用循环 如果x,y有青蛙，将其杀死，返回true
		for (Frog f : frogs)
			if (f.alive && f.x == x && f.y == y) {
				f.alive = false;
				return true;
			}
		return false;
	}

	private void rebuildFrogs() {// 根据蛙蛋重新孵化出蛙
		frogs.clear();
		for (int i = 0; i < frog_eggs.size(); i++) {// 创建青蛙，每个蛋生成n个蛙，并随机取一个别的蛋作为精子
			int loop = FROG_PER_EGG;
			if (frog_eggs.size() > 20) { // 如果数量多，进行一些优化，让排名靠前的Egg多孵出青蛙
				if (i < FROG_PER_EGG)// 0,1,2,3
					loop = FROG_PER_EGG + 1;
				if (i >= (frog_eggs.size() - FROG_PER_EGG))
					loop = FROG_PER_EGG - 1;
			}
			for (int j = 0; j < loop; j++) {
				Egg zygote = new Egg(frog_eggs.get(i), frog_eggs.get(RandomUtils.nextInt(frog_eggs.size())));
				frogs.add(new Frog(RandomUtils.nextInt(ENV_WIDTH), RandomUtils.nextInt(ENV_HEIGHT), zygote));
			}
		}
	}

	private void rebuildSnakes() {// 根据蛇蛋重新孵化出蛇
		snakes.clear();
		for (int i = 0; i < snake_eggs.size(); i++) {// 创建蛇，每个蛋生成n个蛇，并随机取一个别的蛋作为精子
			int loop = SNAKE_PER_EGG;
			if (snake_eggs.size() > 20) { // 如果数量多，进行一些优化，让排名靠前的Egg多孵出青蛙
				if (i < SNAKE_PER_EGG)// 0,1,2,3
					loop = SNAKE_PER_EGG + 1;
				if (i >= (snake_eggs.size() - SNAKE_PER_EGG))
					loop = SNAKE_PER_EGG - 1;
			}
			for (int j = 0; j < loop; j++) {
				Egg zygote = new Egg(snake_eggs.get(i), snake_eggs.get(RandomUtils.nextInt(snake_eggs.size())));
				snakes.add(new Snake(RandomUtils.nextInt(ENV_WIDTH), RandomUtils.nextInt(ENV_HEIGHT), zygote));
			}
		}
	}

	private void drawWorld(Graphics g) {
		int brick;
		for (int x = 0; x < ENV_WIDTH; x++)
			for (int y = 0; y < ENV_HEIGHT; y++) {
				brick = bricks[x][y];
				if (brick != 0) {
					g.setColor(Material.color(brick));
					if (brick >= Material.FOOD && brick <= Material.FLY4) {
						g.fillRoundRect(x, y, 4, 4, 2, 2); // 点模式 食物只有一个点太小，画大一点
						// g.drawString(String.valueOf(brick - Material.FOOD), x, y); // 数字雨模式
					} else
						g.drawLine(x, y, x, y); // only 1 point
				}
			}
		g.setColor(Color.BLACK);
	}

	static final NumberFormat format100 = NumberFormat.getPercentInstance();
	static {
		format100.setMaximumFractionDigits(2);
	}

	private String foodFoundCountText() {// 统计找食数等
		int maxFound = 0;
		for (Frog f : frogs)
			if (f.ateFood > maxFound)
				maxFound = f.ateFood;
		return new StringBuilder("找食率:").append(format100.format(Food.food_ated * 1.00 / FOOD_QTY)).append(", 平均: ")
				.append(Food.food_ated * 1.0f / FROG_PER_SCREEN).append("，最多:").append(maxFound).toString();
	}

	public static void checkIfPause(Frog f) {
		if (pause)
			do {
				if (f != null) {
					Application.brainPic.drawBrainPicture(f);
					Application.brainPic.requestFocus();
				}
				sleep(100);
			} while (pause);
	}

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		FrogEggTool.loadFrogEggs(); // 从磁盘加载蛙egg，或新建一批egg
		if (SNAKE_MODE)
			SnakeEggTool.loadSnakeEggs(); // 从磁盘加载蛇egg，或新建一批egg
		Image buffImg = createImage(this.getWidth(), this.getHeight());
		Graphics g = buffImg.getGraphics();
		long time0;// 计时用
		int round = 1;
		do {
			rebuildFrogs();
			if (SNAKE_MODE)
				rebuildSnakes();
			for (int screen = 0; screen < SCREEN; screen++) {// 分屏测试，每屏FROG_PER_SCREEN个蛙
				time0 = System.currentTimeMillis();
				for (EnvObject thing : things) // 创建食物、陷阱等物体
					thing.build();
				boolean allDead = false;
				Frog firstFrog = frogs.get(screen * FROG_PER_SCREEN);
				Snake firstSnake = null;

				for (int j = 0; j < FROG_PER_SCREEN; j++) {
					Frog f = frogs.get(screen * FROG_PER_SCREEN + j);
					f.initFrog(); // 初始化器官延迟到这一步，是因为脑细胞太占内存，而且当前屏测完后会清空
				}
				if (SNAKE_MODE && !snakes.isEmpty()) {
					firstSnake = snakes.get(screen * SNAKE_PER_SCREEN);
					for (int j = 0; j < SNAKE_PER_SCREEN; j++) {
						Snake s = snakes.get(screen * SNAKE_PER_SCREEN + j);
						s.initFrog(); // 初始化器官延迟到这一步，是因为脑细胞太占内存，而且当前屏测完后会清空
					}
				}
				Frog showFrog = Application.selectFrog ? firstFrog : firstSnake;
				for (step = 0; step < STEPS_PER_ROUND; step++) {
					for (EnvObject thing : things)// 调用食物、陷阱等物体的动作
						thing.active(screen);
					if (allDead)
						break; // 青蛙全死光了就直接跳到下一轮,以节省时间
					allDead = true;
					for (int j = 0; j < FROG_PER_SCREEN; j++) {
						Frog f = frogs.get(screen * FROG_PER_SCREEN + j);
						if (f.active(this))// 调用青蛙的Active方法，并返回是否还活着
							allDead = false;
					}
					if (SNAKE_MODE && !snakes.isEmpty())
						for (int j = 0; j < SNAKE_PER_SCREEN; j++) {
							Snake s = snakes.get(screen * SNAKE_PER_SCREEN + j);
							s.active(this);// snake唯一作用就是吃小蛇
						}

					if (SHOW_SPEED > 0 && step % SHOW_SPEED != 0) // 用是否跳帧画图的方式来控制速度
						continue;

					if (SHOW_SPEED < 0) // 如果speed小于0，人为加入延迟
						sleep(-SHOW_SPEED);

					// 开始画虚拟环境和青蛙和蛇
					g.setColor(Color.white);
					g.fillRect(0, 0, this.getWidth(), this.getHeight()); // 先清空虚拟环境
					g.setColor(Color.BLACK);
					drawWorld(g);// 画整个虚拟环境
					for (int j = 0; j < FROG_PER_SCREEN; j++) { // 显示青蛙
						Frog f = frogs.get(screen * FROG_PER_SCREEN + j);
						f.show(g);
					}
					if (SNAKE_MODE && !snakes.isEmpty())
						for (int j = 0; j < SNAKE_PER_SCREEN; j++) { // 显示蛇
							Snake s = snakes.get(screen * SNAKE_PER_SCREEN + j);
							s.show(g);
						}

					if (SHOW_FIRST_FROG_BRAIN && showFrog != null) {
						g.setColor(Color.red);
						g.drawArc(showFrog.x - 15, showFrog.y - 15, 30, 30, 0, 360);
						g.setColor(Color.BLACK);
					}
					if (DRAW_BRAIN_AFTER_STEPS > 0 && step % DRAW_BRAIN_AFTER_STEPS == 0)
						Application.brainPic.drawBrainPicture(showFrog);
					Graphics g2 = this.getGraphics();
					g2.drawImage(buffImg, 0, 0, this);
				}
				// System.out.println(showFrog.debugInfo());// 打印输出Frog调试内容
				if (SHOW_FIRST_FROG_BRAIN)
					Application.brainPic.drawBrainPicture(showFrog);
				checkIfPause(firstFrog);
				for (int j = 0; j < FROG_PER_SCREEN; j++) {
					Frog f = frogs.get(screen * FROG_PER_SCREEN + j);
					f.cells = null; // 清空frog脑细胞所占用的内存
				}
				Application.mainFrame
						.setTitle(new StringBuilder("Round: ").append(round).append(", screen:").append(screen)
								.append(", speed:").append(Env.SHOW_SPEED).append(", ").append(foodFoundCountText())
								.append(", 用时: ").append(System.currentTimeMillis() - time0).append("ms").toString());
				for (EnvObject thing : things)// 去除食物、陷阱等物体
					thing.destory();
			}
			round++;
			FrogEggTool.layEggs();
			if (SNAKE_MODE) {
				if (snakes.isEmpty())
					SnakeEggTool.loadSnakeEggs();
				else
					SnakeEggTool.layEggs();
			}
		} while (true);
	}

}
