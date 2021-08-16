import React, { useState } from 'react'
import './App.css'
import { BrowserRouter, Route } from 'react-router-dom'
import Add from './components/Add'
import expencesData from './resources/expences'
import Expences from './components/Expences'




function App() {
  const [expences, setExpences] = useState(expencesData);


  return (
    <BrowserRouter>
      <Route path="/" exact render={() => <Expences expences={expences} />} />
        <Route path="/add" exact render={() => <Add expences={expences} setExpences={setExpences} />} />




    </BrowserRouter>
  )
}

export default App
