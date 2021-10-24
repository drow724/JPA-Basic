package jpql;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(
		name = "Member.findByUsername",
		query = "select m from Member m where m.username = :username"
)
public class Member {

	@Id @GeneratedValue
	private Long id;
	private String username;
	private int age;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEAM_ID")
	private Team team;
	
	@Enumerated(EnumType.STRING)
	private MemberType type;
	
	public void changeTeam(Team team) {
		this.team = team;
		team.getMembers().add(this);
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Member [id=" + id + ", username=" + username + ", age=" + age + "]";
	}

	/**
	 * @return the team
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * @param team the team to set
	 */
	public void setTeam(Team team) {
		this.team = team;
	}

	/**
	 * @return the type
	 */
	public MemberType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(MemberType type) {
		this.type = type;
	}
	
}
