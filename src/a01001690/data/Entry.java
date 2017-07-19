/**
 * Project: a01001690Gis
 * File: Entry.java
 * Date: Feb 26, 2017
 * Time: 4:02:15 AM
 */
package a01001690.data;

/**
 * @author chrisdean A01001690
 *
 */
public class Entry {
	private int win;
	private int lose;
	private String gameName;
	private String gamerTag;
	private String platform;

	private Entry(Builder builder) {
		this.win = builder.win;
		this.lose = builder.lose;
		this.gameName = builder.gameName;
		this.gamerTag = builder.gamerTag;
		this.platform = builder.platform;
	}

	public static class Builder {
		private int win;
		private int lose;
		private String gameName;
		private String gamerTag;
		private String platform;

		public Builder(int win, int lose, String gameName, String gamerTag, String platform) {
			this.win = win;
			this.lose = lose;
			this.gameName = gameName;
			this.gamerTag = gamerTag;
			this.platform = platform;
		}

		public Entry build() {
			return new Entry(this);
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Builder [win=" + win + ", lose=" + lose + ", gameName=" + gameName + ", gamerTag=" + gamerTag + ", platform=" + platform + "]";
		}

	}

	/**
	 * @return the win
	 */
	public int getWin() {
		return win;
	}

	/**
	 * @param win
	 *            the win to set
	 */
	public void setWin(int win) {
		this.win = win;
	}

	/**
	 * @return the lose
	 */
	public int getLose() {
		return lose;
	}

	/**
	 * @param lose
	 *            the lose to set
	 */
	public void setLose(int lose) {
		this.lose = lose;
	}

	/**
	 * @return the gameName
	 */
	public String getGameName() {
		return this.gameName;
	}

	/**
	 * @param gameName
	 *            the gameName to set
	 */
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	/**
	 * @return the gamerTag
	 */
	public String getGamerTag() {
		return gamerTag;
	}

	/**
	 * @param gamerTag
	 *            the gamerTag to set
	 */
	public void setGamerTag(String gamerTag) {
		this.gamerTag = gamerTag;
	}

	/**
	 * @return the platform
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * @param platform
	 *            the platform to set
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}
}
