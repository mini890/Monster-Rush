package main;

public class Properties {

	private static final int window_width = 1280;
	private static final int window_height = 720;
	private static final String player_spritesheet = "resources/sprites/player/001-Fighter01.png";
	private static final String fireElemental_spritesheet = "resources/sprites/enemies/083-Elemental01.png";
	private static final String waterElemental_spritesheet = "resources/sprites/enemies/084-Elemental02.png";
	private static final String earthElemental_spritesheet = "resources/sprites/enemies/085-Elemental03.png";
	private static final String airElemental_spritesheet = "resources/sprites/enemies/086-Elemental04.png";

	private static final String sword_activated = "resources/sprites/Painel_Attack/Sword_Activated.png";
	private static final String water_activated = "resources/sprites/Painel_Attack/Water_Activated.png";
	private static final String fire_activated = "resources/sprites/Painel_Attack/Fire_Activated.png";
	private static final String earth_activated = "resources/sprites/Painel_Attack/Earth_Activated.png";
	private static final String air_activated = "resources/sprites/Painel_Attack/Air_Activated.png";

	private static final String backgroundMusic = "resources/sounds/sfx/010-LastBoss02.wav";
	private static final String fireSound = "resources/sounds/sfx/118-Fire02.wav";
	private static final String waterSound = "resources/sounds/sfx/127-Water02.wav";
	private static final String earthSound = "resources/sounds/sfx/130-Earth02.wav";
	private static final String windSound = "resources/sounds/sfx/133-Wind02.wav";

	private static final String swordSoundOne = "resources/sounds/sfx/094-Attack06.wav";
	private static final String swordSoundTwo = "resources/sounds/sfx/095-Attack07.wav";
	private static final String swordSoundThree = "resources/sounds/sfx/096-Attack08.wav";
	private static final String swordsoundFour = "resources/sounds/sfx/097-Attack09.wav";

	private static final String backgroundImage = "resources/sprites/BG.png";
	private static final String poderesAnimacao = "resources/sprites/PODERES_ANIMACAO.png";

	public static final int ATAQUE_NORMAL = 0;
	public static final int ATAQUE_AIR = 1;
	public static final int ATAQUE_EARTH = 2;
	public static final int ATAQUE_FIRE = 3;
	public static final int ATAQUE_WATER = 4;

	public Properties() {

	}

	public int getWindow_width() {
		return window_width;
	}

	public int getWindow_height() {
		return window_height;
	}

	public static String getPlayerSpritesheet() {
		return player_spritesheet;
	}

	public static String getFireElementalSpritesheet() {
		return fireElemental_spritesheet;
	}

	public static String getWaterElementalSpritesheet() {
		return waterElemental_spritesheet;
	}

	public static String getEarthElementalSpritesheet() {
		return earthElemental_spritesheet;
	}

	public static String getAirElementalSpritesheet() {
		return airElemental_spritesheet;
	}

	public static String getBackgroundmusic() {
		return backgroundMusic;
	}

	public static String getSwordActivated() {
		return sword_activated;
	}

	public static String getWaterActivated() {
		return water_activated;
	}

	public static String getFireActivated() {
		return fire_activated;
	}

	public static String getEarthActivated() {
		return earth_activated;
	}

	public static String getAirActivated() {
		return air_activated;
	}

	public static String getFiresound() {
		return fireSound;
	}

	public static String getWatersound() {
		return waterSound;
	}

	public static String getEarthsound() {
		return earthSound;
	}

	public static String getWindsound() {
		return windSound;
	}

	public static String getSwordsoundone() {
		return swordSoundOne;
	}

	public static String getSwordsoundtwo() {
		return swordSoundTwo;
	}

	public static String getSwordsoundthree() {
		return swordSoundThree;
	}

	public static String getSwordsoundfour() {
		return swordsoundFour;
	}

	public static String getBackgroundimage() {
		return backgroundImage;
	}

	public static String getPoderesAnimacao() {
		return poderesAnimacao;
	}
}
