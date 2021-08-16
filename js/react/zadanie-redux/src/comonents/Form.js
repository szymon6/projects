import React, { useState } from 'react';
import { useDispatch } from 'react-redux';

import { TASK_CATEGORY } from '../resources/constants';

const Form = () => {
    const [title, setTitle] = useState('');
    const [category, setCategory] = useState(TASK_CATEGORY.work);
    const [priority, setPriority] = useState(false);

    const dispatch = useDispatch();

    const handleSubmit = (e) => {
        e.preventDefault();

        if (title === '') return;

        dispatch({
            type: 'ADD_TASK',
            payload: {
                title,
                category,
                priority,
            },
        });


        console.log({ title, category, priority });

        setTitle('');
        setCategory(TASK_CATEGORY.work);
        setPriority(false);
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="task">Dodaj Zadanie: </label>
                <input
                    type="text"
                    id="task"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                />
            </div>

            <div>
                <label htmlFor="category">Kategoria </label>
                <br />
                <select
                    id="category"
                    value={category}
                    onChange={(e) => setCategory(e.target.value)}
                >
                    <option value={TASK_CATEGORY.work}>Praca</option>
                    <option value={TASK_CATEGORY.study}>Nauka</option>
                    <option value={TASK_CATEGORY.sport}>Sport</option>
                </select>
            </div>

            <div className="form-bottom">
                <div>
                    <input
                        type="checkbox"
                        id="priority"
                        checked={priority}
                        onChange={(e) => setPriority(e.target.checked)}
                    />
                    <label htmlFor="priority">Priorytet </label>
                </div>

                <button>Dodaj</button>
            </div>
        </form>
    );
};

export default Form;
