/**
 * Project: a01001690Gis
 * File: Score.java
 * Date: Feb 20, 2017
 * Time: 1:07:38 AM
 */
package a01001690.data;

/**
 * @author chrisdean A01001690
 *
 */
public class Score {
	private String personaId;
	private String gameId;
	private String win;

	public Score() {
	};

	public Score(String personaId, String gameId, String win) {
		this.personaId = personaId;
		this.gameId = gameId;
		this.win = win;
	}

	private Score(Builder builder) {
		this.personaId = builder.personaId;
		this.gameId = builder.gameId;
		this.win = builder.win;
	}

	public static class Builder {
		private String personaId;
		private String gameId;
		private String win;

		public Builder(String personaId, String gameId, String win) {
			this.personaId = personaId;
			this.gameId = gameId;
			this.win = win;
		}

		public Score build() {
			return new Score(this);
		}

	}

	/**
	 * @return the personaId
	 */
	public String getPersonaId() {
		return personaId;
	}

	/**
	 * @param personaId
	 *            the personaId to set
	 */
	public void setPersonaId(String personaId) {
		this.personaId = personaId;
	}

	/**
	 * @return the gameId
	 */
	public String getGameId() {
		return gameId;
	}

	/**
	 * @param gameId
	 *            the gameId to set
	 */
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	/**
	 * @return the win
	 */
	public String getWin() {
		return win;
	}

	/**
	 * @param win
	 *            the win to set
	 */
	public void setWin(String win) {
		this.win = win;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Score [personaId=" + personaId + ", gameId=" + gameId + ", win=" + win + "]";
	}

}
