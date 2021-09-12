package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Member {
	
	@Id
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	
	@Column(name = "USERNAME")
	private String userName;
	
//	@Column(name = "TEAM_ID")
//	private Long teamId;

	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
	private Team team;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Team getTeam() {
		return team;
	}

	public void changeTeam(Team team) {
		this.team = team;
		
		team.getMembers().add(this);
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", userName=" + userName + ", team=" + team + "]";
	}
	
	
}