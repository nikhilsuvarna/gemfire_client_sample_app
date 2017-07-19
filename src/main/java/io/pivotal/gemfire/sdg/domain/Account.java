package io.pivotal.gemfire.sdg.domain;

import org.springframework.util.ObjectUtils;

public class Account {

	  private int id;
	  private String firstName;
	  private String lastName;

	  public Account() {}

	  public Account(int id, String firstName, String lastName) {
		  this(firstName, lastName);
		  this.id = id;
	  }

	  public Account(String firstName, String lastName) {
		  this.firstName = firstName;
		  this.lastName = lastName;
	  }

	  public Account(final Account account) {
	    this(account.getId(), account.getFirstName(), account.getLastName());
	  }

	  public int getId() {
	    return id;
	  }

	  public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
	    return firstName;
	  }

	  public String getLastName() {
	    return lastName;
	  }

	  public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	  public boolean equals(final Object obj) {
	    if (obj == this) {
	      return true;
	    }

	    if (!(obj instanceof Account)) {
	      return false;
	    }

	    Account that = (Account) obj;

	    return (equalsIgnoreNull(this.getId(), that.getId())
	      && ObjectUtils.nullSafeEquals(this.getFirstName(), that.getFirstName())
	      && ObjectUtils.nullSafeEquals(this.getLastName(), that.getLastName()));
	  }

	  protected boolean equalsIgnoreNull(final Object expected, final Object actual) {
	    return (expected == null || actual == null || actual.equals(expected));
	  }

	  @Override
	  public int hashCode() {
	    int hashValue = 17;
	    hashValue = 37 * hashValue + ObjectUtils.nullSafeHashCode(getId());
	    hashValue = 37 * hashValue + ObjectUtils.nullSafeHashCode(getFirstName());
	    hashValue = 37 * hashValue + ObjectUtils.nullSafeHashCode(getLastName());
	    return hashValue;
	  }

		@Override
		public String toString() {
			return "Account [id=" + id + ", firstName=" + firstName + ", lastName="
					+ lastName + "]";
		}

//	  @Override
//	  public String toString() {
//	    return String.format("{ @type = %1$s, @version = %2$d, id = %3$d, firstName = %4$s, lastName = %5$s }",
//	      getClass().getName(), getId(), getFirstName(), getLastName());
//	  }



}
