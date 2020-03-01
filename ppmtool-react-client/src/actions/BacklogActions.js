import axios from "axios";
import {
  GET_ERRORS,
  GET_BACKLOG,
  GET_PROJECT_TASK,
  DELETE_PROJECT_TASK
} from "./Types";

export const addProjectTask = (
  projectIdentifier,
  projectTask,
  history
) => async dispatch => {
  try {
    await axios.post(`/api/backlog/${projectIdentifier}`, projectTask);
    history.push(`/projectBoard/${projectIdentifier}`);
    dispatch({
      type: GET_ERRORS,
      payload: {}
    });
  } catch (exc) {
    dispatch({
      type: GET_ERRORS,
      payload: exc.response.data
    });
  }
};

export const getBacklog = projectIdentifier => async dispatch => {
  try {
    const res = await axios.get(`/api/backlog/${projectIdentifier}`);
    dispatch({
      type: GET_BACKLOG,
      payload: res.data
    });
  } catch (exc) {}
};

export const getProjectTask = (
  projectIdentifier,
  projectTaskId
) => async dispatch => {
  try {
    const res = await axios.get(
      `/api/backlog/${projectIdentifier}/${projectTaskId}`
    );
    dispatch({
      type: GET_PROJECT_TASK,
      payload: res.data
    });
  } catch (exc) {
    dispatch({
      type: GET_ERRORS,
      payload: exc.response.data
    });
  }
};

export const deleteProjectTask = (
  projectIdentifier,
  projectTaskId
) => async dispatch => {
  if (window.confirm("Are u sure u want to delete this ProjectTask: "))
    try {
      await axios.delete(`/api/backlog/${projectIdentifier}/${projectTaskId}`);
      dispatch({
        type: DELETE_PROJECT_TASK,
        payload: projectTaskId
      });
    } catch (exc) {}
};
