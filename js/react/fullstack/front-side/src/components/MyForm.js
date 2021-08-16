import axios from 'axios';
import React, { useState } from 'react'
import { Form, Button } from 'react-bootstrap'


const MyForm = () => {



    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();

        console.log(name);
        console.log(surname);


        setName('');
        setSurname('');

        axios.post('http://localhost:5000/people', {
            name,
            surname
        })

    }





    return (

        <Form onSubmit={handleSubmit}>
            <Form.Group>
                <Form.Label>Imie</Form.Label>
                <Form.Control type="text" value={name} onChange={e => setName(e.target.value)} />
            </Form.Group>
            <Form.Group>
                <Form.Label>Nazwisko</Form.Label>
                <Form.Control type="text" value={surname} onChange={e => setSurname(e.target.value)} />
            </Form.Group>

            <Button variant="primary" type="submit">
                Dodaj
            </Button>
        </Form>
    )
}

export default MyForm
