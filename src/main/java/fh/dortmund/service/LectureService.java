package fh.dortmund.service;

import static fh.dortmund.service.ServiceConfig.BASE_PATH;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fh.dortmund.logic.LectureManager;
import fh.dortmund.logic.Usermanager;
import fh.dortmund.logic.entity.Lecture;
import fh.dortmund.logic.entity.User;
import fh.dortmund.logic.exception.LectureNotFoundException;
import fh.dortmund.logic.exception.UserException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = BASE_PATH + "/lecture")
public class LectureService {

	@Autowired
	private LectureManager lectureManager;
	@Autowired
	Usermanager usermanager;

	@RequestMapping(path = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Lecture> getALLLectures() {
		List<Lecture> lectureList = lectureManager.getLectureList();
		return lectureList;
	}

	@RequestMapping(path = "/{oid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Lecture getLectureByOid(@PathVariable("oid") long oid) {
		Lecture lecture;
		try {
			lecture = lectureManager.getLectureByOid(oid);
			return lecture;
		} catch (LectureNotFoundException e) {
			return null;

		}
	}

	@RequestMapping(path = "/delete/{oid}", method = RequestMethod.DELETE)
	public void removeLecture(@PathVariable("oid") long oid) {
		log.info(Long.toString(oid));
		try {
			lectureManager.deleteLecture(oid);
		} catch (LectureNotFoundException e) {
			log.error(e.getMessage());
		}

	}

	@RequestMapping(path = "/vote/true/{oid}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Lecture voteTrue(@PathVariable("oid") long oid) {
		try {
			return lectureManager.vote(oid, true);
		} catch (LectureNotFoundException e) {
			log.error(e.getMessage());
		}
		return null;

	}

	@RequestMapping(path = "/vote/false/{oid}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Lecture voteFalse(@PathVariable("oid") long oid) {
		try {
			return lectureManager.vote(oid, false);
		} catch (LectureNotFoundException e) {
			log.error(e.getMessage());
		}
		return null;

	}

	@RequestMapping(path = "/poll/new/{oid}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Lecture startNewPoll(@PathVariable("oid") long oid) {
		Lecture lecture;
		try {
			lecture = lectureManager.getLectureByOid(oid);
			lectureManager.newPoll(oid);
			return lecture;
		} catch (LectureNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping(path = "/poll/{oid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public int[] getPoll(@PathVariable("oid") long oid) {
		Lecture selectedLecture;
		try {
			selectedLecture = lectureManager.getLectureByOid(oid);
			return selectedLecture.getPoll();
		} catch (LectureNotFoundException e) {
			log.error(e.getMessage());
		}
		return null;
	}

	@RequestMapping(path = "/create", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Lecture saveLecture(@RequestBody Lecture lecture) {
		log.info("POST Lecture '{}'", lecture);
		return lectureManager.create(lecture);
	}

	@RequestMapping(path = "/join/{userOid}/{lecOid}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Lecture join(@PathVariable("userOid") long userOid, @PathVariable("lecOid") long lecOid) {
		Lecture lectureToJoin = null;
		try {
			lectureToJoin = lectureManager.getLectureByOid(lecOid);
			User userJoin = usermanager.getUserByOid(userOid);
			lectureToJoin.join(userJoin);
		} catch (UserException e) {
			log.error(e.getMessage());
		} catch (LectureNotFoundException e) {
			log.error(e.getMessage());
		}

		return lectureToJoin;

	}

	@RequestMapping(path = "close/{oid}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void closePoll(@PathVariable("oid") long oid) {
		Lecture lectureByOid = getLectureByOid(oid);
		lectureByOid.setOpen(false);

	}

}
