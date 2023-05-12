import React from 'react'
import {Routes, Route, useNavigate} from 'react-router-dom';
import AddTask from '../tasks/AddTask';

export default function Navbar() {

  const navigate = useNavigate();
  const navigateToAddTask=()=>{
    navigate('/addTask')
  };
  const navigateToHome=()=>{
    navigate('/')
  };
  return (
    <div>
        <nav className="navbar navbar-expand-lg bg-body-tertiary">
  <div className="container-fluid">
    <button className='btn btn-outline-dark' onClick={navigateToHome}>Tasks</button>
    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span className="navbar-toggler-icon"></span>
    </button>
    <button className='btn btn-outline-dark' onClick={navigateToAddTask}>Add Task</button>
  </div>
</nav>
    </div>
  )
}
