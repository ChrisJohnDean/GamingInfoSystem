/**
 * Project: a01001690Gis
 * File: Game.java
 * Date: Feb 20, 2017
 * Time: 12:57:25 AM
 */
package a01001690.data;

/**
 * @author chrisdean A01001690
 *
 */
public class Game {
	private String id;
	private String name;
	private String producer;

	public Game() {
	};

	private Game(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.producer = builder.producer;
	}

	public static class Builder {
		private String id;
		private String name;
		private String producer;

		public Builder(String id, String name, String producer) {
			this.id = id;
			this.name = name;
			this.producer = producer;
		}

		public Game build() {
			return new Game(this);
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the producer
	 */
	public String getProducer() {
		return producer;
	}

	/**
	 * @param producer
	 *            the producer to set
	 */
	public void setProducer(String producer) {
		this.producer = producer;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", producer=" + producer + "]";
	}

}
