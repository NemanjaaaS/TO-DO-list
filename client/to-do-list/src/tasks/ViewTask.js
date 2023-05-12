import { useParams } from "react-router";
import React, { useEffect, useState } from 'react'
import axios from 'axios'
import "primereact/resources/themes/lara-light-indigo/theme.css";  
import { Card }   from 'primereact/card';   
import {Checkbox} from 'primereact/checkbox' 
import "primeicons/primeicons.css"
import { Button } from 'primereact/button';
import {Routes, Route, useNavigate, Link} from 'react-router-dom';
import Modal from 'react-bootstrap/Modal';


export default function ViewTask() {
    let { id } = useParams();

    const [task, setTask] = useState([]);
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    async function finishedTask(id) {
      let result = await axios.post(`http://localhost:8080//task/${id}`)
      setTask(result.data);
    }
    const navigate = useNavigate();
    const navigateToHome=()=>{
      navigate('/')
    };
    const navigateToEdit=()=>{
        navigate(`/editTask/${id}`)
      };

    useEffect(()=>{
        loadTasks();
    },[])

    const loadTasks=async()=>{
        const result = await axios.get("http://localhost:8080/task/"+id);
        setTask(result.data);
        }
    const deleteTask=async()=>{
        const result = await axios.delete(`http://localhost:8080/task/${id}`);
        setTask(result.data);
        navigateToHome();
        }



    return(
        <div>
            <Button label="Edit" severity="info" outlined onClick={navigateToEdit} />
            <Card key={task.id} title={task.title} footer={task.date+" "+task.time} className="md:w-25rem">
    <p className="m-0">
        {task.description}
    </p><br/>
    <Button label="Delete" severity="danger" outlined onClick={handleShow} /><br/><br/>
    <Checkbox onChange={e => {
      finishedTask(task.id)
    // navigateToHome()
    }} checked={task.finished}></Checkbox>
            </Card>
            <Modal show={show}
        onHide={handleClose}
        backdrop="static"
        keyboard={false}
      >
            <Modal.Header closeButton>
          <Modal.Title>Warning</Modal.Title>
        </Modal.Header>
        <Modal.Body>
        Are you sure you want to delete this task?
        </Modal.Body>
        <Modal.Footer>
          <Button onClick={deleteTask}>
          Yes
          </Button>
        </Modal.Footer>
      </Modal>
        </div>
    )
}