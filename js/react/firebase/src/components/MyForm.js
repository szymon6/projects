import firebase from '../firebase'

import React, { useState } from 'react'
import { Form, Button } from 'react-bootstrap'


const MyForm = () => {



    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();

        console.log(name);
        console.log(surname);

        writeData(name, surname);

        setName('');
        setSurname('');

    }

    function writeData(name, surname) {

        const person = {
            name,
            surname,
        };

        firebase.database().ref('people').push(person);

        // to samo jeśli chciał bym wcześniej znać wygenerowany klucz:
        // const newPostKey = firebase.database().ref().child('people').push().key;  //ref().child('people') to to samo co ref('people')
        // firebase.database().ref('people/' + newPostKey).update(person);
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
