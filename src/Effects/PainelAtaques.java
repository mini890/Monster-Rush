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

public class PainelAtaques {
	private int spriteAtivaX;
	private int spriteAtivaY;
	private BufferedImage img;
	public AffineTransform posicao;
	private Properties properties;
	public int numeroAtaque;

	public void setNumeroAtaque(int numeroAtaque) {
		this.numeroAtaque = numeroAtaque;
	}

	public int getNumeroAtaque() {
		return numeroAtaque;
	}

	private int translateX = 640;
	private int translateY = 900;

	public PainelAtaques() {
		spriteAtivaX = 1;
		spriteAtivaY = 1;

		posicao = new AffineTransform();
		posicao.translate(translateX, translateY);

	}

	public void desenha(Graphics2D g2d) {

		if(numeroAtaque  == 0){		
		try {
			img = ImageIO.read(new File(Properties.getSwordActivated()));
		} catch (IOException e) {
			System.err.println("Erro a carregar resources/sprites/Painel_Attack/Sword_Activated.png");
		}}
		else if(numeroAtaque  == 4){		
			try {
				img = ImageIO.read(new File(Properties.getWaterActivated()));
			} catch (IOException e) {
				System.err.println("Erro a carregar resources/sprites/Painel_Attack/Water_Activated.png");
			}}
		else if(numeroAtaque  == 3){		
			try {
				img = ImageIO.read(new File(Properties.getFireActivated()));
			} catch (IOException e) {
				System.err.println("Erro a carregar resources/sprites/Painel_Attack/Fire_Activated.png");
			}}
		else if(numeroAtaque  == 2){		
			try {
				img = ImageIO.read(new File(Properties.getEarthActivated()));
			} catch (IOException e) {
				System.err.println("Erro a carregar resources/sprites/Painel_Attack/Earth_Activated.png");
			}}
		else if(numeroAtaque  == Properties.ATAQUE_AIR){		
			try {
				img = ImageIO.read(new File(Properties.getAirActivated()));
			} catch (IOException e) {
				System.err.println("Erro a carregar resources/sprites/Painel_Attack/Air_Activated.png");
			}}
		g2d.setTransform(posicao);
		g2d.drawImage(img, -400, -300, 400, 300, spriteAtivaX * 800 - 800, spriteAtivaY * 90 - 90, spriteAtivaX * 800,
				spriteAtivaY * 800, null);
	}

	public AffineTransform getPosicao() {
		return posicao;
	}
}