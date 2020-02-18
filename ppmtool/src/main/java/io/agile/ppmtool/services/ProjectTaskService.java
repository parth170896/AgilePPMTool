package io.agile.ppmtool.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.agile.ppmtool.domain.Backlog;
import io.agile.ppmtool.domain.ProjectTask;
import io.agile.ppmtool.exceptions.ProjectIdException;
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
		try {
			if(projectTask.getId()==null || projectTask.getId()==0) {
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
			}
			return projectTaskRepository.save(projectTask);	
		}
		catch(Exception exc) {
			throw new ProjectIdException("Project with Id "+projectIdentifier+ " does not exists");
		}
	}

	public List<ProjectTask> findBacklogById(String projectIdentifier) {
		return projectTaskRepository.findByProjectIdentifierOrderByPriority(projectIdentifier); 	
	}
	
	public ProjectTask findProjectSequenceById(String projectIdentifier, String projectSequence) {
			Backlog backlog=backlogRepository.findByProjectIdentifier(projectIdentifier);
			if(backlog==null) {
				throw new ProjectIdException("Project with Id "+projectIdentifier+ " does not exists");
			}
			ProjectTask projectTask=projectTaskRepository.findByProjectSequence(projectSequence);
			if(projectTask==null) {
				throw new ProjectIdException("Project with sequence "+projectSequence+ " does not exists");
			}
			if(!projectTask.getProjectIdentifier().equals(projectIdentifier)) {
				throw new ProjectIdException(projectSequence+ " does not belong to "+projectIdentifier);
			}
			return projectTask;
	}

	public void deletebyProjectSequence(String projectIdentifier, String projectSequence) {
		ProjectTask projectTask=findProjectSequenceById(projectIdentifier, projectSequence);

		projectTaskRepository.delete(projectTask);
	}
	
}