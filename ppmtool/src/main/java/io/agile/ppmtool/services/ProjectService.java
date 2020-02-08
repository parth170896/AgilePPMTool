package io.agile.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.agile.ppmtool.domain.Project;
import io.agile.ppmtool.exceptions.ProjectIdException;
import io.agile.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdateProject(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		}
		catch(Exception e) {
			throw new ProjectIdException("Project Id "+project.getProjectIdentifier().toUpperCase()+ " already exists");
		}
	}
}
	