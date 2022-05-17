import Booking from './Booking.js';
import React, {useState, useEffect} from 'react';
import axios from 'axios';
import Grid from '@material-ui/core/Grid';
const BookingList = (props) => {
    const [bookings,setBookings] = useState([]);

    const loadProfilesEvent = async (event) => {
      console.log("calling api");
      const resp = await axios.get(`/api/booking/paginated?page=0&limit=15`, {redirect: 'follow'});
      if(resp.data.length !== bookings.length){     
      setBookings(resp.data.content);
      }
      };
    useEffect(()=>{
      loadProfilesEvent();
    },[])
    return(
      <Grid
                container
                spacing={1}
                direction="row"
                justify="flex-start"
                alignItems="flex-start"
            >
        {bookings.map(booking => {return <Grid item xs={12} sm={6} md={3} key={bookings.indexOf(booking)}>
          <Booking key={booking.id} {...booking}/>
          </Grid>})
        }
        </Grid>
    );
  };

  export default BookingList;