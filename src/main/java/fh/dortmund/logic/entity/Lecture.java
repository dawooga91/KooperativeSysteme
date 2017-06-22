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
	int[] poll;

	/**
	 * Starte neue Umfrage
	 * 
	 */
	public void restart() {
		poll = new int[2];

	}

}
