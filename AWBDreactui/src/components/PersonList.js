import Person from './Person.js';
import React, {useState, useEffect} from 'react';
import axios from 'axios';
import Grid from '@material-ui/core/Grid';
const PersonList = (props) => {
    const [persons,setPersons] = useState([]);

    const loadProfilesEvent = async (event) => {
      console.log("calling api");
      const resp = await axios.get(`/api/person/`, {redirect: 'follow'});
      if(resp.data.length !== persons.length){     
      setPersons(resp.data);
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
        {persons.map(person => {return <Grid item xs={12} sm={6} md={3} key={persons.indexOf(person)}>
          <Person key={person.id} {...person} reloadProfiles={loadProfilesEvent}/>
          </Grid>})
        }
        </Grid>
    );
  };

  export default PersonList;