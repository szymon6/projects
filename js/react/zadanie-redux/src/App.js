import './App.css';
import Form from './comonents/Form';
import TaskList from './comonents/TaskList';

function App() {
  return (
    <div className="App">

      <div className="container">
        <h1>TODO APP</h1>

        <Form />
        <TaskList />
      </div>
    </div>
  );
}

export default App;
