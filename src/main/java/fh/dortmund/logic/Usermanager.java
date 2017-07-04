package fh.dortmund.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import fh.dortmund.logic.entity.User;

@Component
public class Usermanager {

	private List<User> userlist;

	public Usermanager() {
		this.userlist = new ArrayList<>();
	}

	public User join(User u) {
		userlist.add(u);
		return u;
	}

	public void leave(User u) {
		userlist.remove(u);
	}

	public User getUserByOid(User u) {
		for (User user : userlist) {
			if (u.getOid() == user.getOid()) {
				return user;
			}
		}
		return null;
	}
}
