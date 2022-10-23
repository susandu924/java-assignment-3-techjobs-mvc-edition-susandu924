package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 renders the form defined in the search.html template*/
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping(value = "results")
//    You can search by columnChoice or keyword
//    two other parameters, specifying the type of search and the search term.
//In order for these last two parameters to be properly passed in by Spring Boot, you need to use the correct annotation.
// Also, you need to name them appropriately, based on the corresponding form field names defined in search.html.
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

        ArrayList<Job> jobs;
//        model.addAttribute("jobs", jobs);
        if (searchType.equals("all") && searchTerm == "") {
            jobs = JobData.findAll();
            return "search";
        } else {
            jobs = JobData.findByColumnAndValue(columnChoices.toString(), searchTerm);
        }
        return "search";

    }
}
