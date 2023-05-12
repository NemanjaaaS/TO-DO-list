import {Card } from 'primereact/card';
import {InputText } from 'primereact/inputtext'
import React, { useEffect, useState } from 'react'
import { Button } from 'primereact/button';
import {Dialog} from 'primereact/dialog';
import "primereact/resources/themes/lara-light-indigo/theme.css"
import "primeicons/primeicons.css"
import axios from 'axios'
import { Calendar } from 'primereact/calendar'
import "primeicons/primeicons.css"
import {TimePicker } from 'react-time-picker';
import { MobileTimePicker } from 'react-time-picker';
import {Routes, Route, useNavigate, Link} from 'react-router-dom';
import Modal from 'react-bootstrap/Modal';

export default function AddTask() {
    var Task = function(title, description, finished, date, time) {
        this.title = title;
        this.description = description;
        this.finished = finished;
        this.date = date;
        this.time = time;
    } ;
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    const navigate = useNavigate();
    let [title, setTitlte] = useState([]);
    let [description, setDescription] = useState([]);
    let [date, setDate] = useState([]);
    const minDate = new Date();
    let [selectedTime, handleTimeChange] = useState([]);
    const navigateToHome=()=>{
        navigate('/')
      };
    
    return (
        <div>
             <InputText required placeholder='title' value={title} onChange={(e) => setTitlte(e.target.value)} /><br></br><br/>
            <InputText required placeholder='description' value={description} onChange={(e) => setDescription(e.target.value)} /><br/><br/>
            <Calendar required id="minmax" value={date} onChange={(e) => setDate(e.value)} minDate={minDate} readOnlyInput /><br /><br/>
            <TimePicker
                onChange={handleTimeChange}
                value={selectedTime}
            /><br/><br/>
            <Button onClick={async()=>{
                if(title!="" && description!="" && date!="" && selectedTime!="") {
                let date2 = date.toISOString().slice(0, 10);
                let task = new Task(title, description,false, date2, selectedTime);
                 let result = await axios.post(`http://localhost:8080/task`,task);
                 navigateToHome();
                }
                else {
                    handleShow();
                }
            }} icon="pi pi-check" label="Save"></Button>
             <Modal
        show={show}
        onHide={handleClose}
        backdrop="static"
        keyboard={false}
      >
        <Modal.Header closeButton>
          <Modal.Title>Error</Modal.Title>
        </Modal.Header>
        <Modal.Body>
        You must fill in all fields
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
          Understood
          </Button>
        </Modal.Footer>
      </Modal>
        </div>
         
    )
}
