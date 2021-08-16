import React from 'react';
import { Button, Menu, MenuItem } from '@material-ui/core';
import MoreHorizIcon from '@material-ui/icons/MoreHoriz';
import { useDispatch } from 'react-redux';

const Task = ({ task }) => {
    const dispatch = useDispatch();

    const [anchorEl, setAnchorEl] = React.useState(null);

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const remove = () => {
        dispatch({
            type: 'REMOVE_TASK',
            payload: task,
        });

        handleClose();
    };

    const togglePriority = () => {
        dispatch({
            type: 'TOGGLE_PRIORITY',
            payload: task,
        });

        handleClose();
    };

    const priorytedClass = task.priority ? 'prioryted' : '';
    return (
        <div className="task">
            <div className={`priority ${priorytedClass}`}></div>
            <div>
                <h2>{task.title}</h2>
                <p>{task.category}</p>
            </div>

            <div className="dropdown">
                <Button
                    aria-controls="simple-menu"
                    aria-haspopup="true"
                    onClick={handleClick}
                >
                    <MoreHorizIcon />
                </Button>
                <Menu
                    id="simple-menu"
                    anchorEl={anchorEl}
                    keepMounted
                    open={Boolean(anchorEl)}
                    onClose={handleClose}
                >
                    <MenuItem onClick={remove}>Usuń</MenuItem>
                    <MenuItem onClick={togglePriority}>
                        {task.priority ? 'Wyłącz priorytet' : 'Dodaj priorytet'}
                    </MenuItem>
                </Menu>
            </div>
        </div>
    );
};

export default Task;
