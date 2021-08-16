import { useEffect, useState } from 'react';
import DataTable from './components/DataTable';
import './App.css';
import MyForm from './components/MyForm';

import firebase from './firebase';

function App() {
  const [data, setData] = useState([]);

  useEffect(() => {
    //read
    firebase
      .database()
      .ref('people')
      .on('value', (snapshot) => {
        const dataAsObject = snapshot.val();
        const dataAsArray = objectToArray(dataAsObject);
        setData(dataAsArray);
      });
  }, []);

  function objectToArray(object) {
    const array = [];

    for (const [key, value] of Object.entries(object)) {
      const item = {
        id: key,
        ...value,
      };
      array.push(item);
    }

    return array;
  }

  return (
    <div className="App">
      <div className="content">
        <MyForm />
        <DataTable data={data} />
      </div>
    </div>
  );
}



export default App;
