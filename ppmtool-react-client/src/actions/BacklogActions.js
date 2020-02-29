import axios from "axios";
import { GET_ERRORS, GET_BACKLOG } from "./Types";

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
  } catch (exc) {
    dispatch({
      type: GET_ERRORS,
      payload: exc.response.data
    });
  }
};
