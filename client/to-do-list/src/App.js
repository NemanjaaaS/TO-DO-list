import logo from './logo.svg';
import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css"
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import {Routes, Route, useNavigate} from 'react-router-dom';
import AddTask from './tasks/AddTask';
import ViewTask from './tasks/ViewTask'
import EditTask from './tasks/EditTask';

function App() {
  return (
    <div className="App">
      <Navbar></Navbar>
      <Routes>
          <Route path="/addTask" element={<AddTask />} />
          <Route path="/" element={<Home/>}></Route>
          <Route path="/:id" element={<ViewTask />} ></Route>
          <Route path="/editTask/:id" element={<EditTask />} />
        </Routes>
    </div>
  );
}

export default App;
