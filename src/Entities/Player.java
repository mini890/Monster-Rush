package Entities;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import main.Properties;

public class Player {

	Timer t;

	private int spriteAtivaX;
	private int spriteAtivaY;
	private BufferedImage img;
	private AffineTransform posicao;
	private Properties properties;
	private int valorDeAtaqueHeroi;
	private int ValorMagiaHeroi;

	public Player() {
		ValorMagiaHeroi = 3;
		valorDeAtaqueHeroi = 1;
		spriteAtivaX = 1;
		spriteAtivaY = 3;
		posicao = new AffineTransform();
		posicao.translate(32, 320 - 24);
		try {
			img = ImageIO.read(new File(Properties.getPlayerSpritesheet()));
		} catch (IOException e) {
			System.err.println("Erro ao carregar o player");
		}

		t = new Timer();
		t.scheduleAtFixedRate(new Anima(), 0, 100);
	}

	public void desenha(Graphics2D g2d) {
		g2d.setTransform(posicao);
		g2d.drawImage(img, -32, -48, 32, 48, spriteAtivaX * 32 - 32, spriteAtivaY * 48 - 48, spriteAtivaX * 32,
				spriteAtivaY * 48, null);
	}

	public int getValorDeAtaqueHeroi() {
		return valorDeAtaqueHeroi;
	}

	public void setValorDeAtaqueHeroi(int valorDeAtaqueHeroi) {
		this.valorDeAtaqueHeroi = valorDeAtaqueHeroi;
	}

	public int getValorMagiaHeroi() {
		return ValorMagiaHeroi;
	}

	public void setValorMagiaHeroi(int valorMagiaHeroi) {
		ValorMagiaHeroi = valorMagiaHeroi;
	}

	class Anima extends TimerTask {
		@Override
		public void run() {
			
			spriteAtivaX++; 
			if (spriteAtivaX == 4) 
				spriteAtivaX = 1;
			 
		}

	}
}