import React, {useState,useEffect} from 'react';
import axios from 'axios';
import Button from '@material-ui/core/Button';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import { Checkbox } from '@material-ui/core';
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';
import DatePicker from "react-datepicker";
import FormControlLabel from '@material-ui/core/FormControlLabel';
import "react-datepicker/dist/react-datepicker.css";

const useStyles = makeStyles({
    root: {
      minWidth: 275
    }
  });

const AddBookingForm = (props,context) => {
    const[rooms, setRooms] = useState([]);
    const[persons, setPersons] = useState([]);
    const[personId,setPersonId] = useState(1);
    const[selectedRooms, setSelectedRooms] = useState(new Set())
    const[startDate, setStartDate] = useState();
    const[endDate, setEndDate] = useState();


    const loadProfilesEvent = async (event) => {
      console.log("calling api");
      const resp = await axios.get(`/api/person/`, {redirect: 'follow'});
      if(resp.data.length !== persons.length){     
      setPersons(resp.data);
      }
      };

      const loadRoomsEvent = async () => {
        console.log("calling api");
        const resp = await axios.get(`/api/room/`);
        if (resp.data.length !== persons.length){    
        setRooms(resp.data);  }
        console.log(resp.data);
        
    }

    useEffect(()=>{
      loadProfilesEvent();
      loadRoomsEvent();
    },[])
    
  


    const onAddingRoom = (event) =>{
      let id = event.target.value;
      console.log(event.target.value + "Room id checked");
      console.log(id + 'Room id happy');
        if (selectedRooms.has(id)){
        selectedRooms.delete(id);
        setSelectedRooms(selectedRooms);
        } else {
            setSelectedRooms(selectedRooms.add(id));
        }
    }
    const handleSubmit = async (event) => {
        event.preventDefault();
        const resp = await axios.post('/api/booking/',
                    {
                        "responsiblePersonId": personId,
                        "roomIds": [...selectedRooms],
                        "serviceIds":[],
                        "checkInDate":startDate,
                        "checkOutDate":endDate
                    });
        console.log(resp);
        props.history.push('/persons');

    };
    const classes = useStyles();
    return (
        rooms && persons ?(
        <form onSubmit={handleSubmit} style={{marginTop : 0.5 +'cm'}}>
        <Card className={classes.root}>
        <CardContent>
            <Select 
            value={personId}
            placeholder="Responsible Person"
            onChange={(event)=>setPersonId(event.target.value)}>
                {persons.map(person => {
                return <MenuItem value={person.id}> {person.name} </MenuItem>
                })
                }
            </Select>
            <br/>
        
        {rooms.map(room => { 
        return (<><FormControlLabel
        control={
          <Checkbox
            value={room.id}
            onChange={onAddingRoom}
            name={`Room id : ${room.id} Capacity : ${room.capacity} Price : ${room.price} HasView : ${room.hasView}`}
          />
        }
        label={`Room id : ${room.id} Capacity : ${room.capacity} Price : ${room.price} HasView : ${room.hasView}`}
        /> <br/> </>)
        })}
        
        <DatePicker selected={startDate} onChange={date => setStartDate(date)} />
        <br/>
        <DatePicker selected={endDate} onChange={date => setEndDate(date)} />
        <br/>
        <Button type="submit" variant="contained" >Add Booking</Button>
        </CardContent>
        </Card>
        </form>) : <></>
    );
  }

  export default AddBookingForm;