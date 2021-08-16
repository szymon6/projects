import { combineReducers, createStore } from "redux";
import { taskReducer } from './myReducer'

const mainReducer = combineReducers({
    taskReducer
});

export default createStore(mainReducer);