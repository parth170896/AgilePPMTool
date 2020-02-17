package io.agile.ppmtool.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.agile.ppmtool.domain.Backlog;
import io.agile.ppmtool.domain.Project;
import io.agile.ppmtool.exceptions.ProjectIdException;
import io.agile.ppmtool.repositories.BacklogRepository;
import io.agile.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	public Project saveOrUpdateProject(Project project) {
		try {
			String identifier=project.getProjectIdentifier().toUpperCase();
			project.setProjectIdentifier(identifier);
			if(project.getId()==null) {
				Backlog backlog=new Backlog();
				project.setBacklog(backlog);
				backlog.setProject(project);
				backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			}
			else {
				Backlog backlog=backlogRepository.findByProjectIdentifier(identifier);
				project.setBacklog(backlog);
			}
			return projectRepository.save(project);
		}
		catch(Exception e) {
			throw new ProjectIdException("Project Id "+project.getProjectIdentifier().toUpperCase()+ " already exists");
		}
	}
	
	public Project findProjectByIdentifier(String projectId) {
		Project tempProject=projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if(tempProject==null) {
			throw new ProjectIdException("Project id: "+projectId+" doesn't exist");
		}
		return tempProject;
	}
	
	public List<Project> findAllProjects(){
		List<Project> list=(List<Project>) projectRepository.findAll();
		if(list==null || list.size()==0) {
			throw new ProjectIdException("Projects doesn't exist");
		}
		return list;
	}
	
	public void deleteProjectById(String projectId) {
		Project tempProject=projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		
		if(tempProject==null) {
			throw new ProjectIdException("Project id: "+projectId+" doesn't exist");
		}
		
		projectRepository.delete(tempProject);
	}
}
	