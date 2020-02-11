package io.agile.ppmtool.web;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.agile.ppmtool.domain.Project;
import io.agile.ppmtool.services.ProjectService;
import io.agile.ppmtool.services.ValidationErrorService;

@RestController
@RequestMapping("api/project")
@CrossOrigin
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ValidationErrorService 	validationErrorService;

	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
		
		ResponseEntity<?> errorMap=validationErrorService.mapValidationService(result);
		if(errorMap!=null) {
			return errorMap;
		}
		Project project1=projectService.saveOrUpdateProject(project);	
		return new ResponseEntity<Project>(project1,HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProjectById(@PathVariable String projectId){
		Project tempProject=projectService.findProjectByIdentifier(projectId);
		return new ResponseEntity<Project>(tempProject,HttpStatus.OK);
	}
	
	@GetMapping("")
	public ResponseEntity<List<Project>> getAll(){
		List<Project> list=projectService.findAllProjects();
		return new ResponseEntity<List<Project>>(list,HttpStatus.OK);
	}
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProject(@PathVariable String projectId){
		projectService.deleteProjectById(projectId);
		return new ResponseEntity<String>(projectId+" is deleted",HttpStatus.OK); 
	}	
	
}
