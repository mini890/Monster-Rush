package Effects;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import main.Properties;

public class BgImage {

	private int spriteAtivaX;
	private int spriteAtivaY;
	private BufferedImage img;
	public AffineTransform posicao;
	private int translateX = 500;
	private int translateY = 500;
	private int posicaoBg;
	private Timer t;

	public BgImage() {
		spriteAtivaX = 1;
		spriteAtivaY = 1;

		posicao = new AffineTransform();
		posicao.translate(translateX, translateY);
		try {
			img = ImageIO.read(new File(Properties.getBackgroundimage()));
		} catch (IOException e) {
			System.err.println("resources/sprites/Painel_Attack/test.png");
		}

		t = new Timer();
		t.scheduleAtFixedRate(new Anima(), 0, 30);
	}

	public void desenha(Graphics2D g2d) {
		g2d.setTransform(posicao);
		g2d.drawImage(img, -515, -500, 800, 625, spriteAtivaX * 0 - 0 + posicaoBg, spriteAtivaY * 0 - 0,
				spriteAtivaX * 1024 + posicaoBg, spriteAtivaY * 1024, null);
	}

	class Anima extends TimerTask {

		@Override
		public void run() {
			posicaoBg++;
			if (posicaoBg >= 4000) {
				posicaoBg = 0;
			}
		}

	}
}
