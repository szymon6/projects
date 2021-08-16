const express = require('express')
const cors = require('cors')
const mysql = require('mysql');
const app = express()

app.use(cors())
app.use(express.urlencoded({ extended: false }))
app.use(express.json())


const connection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'baza_react1'
});

connection.connect();

//get all
app.get('/people', (req, res) => {

    connection.query('SELECT * from person ', (error, results) => {
        if (error) throw error;
        res.send(results)
    });

})

// get one
app.get('/people/:id', (req, res) => {

    connection.query('SELECT * from person where id = ? ', [req.params.id], (error, results) => {
        if (error) throw error;
        res.send(results)
    });

})

// delete
app.delete('/people/:id', (req, res) => {

    connection.query('DELETE from person where id = ? ', [req.params.id], (error, results) => {
        if (error) throw error;
        res.send(results);
    });

})

// add
app.post('/people', (req, res) => {

    connection.query('INSERT INTO person SET name = ?, surname = ?  ', [req.body.name, req.body.surname], (error, results) => {
        if (error) throw error;
        res.send(results);
    });

})


// update
app.put('/people', (req, res) => {

    const { id, name, surname } = req.body;

    connection.query('UPDATE person SET name = ?, surname = ? where id = ?', [name, surname, id], (error, results) => {
        if (error) throw error;
        res.send(results);
    });

})







app.listen(5000, () => { })
