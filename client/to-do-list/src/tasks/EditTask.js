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
import { Input } from "@mui/material";
import { Calendar } from 'primereact/calendar'
import "primeicons/primeicons.css"
import {TimePicker } from 'react-time-picker';
import { InputText } from "primereact/inputtext";


export default function EditTask() {

    var Task = function(title, description, finished, date, time) {
        this.title = title;
        this.description = description;
        this.finished = finished;
        this.date = date;
        this.time = time;
    } ;
    let { id } = useParams();
    
    const navigate = useNavigate();
    const navigateToHome=()=>{
        navigate('/')
      };

    const [task, setTask] = useState([]);
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    let [title, setTitlte] = useState([]);
    let [description, setDescription] = useState([]);
    let [date, setDate] = useState([]);
    const minDate = new Date();
    let [selectedTime, handleTimeChange] = useState([]);

    useEffect(()=>{
        loadTasks();
        
    },[])

    const loadTasks=async()=>{
        const result = await axios.get("http://localhost:8080/task/"+id);
        setTask(result.data);
        setTitlte(result.data.title);
        setDescription(result.data.description);
        setDate(result.data.date);
        handleTimeChange(result.data.time);
        console.log(title);
        console.log(description);
        console.log(selectedTime);
        console.log(date);
        }

    const upadateTask=async()=>{
        console.log(date)
        console.log(task.date)
        if(date!=task.date){
            let date2 = date.toISOString().slice(0, 10);
            let editask = new Task(title, description,false, date2, selectedTime);
            const result = await axios.put(`http://localhost:8080/task/${id}`,editask);
        }
        else {
            let editask = new Task(title, description,false, task.date, selectedTime);
            const result = await axios.put(`http://localhost:8080/task/${id}`,editask);
        }
        navigateToHome();
        }
    


    return(
        <div>
            <Card key={task.id} footer={task.date+" "+task.time} className="md:w-25rem">


               <InputText placeholder="title" value={title} onChange={(e) => setTitlte(e.target.value)} /> <br/><br/>
               <InputText placeholder="description" value={description} onChange={(e) => setDescription(e.target.value)}/> <br/><br/>

    
               <Calendar value={date} required onChange={(e) => setDate(e.value)} readOnlyInput /><br /><br/>
            <TimePicker value={selectedTime}
                onChange={handleTimeChange}
                // value={selectedTime}
            /><br/><br/>
    
    
    
    
    
    
    
    <Button label="Save changes" severity="info" outlined onClick={handleShow} /><br/><br/>
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
        Are you sure you want to save this changes?
        </Modal.Body>
        <Modal.Footer>
          <Button onClick={
            upadateTask
        }>
          Yes
          </Button>
        </Modal.Footer>
      </Modal>
        </div>
    )
}