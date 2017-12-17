package Effects;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Properties;

public class Poder {

	private int spritAtivaX;
	private int spritAtivaY;
	private BufferedImage img;
	private AffineTransform posicao;
	private Properties properties;

	public Poder() {
		spritAtivaX = 1;
		spritAtivaY = 1;
		posicao = new AffineTransform();
		posicao.translate(64, 720 - 96);
	}

	public void desenha(Graphics2D g2d, int y) {
		try {
			img = ImageIO.read(new File(properties.getPoderesAnimacao()));
		} catch (IOException e) {
			System.err.println("Erro ao carregar " + properties.getPoderesAnimacao());
		}

		g2d.setTransform(posicao);
		for (int i = 0; i < 4; i++) {
			spritAtivaX++;
			g2d.drawImage(img, -32, -47, 32, 47, spritAtivaX * 32 - 32, y * 47 - 47, spritAtivaX * 32, y * 47, null);
		}
		spritAtivaX = 1;
	}

}