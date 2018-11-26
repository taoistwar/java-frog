package com.github.drinkjava2.env;

import javax.swing.JFrame;

/**
 * Application will build env, frogs and let them run
 */
public class Application {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(Env.XSIZE+20, Env.YSIZE+40); // 窗口大小
		frame.setTitle("Stage#1: First Artifical Live"); // 标题
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭时退出程序

		Env env = new Env();
		frame.add(env);
 
		frame.setVisible(true);
	}

}
