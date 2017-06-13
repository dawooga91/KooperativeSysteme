package fh.dortmund.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import fh.dortmund.logic.entity.Lecture;

@Component
public class LectureManager {

	private List<Lecture> lecturesList;

	public LectureManager() {
		lecturesList = new ArrayList<>();
	}

	public List<Lecture> getLectureList() {
		return lecturesList;
	}

	/**Gibt die Vorlesung wieder
	 * @param name
	 * @return Vorlsung 
	 */
	public Lecture getLectureByName(String name) {

		for (Lecture lecture : lecturesList) {
			if (lecture.getName().equals(name)) {
				return lecture;
			}
		}
		return null;
	}

	/** Schließt die Umfrage der Vorlesung
	 * @param name
	 */
	public void closePoll(String name) {
		getLectureByName(name).setOpen(false);
	}

	/**Neue Umfrage der Vorlesung
	 * @param lecture 
	 * @param name der Vorlesung
	 */
	public void newPoll(Lecture lecture) {
		
		lecture.restart();
	}

	/**Schließt die Vorlesung
	 * @param name
	 */
	public Lecture closeLecture(String name) {

		Lecture lecture = getLectureByName(name);
		if(lecture!=null){
			lecturesList.remove(lecture);
			return lecture;
		}
		return null;
		

	}

	/**Erstellt eine neue Vorlesung
	 * @param name der Vorlesung
	 */
	public void newLecture(String name) {
		Lecture lecture = new Lecture(name);

		lecturesList.add(lecture);
	}

	/**Erhöht den ja oder nein Zähler der Vorlesung, wenn die Umfrage freigegeben ist.
	 * @param lecture
	 * @param vote
	 */
	public void vote(String lecture, boolean vote) {
		Lecture currentLecture = getLectureByName(lecture);
		if (currentLecture.isOpen()) {
			if (vote) {
				currentLecture.setCountYes(currentLecture.getCountYes() + 1);
			} else {
				currentLecture.setCountNo(currentLecture.getCountNo() + 1);
			}
		} else {

		}

	}

}
