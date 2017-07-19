package fh.dortmund.logic.entity;

import java.util.ArrayList;
import java.util.List;

import fh.dortmund.logic.exception.UserException;
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
	
	public void join(User user) throws UserException
	{
		if (users==null) {
			users= new ArrayList<>();
		}
		if(user!=null)
			users.add(user);
		else throw new UserException("User= null");
	}

}
