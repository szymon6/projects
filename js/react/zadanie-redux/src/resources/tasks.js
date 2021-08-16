import { TASK_CATEGORY } from './constants';


export const tasks = [

    {
        id: 1,
        title: "Zadanie1",
        category: TASK_CATEGORY.work,
        priority: true
    },
    {
        id: 2,
        title: "Zadanie2",
        category: TASK_CATEGORY.study,
        priority: false
    }

];

export default tasks;