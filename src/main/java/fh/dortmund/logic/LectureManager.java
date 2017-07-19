package fh.dortmund.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import fh.dortmund.logic.entity.Lecture;
import fh.dortmund.logic.entity.User;
import fh.dortmund.logic.exception.UserException;
import fh.dortmund.logic.exception.LectureNotFoundException;
import lombok.extern.slf4j.Slf4j;


@Slf4j
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

	public Lecture getLectureByOid(long oid) throws LectureNotFoundException {
	if (oid!=0) {
		
		for (Lecture lecture : lecturesList) {
			if (lecture.getOid() == oid)
				return lecture;
		}
	}
	else
		throw new LectureNotFoundException("User not found");
		return null;
	}

	public Lecture deleteLecture(long oid) throws LectureNotFoundException {
		Lecture lectureByOid;
		
		
			lectureByOid = getLectureByOid(oid);
			lecturesList.remove(lectureByOid);
		


		return lectureByOid;
	}

	public Lecture vote(long oid, boolean vote) throws LectureNotFoundException {
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

	public Lecture newPoll(long oid) throws LectureNotFoundException {
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
	public void join(Lecture lec, User user)
	{
		try{
			log.info("Join User");
		lec.join(user);
		}
		catch (UserException e) {
			log.error(e.getMessage());
		}
	}
}
