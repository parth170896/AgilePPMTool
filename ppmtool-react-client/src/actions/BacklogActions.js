import axios from "axios";

export const addProjectTask = (
  projectIdentifier,
  projectTask,
  history
) => async dispatch => {
  await axios.post(`/api/backlog/${projectIdentifier}`, projectTask);
  history.push(`/projectBoard/${projectIdentifier}`);
};
