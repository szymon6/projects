import '../styles/Add.css'
import React, {useState} from 'react'
import {Link} from 'react-router-dom'

const Add = ({expences, setExpences}) => {
    const [date, setDate] = useState(new Date().toISOString().slice(0, 10));

    const [name, setName] = useState();
    const [price, setPrice] = useState();

    function add() {
        const newExpence = {
            id: expences.length + 1,
            name: name,
            price: parseInt(price),
            date: date,
        }

        const newExpences = [...expences, newExpence];
        setExpences(newExpences);
        setName('');
    }

    return (
        <div className="wrapper">
            <header>
                <p>Add new</p>
                <Link className="link" to="/"> Back </Link>
            </header>

            <div className="card">
                <div className="add-bar">
                    <input type="text" placeholder="Name" value={name} onChange={(e) => setName(e.target.value)}/>
                    <input type="text" placeholder="Price" value={price} onChange={(e) => setPrice(e.target.value)}/>
                    <input type="date" value={date} onChange={(e) => setDate(e.target.value)}/>

                    <button onClick={add}> Add</button>
                </div>
            </div>
        </div>
    )
}

export default Add
