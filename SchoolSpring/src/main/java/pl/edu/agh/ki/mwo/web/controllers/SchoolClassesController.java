package pl.edu.agh.ki.mwo.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.edu.agh.ki.mwo.model.School;
import pl.edu.agh.ki.mwo.model.SchoolClass;
import pl.edu.agh.ki.mwo.persistence.DatabaseConnector;

@Controller
public class SchoolClassesController {

	@RequestMapping(value = "/SchoolsClasses")
	public String listSchoolsClasses(Model model, HttpSession session) {
		if (session.getAttribute("userLogin") == null)
			return "redirect:/Login";

		model.addAttribute("schoolsClasses", DatabaseConnector.getInstance().getSchoolsClasses());

		return "schoolClassesList";
	}

	@RequestMapping(value = "/AddSchoolClass")
	public String displayAddSchoolForm(Model model, HttpSession session) {
		if (session.getAttribute("userLogin") == null)
			return "redirect:/Login";

		return "schoolClassForm";
	}

	@RequestMapping(value = "/CreateSchoolClass", method = RequestMethod.POST)
	public String createSchool(@RequestParam(value = "schoolClassStartYear", required = false) int schoolClassStartYear,
			@RequestParam(value = "schoolClassProfile", required = false) String schoolClassProfile, Model model,
			@RequestParam(value = "schoolClassStartYear", required = false) int schoolClassCurrentYear,
			HttpSession session) {
		if (session.getAttribute("userLogin") == null)
			return "redirect:/Login";

		SchoolClass schoolClass = new SchoolClass();
		schoolClass.setStartYear(schoolClassStartYear);
		schoolClass.setProfile(schoolClassProfile);
		schoolClass.setCurrentYear(schoolClassCurrentYear);

		DatabaseConnector.getInstance().addSchoolClass(schoolClass);
		model.addAttribute("schoolsClasses", DatabaseConnector.getInstance().getSchoolsClasses());
		model.addAttribute("message", "Nowa klasa została dodana");

		return "schoolClassesList";
	}

	@RequestMapping(value = "/DeleteSchoolClasses", method = RequestMethod.POST)
	public String deleteSchool(@RequestParam(value = "schoolClassId", required = false) String schoolClassId,
			Model model, HttpSession session) {
		if (session.getAttribute("userLogin") == null)
			return "redirect:/Login";

		DatabaseConnector.getInstance().deleteSchoolClass(schoolClassId);
		model.addAttribute("schoolsClasses", DatabaseConnector.getInstance().getSchoolsClasses());
		model.addAttribute("message", "Szkoła została usunięta");

		return "schoolClassesList";
	}

}