import tasks from '../resources/tasks'

export const taskReducer = (state = tasks, action) => {

    let index = null;

    switch (action.type) {
        case 'ADD_TASK':
            const newTask = {
                id: Math.random(),
                title: action.payload.title,
                category: action.payload.category,
                priority: action.payload.priority
            }
            return sortTasks([...state, newTask]);

        case 'REMOVE_TASK':
            index = state.indexOf(action.payload);
            const newState = [...state];
            newState.splice(index, 1);
            return newState;

        case 'TOGGLE_PRIORITY':
            index = state.indexOf(action.payload);
            state[index].priority = !state[index].priority;
            return sortTasks(state);

        default:
            return state
    }

}

function sortTasks(tasks) {
    const priorytedTasks = tasks.filter(item => item.priority === true);
    const notPriorytedTasks = tasks.filter(item => item.priority === false);
    return [...priorytedTasks, ...notPriorytedTasks];
}



