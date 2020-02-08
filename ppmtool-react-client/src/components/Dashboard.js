import React, { Component } from "react";
import ProjectItem from "./Project/ProjectItem";

class Dashboard extends Component {
  render() {
    return (
      <div>
        <p>Welcome to Dashboard</p>
        <ProjectItem />
      </div>
    );
  }
}

export default Dashboard;
