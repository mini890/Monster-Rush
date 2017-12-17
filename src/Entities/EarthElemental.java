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

public class EarthElemental {
	private int maxVida;
	private int vida;
	private int spriteAtivaX;
	private int spriteAtivaY;
	private BufferedImage img;
	private AffineTransform posicao;
	private Properties properties;

	Timer t;

	private int translateX = 1300;
	private int translateY;

	public EarthElemental() {
		spriteAtivaX = 1;
		spriteAtivaY = 2;
		vida = 5;
		maxVida = 5;

		int lane = (int) (Math.random() * 3);
		switch (lane) {
		case 0:
			translateY = 110 - 32;
			break;

		case 1:
			translateY = 320 - 32;
			break;

		case 2:
			translateY = 530 - 32;
			break;
		}

		posicao = new AffineTransform();
		posicao.translate(translateX, translateY);
		try {
			img = ImageIO.read(new File(Properties.getEarthElementalSpritesheet()));
		} catch (IOException e) {
			System.err.println("Erro a carregar o Earth Elemental");
		}

		t = new Timer();
		t.scheduleAtFixedRate(new Anima(), 0, 100);

	}

	public void desenha(Graphics2D g2d) {
		g2d.setTransform(posicao);
		g2d.drawImage(img, -32, -48, 32, 48, spriteAtivaX * 32 - 32, spriteAtivaY * 48 - 48, spriteAtivaX * 32,
				spriteAtivaY * 48, null);
	}

	public AffineTransform getPosicao() {
		return posicao;
	}



	class Anima extends TimerTask {

		@Override
		public void run() {
			int speed = (int) (Math.random() * (8 - 2) + 2);
			posicao.translate(-speed, 0);
			spriteAtivaX++;
			if (spriteAtivaX == 4)
				spriteAtivaX = 1;
		}

	}



	public int getVida() {
		return vida;
	}

	public int getMaxVida() {
		return maxVida;
	}

	public void setMaxVida(int maxVida) {
		this.maxVida = maxVida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

}