import Room from './Room.js';
import React, {useState, useEffect} from 'react';
import axios from 'axios';
import Grid from '@material-ui/core/Grid';
import UpdateRoomForm from './UpdateRoomForm.js';
const RoomList = (props) => {
    const [rooms,setRooms] = useState([]);
    const [updateRoom, setUpdateRoom] = useState(null);
    const loadProfilesEvent = async (event) => {
      console.log("calling api");
      const resp = await axios.get(`/api/room/`, {redirect: 'follow'});    
      setRooms(resp.data);
      };
    useEffect(()=>{
      loadProfilesEvent();
    },[updateRoom])
    const updateRoomEvent = (id) =>{
      
      console.log(updateRoom);
      setUpdateRoom(id);
      
      console.log(id);
    }

    return(
      updateRoom != undefined ? <UpdateRoomForm id={updateRoom} updateRoom={updateRoomEvent} />
      :
      <Grid
                container
                spacing={1}
                direction="row"
                justify="flex-start"
                alignItems="flex-start"
            >
        {rooms.map(room => {return <Grid item xs={12} sm={6} md={3} key={rooms.indexOf(room)}>
          <Room key={room.id} {...room} reloadProfiles={loadProfilesEvent} updateRoom={updateRoomEvent}/>
          </Grid>})
        }
        </Grid>
    );
  };

  export default RoomList;