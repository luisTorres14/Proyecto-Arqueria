package models;

import java.util.ArrayList;

public class Team {

	private int id;
	private ArrayList<Player> listPlayers;
	private int point;

	public Team(int id, ArrayList<Player> listPlayers) {
		super();
		this.id = id;
		this.listPlayers = listPlayers;
		this.point = 0;
	}
	
	public void addPoints(int points) {
		point+=points;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Player> getListPlayers() {
		return listPlayers;
	}

	public void setListPlayers(ArrayList<Player> listPlayers) {
		this.listPlayers = listPlayers;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", listPlayers=" + listPlayers + ", point=" + point + "]";
	}
}