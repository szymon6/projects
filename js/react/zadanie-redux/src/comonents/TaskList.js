import React from 'react';
import { useSelector } from 'react-redux';
import Task from './Task';


const TaskList = () => {
    const tasks = useSelector((state) => state.taskReducer);
    const list = tasks.map((task) => <Task task={task} key={task.id} />);

    return (
        <div>
            {tasks.length !== 0 ? (
                <div className="task-list">{list}</div>
            ) : null}
        </div>
    );
};

export default TaskList;
