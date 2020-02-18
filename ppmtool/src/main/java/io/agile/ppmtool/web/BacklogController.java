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

import io.agile.ppmtool.domain.ProjectTask;
import io.agile.ppmtool.services.ProjectTaskService;
import io.agile.ppmtool.services.ValidationErrorService;

@RestController	
@CrossOrigin
@RequestMapping("/api/backlog")
public class BacklogController {

	@Autowired
	private ProjectTaskService projectTaskService;
	
	@Autowired
	private ValidationErrorService validationErrorService;
	
	@PostMapping("/{projectidentifier}")
	public ResponseEntity<?> addProjectTask(@PathVariable @Valid String projectidentifier,
			@Valid @RequestBody ProjectTask projectTask, BindingResult result){
		
		ResponseEntity<?> errorMap=validationErrorService.mapValidationService(result);
		if(errorMap!=null) {
			return errorMap;
		}
		
		ProjectTask tempProjectTask=projectTaskService.addProjectTask(projectidentifier, projectTask);
		return new ResponseEntity<ProjectTask>(tempProjectTask,HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectIdentifier}")
	public ResponseEntity<List<ProjectTask>> getProjectBacklof(@PathVariable String projectIdentifier){
		return new ResponseEntity<List<ProjectTask>>(projectTaskService.findBacklogById(projectIdentifier),HttpStatus.OK); 
	}
	
	@GetMapping("/{projectIdentifier}/{projectSequence}")
	public ResponseEntity<?> getProjectTask(@PathVariable String projectIdentifier, @PathVariable String projectSequence){
		ProjectTask tempProjectTask=projectTaskService.findProjectSequenceById(projectIdentifier, projectSequence);                                                    
		return new ResponseEntity<ProjectTask>(tempProjectTask,HttpStatus.OK);
	}
	
	@DeleteMapping("/{projectIdentifier}/{projectSequence}")
	public ResponseEntity<?> deleteProjectTask(@PathVariable String projectIdentifier, @PathVariable String projectSequence){
		projectTaskService.deletebyProjectSequence(projectIdentifier, projectSequence);
		return new ResponseEntity<String>("project Task is deleted: "+projectSequence,HttpStatus.OK);
	}
}
