package io.agile.ppmtool.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.agile.ppmtool.domain.Backlog;
import io.agile.ppmtool.domain.ProjectTask;
import io.agile.ppmtool.repositories.BacklogRepository;
import io.agile.ppmtool.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
		
		//add ProjectTask only if, project!=null(Backlog for it exists)
		//Set backlog in ProjectTask
		//ProjectSequence => (projectIdentifier)+(ProjectseuqnceNumber)
		//Update Backlog Sequence
		//Set Initial Priority when priority is null
		//Set initial Status
		Backlog backlog=backlogRepository.findByProjectIdentifier(projectIdentifier);
		projectTask.setBacklog(backlog);
		Integer PTSequence=backlog.getPTSequence();
		PTSequence++;
		backlog.setPTSequence(PTSequence);
		projectTask.setProjectSequence(projectIdentifier+"-"+PTSequence);
		projectTask.setProjectIdentifier(projectIdentifier);
		
		if(projectTask.getStatus()==null || projectTask.getStatus()=="") {
			projectTask.setStatus("TO_DO");
		}
		
		if(projectTask.getPriority()==null || projectTask.getPriority()==0) {
			projectTask.setPriority(3);
		}
		
		return projectTaskRepository.save(projectTask);	
	}

	public List<ProjectTask> findBacklogById(String projectIdentifier) {
		return projectTaskRepository.findByProjectIdentifierOrderByPriority(projectIdentifier); 	
	}
}