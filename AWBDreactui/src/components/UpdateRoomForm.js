import React, {useState, useEffect, setState} from 'react';
import axios from 'axios';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import Select from '@material-ui/core/Select';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import MenuItem from '@material-ui/core/MenuItem';


const useStyles = makeStyles({
    root: {
      minWidth: 275
    }
  });

const UpdateRoomForm = (props,context) => {
    const [capacity,setCapacity] = useState('');
    const [hasView,setHasView] = useState(0);
    const [price, setPrice] = useState(0);
    const handleSubmit = async (event) => {
        event.preventDefault();
        const resp = await axios.put(`/api/room/${props.id}`,
                    {
                    "capacity": Number(capacity),
                    "hasView": Boolean(hasView),
                    "price": Number(price)   
                    });
        console.log(resp);
        setCapacity('');
        setHasView(false);
        setPrice(0);
        props.updateRoom(null);

    };
    const LoadRoomEvent = async (event) => {
        const resp = await axios.get(`/api/room/${props.id}`, {redirect: 'follow'});
        setCapacity(resp.data.capacity);
        setHasView(resp.data.hasView);
        setPrice(resp.data.price);
    }
    useEffect(() =>{
        LoadRoomEvent();
    },[]);
    const classes = useStyles();
    return (
        <Grid item xs={12} sm={6} md={3} >
        <form onSubmit={handleSubmit} style={{marginTop : 0.5 +'cm'}}>
        <Card className={classes.root}>
        <CardContent>
            <TextField 
            type="number"
            value={capacity}
            onChange={event => setCapacity(event.target.value)}
            placeholder="Room capacity" 
            required >
            </TextField>
            <br/>
            <Select 
            value={Number(hasView)}
            placeholder="Room with view"
            onChange={(event)=>setHasView(event.target.value)}>
            <MenuItem value={1}>Yes</MenuItem>
            <MenuItem value={0}>No</MenuItem>
            </Select>
            <br/>
            <TextField
            type="number"
            value={price}
            onChange={event => setPrice(event.target.value)}
            placeholder="Room price" 
            required 
            />
            <Button type="submit" variant="contained" >Update Room</Button>
        </CardContent>
        </Card>
        </form>
        </Grid>
    );
  }

  export default UpdateRoomForm;