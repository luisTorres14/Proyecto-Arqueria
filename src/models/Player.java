package models;

public class Player {

	private int id;
	private int resistance;
	private int experience;
	private double luck;
	private boolean gender; // 1 mujer=false, 0 hombre=true
	private int point;
	private boolean expert; // experto es por que tiene 9 o mas de experiencia
	private int timesExpert; // cantidad de veces como experto masx 3 e ira disminuyendo una unidad por ronda 

	private int resistanceAux;

	public Player(int id) {
		this.id = id;
		this.resistance = (int) (Math.random() * (65 - 45)) + 45;
		resistanceAux = resistance;
		this.experience = 0;
		this.luck = (Math.random() * (5 - 1)) + 1;
		this.gender = ((int) (0 + (2 - 0) * Math.random())) >= 1 ? false : true;
		this.point = 0;
		this.expert=false;
		this.timesExpert=3;
	}
	
	public int restTimesExpert(int timeExpert) {
		return timeExpert-1;
	}
	
	public boolean isExpert() {
		return (experience)>= 9 ? true: false;
	}

	public void addPoints(int points) {
		point += points;
	}

	public void throwArrow() {
		resistanceAux = resistanceAux - 5;
	}

	public void recoverResistance() {
		resistance = resistance - (int) (1 + (3 - 1) * Math.random());
		resistanceAux = resistance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getResistance() {
		return resistance;
	}

	public void setResistance(int resistance) {
		this.resistance = resistance;
	}

	public void addPointExperience() {
		experience += 3;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public double getLuck() {
		return luck;
	}

	public void setLuck(int luck) {
		this.luck = luck;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", resistance=" + resistance + ", experience=" + experience + ", luck=" + luck
				+ ", gender=" + gender + ", point=" + point + ", resistanceAux=" + resistanceAux + "]";
	}

}
