import React, { Component } from "react";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import { deleteProjectTask } from "../../../actions/BacklogActions";
import PropTypes from "prop-types";

class ProjectTask extends Component {
  onDeleteClick = (projectIdentifier, projectTaskId) => {
    this.props.deleteProjectTask(projectIdentifier, projectTaskId);
  };
  render() {
    const { projectTask } = this.props;
    let priorityString;
    let priorityClass;
    if (projectTask.priority === 1) {
      priorityString = "HIGH";
      priorityClass = "bg-danger text-light";
    } else if (projectTask.priority === 2) {
      priorityString = "MEDIUM";
      priorityClass = "bg-warning text-light";
    } else if (ProjectTask.priority === 3) {
      priorityString = "LOW";
      priorityClass = "bg-info text-light";
    }
    return (
      <div className="card mb-1 bg-light">
        <div className={priorityClass}>
          ID: {projectTask.projectSequence} -- Priority: {priorityString}
        </div>
        <div className="card-body bg-light">
          <h5 className="card-title">{projectTask.summary}</h5>
          <p className="card-text text-truncate ">
            {projectTask.acceptanceCriteria}
          </p>
          <Link
            to={`/updateProjectTask/${projectTask.projectIdentifier}/${projectTask.projectSequence}`}
            className="btn btn-primary ml-4"
          >
            View / Update
          </Link>
          <button
            className="btn btn-danger ml-4"
            onClick={this.onDeleteClick.bind(
              this,
              projectTask.projectIdentifier,
              projectTask.projectSequence
            )}
          >
            Delete
          </button>
        </div>
      </div>
    );
  }
}

ProjectTask.propTypes = {
  deleteProjectTask: PropTypes.func.isRequired
};

export default connect(null, { deleteProjectTask })(ProjectTask);
