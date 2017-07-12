package fh.dortmund.logic.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Lecture extends BaseEntity {

	private String name;
	private boolean open;
	List<User> users;
	private User admin;
	
	/**
	 * Pos 1 Ja Pos 2 Nein
	 */
	int[] poll;

	/**
	 * Starte eine neue Umfrage
	 * 
	 */
	public void restart() {
		poll = new int[2];

	}

	public void leaveUser(User u) {
		for (User user : users) {
			if (u.getOid() == user.getOid()) {
				users.remove(user);
				return;
			}
		}

	}

}
