import React, { Component } from "react";
import ProjectReducer from "../../reducers/ProjectReducer";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";

class ProjectItem extends Component {
  render() {
    const project = this.props.project;
    return (
      <div class="container">
        <div class="card card-body bg-light mb-3">
          <div class="row">
            <div class="col-2">
              <span class="mx-auto">{project.projectIdentifier}</span>
            </div>
            <div class="col-lg-6 col-md-4 col-8">
              <h3>{project.projectName}</h3>
              <p>{project.description}</p>
            </div>
            <div class="col-md-4 d-none d-lg-block">
              <ul class="list-group">
                <a href="#">
                  <li class="list-group-item board">
                    <i class="fa fa-flag-checkered pr-1"> Project Board </i>
                  </li>
                </a>
                <Link to={`/updateProject/${project.projectIdentifier}`}>
                  <li class="list-group-item update">
                    <i class="fa fa-edit pr-1"> Update Project Info</i>
                  </li>
                </Link>
                <a href="">
                  <li class="list-group-item delete">
                    <i class="fa fa-minus-circle pr-1"> Delete Project</i>
                  </li>
                </a>
              </ul>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
ProjectReducer.propTypes = {
  project: PropTypes.object.isRequired
};
export default ProjectItem;
