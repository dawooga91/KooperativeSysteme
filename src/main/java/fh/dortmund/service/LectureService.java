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
import fh.dortmund.logic.entity.Lecture;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = BASE_PATH + "/lecture")
public class LectureService {

	@Autowired
	private LectureManager lectureManager;

	@RequestMapping(path = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Lecture> getALLLectures() {
		List<Lecture> lectureList = lectureManager.getLectureList();
		return lectureList;
	}

	@RequestMapping(path = "/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Lecture getLectureByOid(@PathVariable("oid") long oid) {
		Lecture lecture = lectureManager.getLectureByOid(oid);
		return lecture;
	}

	@RequestMapping(path = "/delete/{oid}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Lecture removeLecture(@PathVariable("oid") long oid) {

		return lectureManager.deleteLecture(oid);

	}

	@RequestMapping(path = "/poll/{oid}/{vote}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Lecture vote(@PathVariable("oid") long oid, @PathVariable("vote") boolean vote) {
		return lectureManager.vote(oid, vote);

	}

	@RequestMapping(path = "/poll/new/{oid}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public int[] startNewPoll(@RequestBody long oid) {
		Lecture lecture = lectureManager.getLectureByOid(oid);
		lectureManager.newPoll(oid);

		return lecture.getPoll();

	}

	@RequestMapping(path = "/poll/{oid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public int[] getPoll(@PathVariable("oid") long oid) {
		Lecture selectedLecture = lectureManager.getLectureByOid(oid);
		return selectedLecture.getPoll();
	}

	@RequestMapping(path = "/create", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Lecture saveLecture(@RequestBody Lecture lecture) {
		log.info("POST Lecture '{}'", lecture);
		return lectureManager.create(lecture);
	}

}
