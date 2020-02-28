import React, { Component } from "react";
import ProjectTask from "./ProjectTasks/ProjectTask";

class Backlog extends Component {
  render() {
    const { projectTasks } = this.props;
    const task = projectTasks.map(projectTask => (
      <ProjectTask key={projectTask.id} projectTask={projectTask} />
    ));
    let todo = [];
    let inProgress = [];
    let done = [];
    for (let i = 0; i < task.length; i++) {
      if (task[i].props.projectTask.status == "TO_DO") {
        todo.push(task[i]);
      } else if (task[i].props.projectTask.status == "IN_PROGRESS") {
        inProgress.push(task[i]);
      } else if (task[i].props.projectTask.status == "DONE") {
        done.push(task[i]);
      }
    }
    return (
      <div className="container">
        <div className="row">
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-secondary text-white">
                <h3>TO DO</h3>
              </div>
            </div>
            {todo}
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-primary text-white">
                <h3>In Progress</h3>
              </div>
            </div>
            {inProgress}
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-success text-white">
                <h3>Done</h3>
              </div>
            </div>
            {done}
          </div>
        </div>
      </div>
    );
  }
}

export default Backlog;
