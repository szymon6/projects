import axios from 'axios';
import { useEffect, useState } from 'react';
import DataTable from './components/DataTable';
import './App.css'
import MyForm from './components/MyForm';

function App() {
  const [data, setData] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:5000/people')
      .then((result) => {
        setData(result.data);
      });
  });



  return (

    <div className="App" >

      <div className="content">


        <MyForm />
        <DataTable data={data} />

      </div>

    </div >
  );
}

//TODO  CRUD: dodawanie, usuwanie edycja wyszukiwarka

export default App;
