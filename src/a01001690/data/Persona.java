/**
 * Project: a01001690Gis
 * File: Persona.java
 * Date: Feb 20, 2017
 * Time: 12:22:19 AM
 */
package a01001690.data;

/**
 * @author chrisdean A01001690
 *
 */
public class Persona {
	private String id;
	private String playerId;
	private String gamerTag;
	private String platform;

	public Persona() {
	};

	private Persona(Builder builder) {
		this.id = builder.id;
		this.playerId = builder.playerId;
		this.gamerTag = builder.gamerTag;
		this.platform = builder.platform;
	}

	public static class Builder {
		private String id;
		private String playerId;
		private String gamerTag;
		private String platform;

		public Builder(String id, String playerId) {
			this.id = id;
			this.playerId = playerId;
		}

		public Builder gamerTag(String val) {
			this.gamerTag = val;
			return this;
		}

		public Builder platform(String val) {
			this.platform = val;
			return this;
		}

		public Persona build() {
			return new Persona(this);
		}

	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the playerId
	 */
	public String getPlayerId() {
		return playerId;
	}

	/**
	 * @param playerId
	 *            the playerId to set
	 */
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "gamerTag=" + gamerTag;
	}

}
