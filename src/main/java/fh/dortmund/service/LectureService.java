package fh.dortmund.service;

import static fh.dortmund.service.ServiceConfig.BASE_PATH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fh.dortmund.logic.LectureManager;

@RestController
@RequestMapping(path = BASE_PATH + "/Lecture")
public class LectureService {

	@Autowired
	private LectureManager lectureManager;

	// @RequestMapping(path = "/LectureList", method = RequestMethod.GET,
	// produces = MediaType.APPLICATION_JSON_VALUE)
	// public List<Lecture> getALLLectures() {
	// List<Lecture> lectureList = lectureManager.getLectureList();
	// return lectureList;
	// }
	//
	// @RequestMapping(path = "/{name}", method = RequestMethod.GET, produces =
	// MediaType.APPLICATION_JSON_VALUE)
	// public Lecture getLectureByName(@PathVariable("name") String name,
	// HttpServletRequest request) {
	// Lecture lecture = lectureManager.getLectureByName(name);
	// return lecture;
	// }
	//
	// @RequestMapping(path = "/remove", method = RequestMethod.PUT, consumes =
	// MediaType.APPLICATION_JSON_VALUE, produces =
	// MediaType.APPLICATION_JSON_VALUE)
	// public Lecture removeLecture(@RequestBody Lecture lecture) {
	//
	// return lectureManager.closeLecture(lecture.getName());
	//
	// }
	//
	// @RequestMapping(path = "/poll/{name}/{vote}", method = RequestMethod.PUT,
	// consumes = MediaType.APPLICATION_JSON_VALUE, produces =
	// MediaType.APPLICATION_JSON_VALUE)
	// public void vote(@PathVariable("name") String name, @PathVariable("vote")
	// boolean vote) {
	// lectureManager.vote(name, vote);
	//
	// }

	// @RequestMapping(path ="/poll/new/{oid}",method=
	// RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE, produces=
	// MediaType.APPLICATION_JSON_VALUE)
	// public int[] startNewPoll(@RequestBody long oid)
	// {
	// Lecture lecture = lectureManager.getLectureByOID(oid));
	// lectureManager.newPoll(lecture);
	//
	// return lecture.getPoll();
	//
	// }

	// @RequestMapping(path = "/poll/{name}", method = RequestMethod.GET,
	// produces = MediaType.APPLICATION_JSON_VALUE)
	// public int[] getPoll(Lecture lecture) {
	// Lecture selectedLecture =
	// lectureManager.getLectureByName(lecture.getName());
	// return selectedLecture.getPoll();
	// }

}
