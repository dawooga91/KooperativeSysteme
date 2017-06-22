package fh.dortmund.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import fh.dortmund.logic.entity.Lecture;
import fh.dortmund.logic.entity.User;

@Component
public class LectureManager {

	private List<Lecture> lecturesList;
	private Usermanager usermanager;

	public LectureManager() {
		lecturesList = new ArrayList<>();
	}

	public List<Lecture> getLectureList() {
		return lecturesList;
	}

	/**
	 * Gibt die Vorlesung wieder
	 * 
	 * @param name
	 * @return Vorlesung
	 */
	public Lecture getLectureByOID(Lecture lecture) {

		for (Lecture lec : lecturesList) {
			if (lecture.getOid() == lec.getOid()) {
				return lecture;
			}
		}
		return null;
	}

	/**
	 * Schließt die Umfrage der Vorlesung
	 * 
	 * @param name
	 */
	public Lecture closePoll(Lecture lec) {
		Lecture lecture = getLectureByOID(lec);
		lecture.setOpen(false);
		return lecture;
	}

	/**
	 * Neue Umfrage der Vorlesung
	 * 
	 * @param lecture
	 *            der Vorlesung
	 */
	public void newPoll(Lecture lecture) {

		lecture.restart();
	}

	/**
	 * Schließt die Vorlesung
	 * 
	 * @param lec
	 */
	public Lecture closeLecture(Lecture lec) {

		Lecture lecture = getLectureByOID(lec);
		if (lecture != null) {
			lecturesList.remove(lecture);
			return lecture;
		}
		return null;

	}

	/**
	 * Erstellt eine neue Vorlesung
	 * 
	 * @param name
	 *            der Vorlesung
	 */
	public void newLecture(String name) {

		Lecture lecture = new Lecture();
		lecture.setName(name);

		lecturesList.add(lecture);
	}

	/**
	 * Erhöht den ja oder nein Zähler der Vorlesung, wenn die Umfrage
	 * freigegeben ist.
	 * 
	 * @param lecture
	 * @param vote
	 */
	public Lecture vote(Lecture lecture, boolean vote) {
		Lecture currentLecture = getLectureByOID(lecture);
		if (currentLecture.isOpen()) {
			int[] poll = currentLecture.getPoll();
			if (vote) {
				poll[0] += 1;
			} else {
				poll[1] += 1;
			}
			currentLecture.setPoll(poll);
			return currentLecture;
		} else {
			return null;
		}

	}

	/**
	 * Benutzer tritt der Vorlesung bei. Falls der Nutzer schon angemeldet ist,
	 * kann er nicht mehr beitretten
	 * 
	 * @param lec
	 * @param user
	 * @return
	 */
	public Lecture joinLecture(Lecture lec, User user) {

		Lecture joinedLecture = getLectureByOID(lec);
		User u = usermanager.getUserByOid(user);
		List<User> activeUsers = joinedLecture.getUsers();
		for (User activeUser : activeUsers) {
			if (user.getOid() == activeUser.getOid()) {
				return null;
			}
			joinedLecture.getUsers().add(u);
		}

		return joinedLecture;
	}
}
