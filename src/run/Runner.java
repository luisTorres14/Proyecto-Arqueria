package run;

import java.util.ArrayList;

import models.Player;
import models.Team;

public class Runner {

	public static ArrayList<Player> players1 = generatePlayersForATeam();
	public static ArrayList<Player> players2 = generatePlayersForATeam();

	public static Team team1 = new Team(1, players1);
	public static Team team2 = new Team(2, players2);

	public static void main(String[] args) {

		System.out.println(torneo());

	}

	public static int torneo() {
		int pteam1 = 0;
		int pteam2 = 0;
		Team team ;

		for (int i = 0; i < 1000; i++) {
			team = juego2();
			if (team.getId() == 1) {
				pteam1++;
			} else if (team.getId()==2) {
				pteam2++;
			}
		}
		
		Player playerLuck = mostLuckyPlayer(players1, players2);
		System.out.println("El jugador con mayor suerte en el juego es: " + playerLuck + "\n ");

		Player playerExperience = mostExperience(players1, players2);
		System.out.println("El jugador con mayor experiencia en el juego es: " + playerExperience + "\n ");

		if (pteam1 > pteam2) {
			System.out.println("el equipo ganador es el " + team1.getId() + " con un puntaje total de: "
					+ team1.getPoint() + " puntos" + "\n ");
			return 1;
		} else {
			System.out.println("el equipo ganador es el " + team2.getId() + " con un puntaje total de: "
					+ team2.getPoint() + " puntos" + "\n ");
			return 2;
		}
	}


	public static Team juego2() {
		int localteam1 = 0;
		int localteam2 = 0;
		
		Team team;

		for (int i = 0; i < 11; i++) {
			team= ronda();
			if (team.getId() == 2) {
				localteam2++;
			} else if (team.getId()==1) {
				localteam1++;
			}
		}

		System.out.println("..........Inicio partido............");
		if (localteam1 > localteam2) {
			System.out.println("El ganador de este juego es el equipo es: " +"\n"+ team1.getId()+"\n"+"con "+team1.getPoint()+" puntos");
			System.out.println("..........fin partido............\n");
			return team1;
		} else if (localteam1==localteam2) {
			System.out.println("Partido empatado ");
			return null;
		}else {
			System.out.println("El ganador de este juego es el equipo es: "+"\n" + team2.getId()+"\n"+"con "+team2.getPoint()+" puntos");
			System.out.println("..........fin partido............\n");
			return team2;
		}
	}

	public static Team ronda() {

		Player luckiestPlayerOne = luckyShoot(players1);
		Player luckiestPlayerTwo = luckyShoot(players2);

		for (int i = 0; i < players1.size(); i++) {
			while (players1.get(i).getResistance() >= 5) {
				players1.get(i).throwArrow();
				players1.get(i).recoverResistance();
				int score = calculateScore(players1.get(i).isGender());
				players1.get(i).addPoints(score);
				team1.addPoints(score);
			}

			while (players2.get(i).getResistance() >= 5) {
				players2.get(i).throwArrow();
				players2.get(i).recoverResistance();
				int score = calculateScore(players2.get(i).isGender());
				players2.get(i).addPoints(score);
				team2.addPoints(score);
			}

			team1.addPoints(calculateScore(luckiestPlayerOne.isGender()));
			team2.addPoints(calculateScore(luckiestPlayerTwo.isGender()));

			Player bestPlayerGame = bestPlayerGame(players1, players2);
			bestPlayerGame.addPointExperience();

		}
		if (team1.getPoint() > team2.getPoint()) {
			return team1;
		} else {
			return team2;
		}

	}

	public static Player bestPlayerGame(ArrayList<Player> players1, ArrayList<Player> players2) {

		Player bestPlayer1 = bestPlayerOfATeam(players1);
		Player bestPlayer2 = bestPlayerOfATeam(players2);

		if (bestPlayer1.getPoint() == bestPlayer2.getPoint()) {
			System.out.println("Se desempatara en 10 lanzamientos nuevos");
			for (int i = 0; i < 10; i++) {
				bestPlayer1.addPoints(calculateScore(bestPlayer1.isGender()));
				bestPlayer2.addPoints(calculateScore(bestPlayer2.isGender()));
			}
		}

		if (bestPlayer1.getPoint() > bestPlayer2.getPoint()) {
			return bestPlayer1;
		}
		return bestPlayer2;
	}

	// 1 Jugador con más suerte en cada uno de los juegos
	public static Player mostLuckyPlayer(ArrayList<Player> players1, ArrayList<Player> players2) {
		Player luckiest1 = players1.get(0);
		for (Player player : players1) {
			if (player.getLuck() >= luckiest1.getLuck()) {
				luckiest1 = player;
			}
		}
		Player luckiest2 = players2.get(0);
		for (Player player : players2) {
			if (player.getLuck() >= luckiest2.getLuck()) {
				luckiest2 = player;
			}
		}

		if (luckiest1.getLuck() > luckiest2.getLuck()) {
			return luckiest1;
		}
		return luckiest2;
	}

	// Jugador con mas experiencia de los dos equipos
	public static Player mostExperience(ArrayList<Player> players1, ArrayList<Player> players2) {
		Player expe1 = players1.get(0);
		for (Player player : players1) {
			if (player.getExperience() > expe1.getExperience()) {
				expe1 = player;
			}
		}

		Player expe2 = players2.get(0);
		for (Player player : players2) {
			if (player.getExperience() > expe2.getExperience()) {
				expe2 = player;
			}
		}

		if (expe1.getExperience() < expe2.getExperience()) {
			return expe2;
		}
		return expe1;
	}

	

	public static Player bestPlayerOfATeam(ArrayList<Player> plaList) {

		Player bestPlayer = plaList.get(0);

		for (Player player : plaList) {
			if (player.getPoint() > bestPlayer.getPoint()) {
				bestPlayer = player;
			}
		}
		return bestPlayer;
	}

	public static Player luckyShoot(ArrayList<Player> plaList) {
		double luckuAux = 0;
		Player luckiest = plaList.get(0);

		for (Player player : plaList) {
			if (player.getLuck() > luckuAux) {
				luckiest = player;
				luckuAux = player.getLuck();
			}
		}

		return luckiest;
	}

	public static int calculateScore(boolean gender) {
		double score = score();
		if (gender) {
			if (score < 0.20) {
				return 10;
			} else if (score > 0.20 && score <= 0.53) {
				return 8;
			} else if (score > 0.53 && score <= 0.93) {
				return 6;
			} else {
				return 0;
			}
		} else {
			if (score < 0.30) {
				return 10;
			} else if (score > 0.30 && score <= 0.68) {
				return 8;
			} else if (score > 0.68 && score <= 0.95) {
				return 6;
			} else {
				return 0;
			}
		}
	}

	public static double score() {
		return ((Math.random() * (1 - 0)) + 0);
	}

	public static ArrayList<Player> generatePlayersForATeam() {

		ArrayList<Player> plaList = new ArrayList<Player>();

		for (int i = 1; i < 16; i++) {
			plaList.add(new Player(i));
//			System.out.println(plaList.get(i - 1));
		}

		return plaList;

	}
}
