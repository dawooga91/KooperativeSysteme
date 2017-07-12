package fh.dortmund.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import fh.dortmund.logic.entity.Lecture;

@Component
public class LectureManager {

	private List<Lecture> lecturesList;
	private Usermanager usermanager;
	private static long oidCounter=1;

	public LectureManager() {
		lecturesList = new ArrayList<>();
		
	}

	public List<Lecture> getLectureList() {
		return lecturesList;
	}

	public Lecture getLectureByOid(long oid) {
		for (Lecture lecture : lecturesList) {
			if (lecture.getOid() == oid)
				return lecture;
		}
		return null;
	}

	public Lecture deleteLecture(long oid) {
		Lecture lectureByOid = getLectureByOid(oid);

		lecturesList.remove(lectureByOid);

		return lectureByOid;
	}

	public Lecture vote(long oid, boolean vote) {
		Lecture voteLec = getLectureByOid(oid);

		int[] poll = voteLec.getPoll();
		if(poll==null)
			poll= new int[2];
		if (vote) {
			poll[0] += 1;
		} else
			poll[1] += 1;

		voteLec.setPoll(poll);
		return voteLec;
	}

	public Lecture newPoll(long oid) {
		Lecture lectureByOid = getLectureByOid(oid);
		lectureByOid.restart();
		return lectureByOid;
	}

	public Lecture create(Lecture lecture) {
		lecture.setOid(oidCounter);
		oidCounter+=1;
		lecturesList.add(lecture);
		return lecture;
	}

}
