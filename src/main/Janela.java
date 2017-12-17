package main;

import javax.swing.JFrame;

public class Janela extends JFrame {

	public Janela() {
		add(new Tela());
		setTitle("Monster Rush");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(true);
	}

	public static void main(String[] args) {
		new Janela();
	}

}