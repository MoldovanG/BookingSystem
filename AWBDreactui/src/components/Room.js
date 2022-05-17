import React, {useState, useEffect} from 'react';
import Axios from 'axios';
import Button from '@material-ui/core/Button';
import Card from '@material-ui/core/Card';
import Typography from '@material-ui/core/Typography';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles({
  root: {
    minWidth: 275
  },
  bullet: {
    display: 'inline-block',
    margin: '0 2px',
    transform: 'scale(0.8)',
  },
  title: {
    fontSize: 14,
  },
  pos: {
    marginBottom: 12,
  },
});

const Room = props => {
  const classes = useStyles();
  const room = props;
  const deleteHandler = async () =>{
    let resp = await Axios.delete(`/api/room/${room.id}`);
    props.reloadProfiles();
  }
  const updateHandler = async() => {
    props.updateRoom(room.id);
  }
  return (
    <Card className={classes.root}>
    <CardContent>
    <Typography className={classes.title} color="textSecondary" gutterBottom> Id: {room.id}</Typography>
    <Typography variant="h5" component="h2"> {room.capacity}</Typography>
    <Typography className={classes.pos} color="textSecondary">Type: {room.hasView === true ? 'yes' : 'no'}</Typography>
    <Typography variant="h5" component="h2"> {room.price}</Typography>
    </CardContent>
    <CardActions>
      <Button variant="outlined" onClick={deleteHandler}>Delete</Button>
      <Button variant="outlined" onClick={updateHandler}>Update</Button>
    </CardActions>
    </Card>
  );
}

export default Room;