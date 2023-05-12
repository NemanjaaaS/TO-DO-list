import React, { useEffect, useState } from 'react'
import axios from 'axios'
import "primereact/resources/themes/lara-light-indigo/theme.css";  
import { Card }   from 'primereact/card';   
import {Checkbox} from 'primereact/checkbox' 
import "primeicons/primeicons.css"
import { Button } from 'primereact/button';
import {Routes, Route, useNavigate, Link} from 'react-router-dom';
import ViewTask from '../tasks/ViewTask';


export default function Home() {
    const [tasks, setTask] = useState([]);

    async function finishedTask(id) {
      let result = await axios.post(`http://localhost:8080//task/${id}`)
      setTask(result.data);
    }
    const navigate = useNavigate();
    const navigateToHome=()=>{
      navigate('/')
    };

    const navigateToTask=()=>{
      navigate('/id')
    };

    useEffect(()=>{
        loadTasks();
    },[])

    const loadTasks=async()=>{
        const result = await axios.get("http://localhost:8080/tasks")
        setTask(result.data);
        }

  return (
    <div className='container'>
        
    {
        tasks.map((task)=>(

          <Card key={task.id} title={task.title} footer={task.date+" "+task.time} className="md:w-25rem">
    <Link to={`${task.id}`} className='btn btn-outline-dark' > View </Link>
</Card>


        ))
    }

     </div>
  )
}
