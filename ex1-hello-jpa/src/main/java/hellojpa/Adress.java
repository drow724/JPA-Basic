package hellojpa;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Adress {

	private String city;
	private String street;
	@Column(name = "ZIPCODE")
	private String zipcode;
	
	public Adress() {

	}
	
	public Adress(String city, String street, String zipcode) {
		super();
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
	
	public String getCity() {
		return city;
	}
	
	private void setCity(String city) {
		this.city = city;
	}
	
	public String getStreet() {
		return street;
	}
	
	private void setStreet(String street) {
		this.street = street;
	}
	
	public String getZipcode() {
		return zipcode;
	}
	
	private void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, street, zipcode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adress other = (Adress) obj;
		return Objects.equals(city, other.city) && Objects.equals(street, other.street)
				&& Objects.equals(zipcode, other.zipcode);
	}

	
	
	
	
}
