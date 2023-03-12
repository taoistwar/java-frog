package com.github.drinkjava2.frog.env;

import java.io.File;

import javax.swing.JFrame;

/**
 * Application will build env, frogs and let them run
 */
public class Application {
	public static final String CLASSPATH;
	static {
		String classpath = new File("").getAbsolutePath();
		int i = classpath.lastIndexOf("\\frog\\");
		if (i > 0)
			CLASSPATH = classpath.substring(0, i) + "\\frog\\";// windows
		else
			CLASSPATH = classpath + "/"; // UNIX
	}
	public static JFrame mainFrame = new JFrame();

	public static void main(String[] args) throws InterruptedException {
		Env env = new Env();

		mainFrame.setLayout(null);
		mainFrame.setSize(520, 550); // 窗口大小
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭时退出程序
		mainFrame.add(env);
		mainFrame.setVisible(true);
		env.run();
	}

}
