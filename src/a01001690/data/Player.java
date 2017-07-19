/**
 * Project: a01001690Gis
 * File: Player.java
 * Date: Feb 20, 2017
 * Time: 12:12:45 AM
 */
package a01001690.data;

/**
 * @author chrisdean A01001690
 *
 */
public class Player {
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String birthDate;

	public Player() {
	};

	public Player(String id, String email, String firstName, String lastName, String birthDate) {
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

	private Player(Builder builder) {
		this.id = builder.id;
		this.email = builder.email;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.birthDate = builder.birthDate;
	}

	public static class Builder {
		// Required Parameters
		private String id;
		private String email;

		private String firstName;
		private String lastName;
		private String birthDate;

		/**
		 * @param id
		 *            the identifier for the player
		 * @param email
		 *            the email for the player
		 */
		public Builder(String id, String email) {
			this.id = id;
			this.email = email;
		}

		public Builder firstName(String val) {
			this.firstName = val;
			return this;
		}

		public Builder lastName(String val) {
			this.lastName = val;
			return this;
		}

		public Builder birthDate(String val) {
			this.birthDate = val;
			return this;
		}

		public Player build() {
			return new Player(this);
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the birth date
	 */
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate
	 *            the birthDate to set
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "firstName=" + firstName + ", lastName=" + lastName;
	}

}
