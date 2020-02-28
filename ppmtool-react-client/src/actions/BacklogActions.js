import axios from "axios";
import { GET_ERRORS } from "./Types";

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
