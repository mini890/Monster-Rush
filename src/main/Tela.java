package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JPanel;

import Effects.BgImage;
import Effects.PainelAtaques;
import Effects.Poder;
import Entities.AirElemental;
import Entities.EarthElemental;
import Entities.FireElemental;
import Entities.Player;
import Entities.WaterElemental;

public class Tela extends JPanel implements MouseListener, KeyListener {

	// Lane 1 = 110 - spriteY/2
	// Lane 2 = 320 - spriteY/2
	// Lane 3 = 530 - spriteY/2

	Properties properies;
	PainelAtaques painelAtaques;

	private Player player;
	private Clip clip;

	private int poder = 0;
	private int quantidadeInimigos = 0;
	private int score = 0;
	private int vidas = 3;

	private Timer t;
	private ArrayList<AirElemental> airElementals;
	private ArrayList<EarthElemental> earthElementals;
	private ArrayList<FireElemental> fireElementals;
	private ArrayList<WaterElemental> waterElementals;

	private BgImage bgImage;
	private boolean derrota = false;

	public Tela() {
		addMouseListener(this);
		addKeyListener(this);
		setFocusable(true);
		player = new Player();
		airElementals = new ArrayList<AirElemental>();
		earthElementals = new ArrayList<EarthElemental>();
		fireElementals = new ArrayList<FireElemental>();
		waterElementals = new ArrayList<WaterElemental>();
		painelAtaques = new PainelAtaques();

		playBgm(properies.getBackgroundmusic());
		bgImage = new BgImage();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		bgImage.desenha(g2d);
		player.desenha(g2d);

		if (derrota) {
			airElementals.clear();
			waterElementals.clear();
			fireElementals.clear();
			earthElementals.clear();
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, 32));
			g2d.drawString("Game Over", 520, 20);
			g2d.drawString("Score :" + score, 540, 50);
			g2d.drawString("Prima S para começar de novo", 400, 80);
			stopBgm();
		}

		if (!derrota) {
			g2d.setFont(new Font("TimesRoman", Font.BOLD, 16));
			g2d.setColor(Color.WHITE);
			g2d.drawString("Score: " + score, 0, -275);
			g2d.drawString(String.valueOf(vidas), 0, -50);
		}

		// painel_Ataques
		if (poder == properies.ATAQUE_NORMAL) {
			painelAtaques.setNumeroAtaque(0);
			painelAtaques.desenha(g2d);
		} else if (poder == properies.ATAQUE_AIR) {
			painelAtaques.setNumeroAtaque(1);
			painelAtaques.desenha(g2d);
		} else if (poder == properies.ATAQUE_FIRE) {
			painelAtaques.setNumeroAtaque(3);
			painelAtaques.desenha(g2d);
		} else if (poder == properies.ATAQUE_EARTH) {
			painelAtaques.setNumeroAtaque(2);
			painelAtaques.desenha(g2d);
		} else if (poder == properies.ATAQUE_WATER) {
			painelAtaques.setNumeroAtaque(4);
			painelAtaques.desenha(g2d);
		}

		t = new Timer();
		t.scheduleAtFixedRate(new Temporizador(), 0, 1000);

		for (AirElemental a : airElementals) {
			a.desenha(g2d);
			if (a.getPosicao().getTranslateX() < 64) {
				vidas--;
				a.getPosicao().setToTranslation(500000, 0);
			}
		}

		for (EarthElemental e : earthElementals) {
			e.desenha(g2d);
			if (e.getPosicao().getTranslateX() < 64) {
				vidas--;
				e.getPosicao().setToTranslation(500000, 0);
			}
		}

		for (FireElemental f : fireElementals) {
			f.desenha(g2d);
			if (f.getPosicao().getTranslateX() < 64) {
				vidas--;
				f.getPosicao().setToTranslation(500000, 0);
			}
		}

		for (WaterElemental w : waterElementals) {
			w.desenha(g2d);
			if (w.getPosicao().getTranslateX() < 64) {
				vidas--;
				w.getPosicao().setToTranslation(500000, 0);
			}
		}

		repaint();
	}

	class Temporizador extends TimerTask {
		@Override
		public void run() {
			if (vidas > 0) {
				if (quantidadeInimigos < 15) {
					int spawnProbality = (int) (Math.random() * 10000);
					if (spawnProbality == 10) {
						int elementalType = (int) (Math.random() * ((4 - 1) + 1)) + 1;
						switch (elementalType) {
						case 1:
							if (airElementals.size() < 10) {
								airElementals.add(new AirElemental());
								quantidadeInimigos++;
							}
							break;

						case 2:
							if (earthElementals.size() < 10) {
								earthElementals.add(new EarthElemental());
								quantidadeInimigos++;
							}
							break;

						case 3:
							if (fireElementals.size() < 10) {
								fireElementals.add(new FireElemental());
								quantidadeInimigos++;
							}
							break;

						case 4:
							if (waterElementals.size() < 10) {
								waterElementals.add(new WaterElemental());
								quantidadeInimigos++;
							}
							break;
						}

					}
				}
			}

			if (vidas <= 0) {
				derrota = true;
			}

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch (poder) {
		case 0:
			if (poder == Properties.ATAQUE_NORMAL) {
				int soundPoderNormal = (int) (Math.random() * (4 - 1) + 1);
				if (soundPoderNormal == 1) {
					playSound(Properties.getSwordsoundone());
					soundPoderNormal = 0;
				}
				if (soundPoderNormal == 2) {
					playSound(Properties.getSwordsoundtwo());
					soundPoderNormal = 0;
				}
				if (soundPoderNormal == 3) {
					playSound(Properties.getSwordsoundthree());
					soundPoderNormal = 0;
				}
				if (soundPoderNormal == 4) {
					playSound(Properties.getSwordsoundfour());
					soundPoderNormal = 0;
				}

			}
			break;
		case 2:
			if (poder == Properties.ATAQUE_EARTH)
				;
			playSound(Properties.getEarthsound());

			break;
		case 3:
			if (poder == Properties.ATAQUE_FIRE)
				;
			playSound(Properties.getFiresound());

			break;
		case 4:
			if (poder == Properties.ATAQUE_WATER)
				;
			playSound(Properties.getWatersound());

			break;
		case 1:
			if (poder == Properties.ATAQUE_AIR)
				;
			playSound(Properties.getWindsound());

			break;

		}

		for (AirElemental a : airElementals) {
			if (airElementals.size() > 0) {
				if (e.getX() > a.getPosicao().getTranslateX() - 32 && e.getX() < a.getPosicao().getTranslateX() + 64)
					if (e.getY() > a.getPosicao().getTranslateY() - 32
							&& e.getY() < a.getPosicao().getTranslateY() + 96) {
						if (poder == properies.ATAQUE_EARTH) {
							airElementals.remove(a);
							quantidadeInimigos--;
							score++;
						} else if (poder == properies.ATAQUE_NORMAL && a.getVida() > 1) {
							a.setVida(a.getVida() - player.getValorDeAtaqueHeroi());
						} else if (poder == properies.ATAQUE_NORMAL && a.getVida() <= 1) {
							a.setVida(a.getVida() - player.getValorDeAtaqueHeroi());
							airElementals.remove(a);
							quantidadeInimigos--;
							score += 2;
						}

						else if (poder == properies.ATAQUE_AIR && a.getVida() >= 1) {
							a.setVida(a.getVida() + player.getValorMagiaHeroi());
						} else if (poder != properies.ATAQUE_AIR && a.getVida() > 1) {
							a.setVida(a.getVida() - player.getValorMagiaHeroi());
						} else {
							a.setVida(a.getVida() - player.getValorMagiaHeroi());
							airElementals.remove(a);
							quantidadeInimigos--;
							score += 2;
						}
						if (a.getVida() <= 0) {
							airElementals.remove(a);
							quantidadeInimigos--;
						}
					}
			}
		}
		for (EarthElemental er : earthElementals) {
			if (earthElementals.size() > 0) {
				if (e.getX() > er.getPosicao().getTranslateX() - 32 && e.getX() < er.getPosicao().getTranslateX() + 64)
					if (e.getY() > er.getPosicao().getTranslateY() - 32
							&& e.getY() < er.getPosicao().getTranslateY() + 96) {
						if (poder == properies.ATAQUE_FIRE) {
							earthElementals.remove(er);
							quantidadeInimigos--;
							score++;
						} else if (poder == properies.ATAQUE_NORMAL && er.getVida() > 1) {
							er.setVida(er.getVida() - player.getValorDeAtaqueHeroi());
						} else if (poder == properies.ATAQUE_NORMAL && er.getVida() <= 1) {
							er.setVida(er.getVida() - player.getValorDeAtaqueHeroi());
							earthElementals.remove(er);
							quantidadeInimigos--;
							score += 2;

						}

						else if (poder == properies.ATAQUE_EARTH && er.getVida() >= 1) {
							er.setVida(er.getVida() + player.getValorMagiaHeroi());
						} else if (poder != properies.ATAQUE_EARTH && er.getVida() > 1) {
							er.setVida(er.getVida() - player.getValorMagiaHeroi());
						} else {
							er.setVida(er.getVida() - player.getValorMagiaHeroi());
							earthElementals.remove(er);
							quantidadeInimigos--;
							score += 2;
						}
						if (er.getVida() <= 0) {
							earthElementals.remove(er);
							quantidadeInimigos--;
						}
					}
			}
		}

		for (FireElemental f : fireElementals) {
			if (fireElementals.size() > 0) {
				if (e.getX() > f.getPosicao().getTranslateX() - 32 && e.getX() < f.getPosicao().getTranslateX() + 64)
					if (e.getY() > f.getPosicao().getTranslateY() - 32
							&& e.getY() < f.getPosicao().getTranslateY() + 96) {
						if (poder == properies.ATAQUE_WATER) {
							fireElementals.remove(f);
							quantidadeInimigos--;
							score++;
						} else if (poder == properies.ATAQUE_NORMAL && f.getVida() > 1) {
							f.setVida(f.getVida() - player.getValorDeAtaqueHeroi());
						} else if (poder == properies.ATAQUE_NORMAL && f.getVida() <= 1) {
							f.setVida(f.getVida() - player.getValorDeAtaqueHeroi());
							fireElementals.remove(f);
							quantidadeInimigos--;
							score += 2;

						}

						else if (poder == properies.ATAQUE_FIRE && f.getVida() >= 1) {
							f.setVida(f.getVida() + player.getValorMagiaHeroi());
						} else if (poder != properies.ATAQUE_WATER && f.getVida() > 1) {
							f.setVida(f.getVida() - player.getValorMagiaHeroi());
						} else {
							f.setVida(f.getVida() - player.getValorMagiaHeroi());
							fireElementals.remove(f);
							quantidadeInimigos--;
							score += 2;
						}
						if (f.getVida() <= 0) {
							fireElementals.remove(f);
							quantidadeInimigos--;
						}
					}
			}
		}
		for (WaterElemental w : waterElementals) {
			if (waterElementals.size() > 0) {
				if (e.getX() > w.getPosicao().getTranslateX() - 32 && e.getX() < w.getPosicao().getTranslateX() + 64)
					if (e.getY() > w.getPosicao().getTranslateY() - 32
							&& e.getY() < w.getPosicao().getTranslateY() + 96) {
						if (poder == properies.ATAQUE_AIR) {
							waterElementals.remove(w);
							quantidadeInimigos--;
							score++;
						} else if (poder == properies.ATAQUE_NORMAL && w.getVida() > 1) {
							w.setVida(w.getVida() - player.getValorDeAtaqueHeroi());
						} else if (poder == properies.ATAQUE_NORMAL && w.getVida() <= 1) {
							w.setVida(w.getVida() - player.getValorDeAtaqueHeroi());
							fireElementals.remove(w);
							quantidadeInimigos--;
							score += 2;
						}

						else if (poder == properies.ATAQUE_WATER && w.getVida() >= 1) {
							w.setVida(w.getVida() + player.getValorMagiaHeroi());
						} else if (poder != properies.ATAQUE_WATER && w.getVida() > 1) {
							w.setVida(w.getVida() - player.getValorMagiaHeroi());
						} else {
							w.setVida(w.getVida() - player.getValorMagiaHeroi());
							waterElementals.remove(w);
							quantidadeInimigos--;
							score += 2;
						}
						if (w.getVida() <= 0) {
							waterElementals.remove(w);
							quantidadeInimigos--;
						}
					}
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_D:
			derrota = true;
			break;
		case KeyEvent.VK_S:
			if (derrota)
				new Janela();
			break;
		case KeyEvent.VK_R:
			if (poder != 1) {
				poder = 1;
			} else {
				poder = 0;
			}
			break;

		case KeyEvent.VK_E:
			if (poder != 2) {
				poder = 2;
			} else {
				poder = 0;
			}
			break;

		case KeyEvent.VK_W:
			if (poder != 3) {
				poder = 3;
			} else {
				poder = 0;
			}
			break;

		case KeyEvent.VK_Q:
			if (poder != 4) {
				poder = 4;
			} else {
				poder = 0;
			}
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void playBgm(String music) {
		try {
			File file = new File(music);
			clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
			clip.open(inputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-20);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void stopBgm() {
		clip.stop();
	}

	public void playSound(String music) {
		try {
			File file = new File(music);
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
			clip.open(inputStream);
			clip.loop(0);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-6);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}