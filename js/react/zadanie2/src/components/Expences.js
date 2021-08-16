import '../styles/Expences.css';
import React, { useState } from 'react';
import { Link } from 'react-router-dom';

const Expences = ({ expences }) => {
    const [showedExpences, setShowedExpecnces] = useState(expences);
    const [startDate, setStartDate] = useState('2021-01-01');
    const [endDate, setEndDate] = useState('2021-12-31');

    let totalPrice = 0;
    showedExpences.forEach((element) => {
        totalPrice += element.price;
    });

    function changeShowedExpences() {
        let newShowedExpences = [...expences];
        newShowedExpences = newShowedExpences.filter((item) => {
            return item.date >= startDate && item.date <= endDate;
        });

        setShowedExpecnces(newShowedExpences);
    }

    const showedExpencesList = showedExpences.map((item) => {
        return (
            <div className="expence" key={item.id}>
                <div className="info">
                    <p className="name">{item.name}</p>
                    <p className="date">{item.date} </p>
                </div>

                <p className="price">${item.price}</p>
            </div>
        );
    });

    return (
        <div className="wrapper">
            <header>
                <p> Viewing <b>{showedExpences.length}</b> expences totalling <b>${totalPrice}</b> </p>
                <Link className="link" to="/add">Add Expence</Link>
            </header>

            <div className="card">
                <div className="search-bar">
                    <input type="date" defaultValue="2021-01-01" onChange={(e) => setStartDate(e.target.value)} />
                    <input type="date" defaultValue="2021-12-31" onChange={(e) => setEndDate(e.target.value)} />
                    <button onClick={changeShowedExpences}>Search</button>
                </div>

                <div className="expences">
                    <div className="expences-info">
                        <p>Expence</p>
                        <p>Amout</p>
                    </div>
                    <div className="expences-list">
                        {showedExpencesList}
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Expences;
