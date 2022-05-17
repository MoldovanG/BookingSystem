import React, {useState, useEffect} from 'react';
import Card from '@material-ui/core/Card';
import Typography from '@material-ui/core/Typography';
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

const Booking = props => {
  const classes = useStyles();
  const booking = props;
  const person = booking.invoice.responsiblePerson;
  return (
    <Card className={classes.root}>
    <CardContent>
    <Typography variant="h5" component="h2"> Nume : {person.name}</Typography>
    <Typography variant="h5" component="h2"> Prenume : {person.surname}</Typography>
    <Typography variant="h5" component="h2"> Buletin: {person.identityCardIdentifier}</Typography>
    <Typography variant="h5" component="h2"> Adresa :{person.address}</Typography>
    <Typography variant="h5" component="h2"> Email :{person.email}</Typography>
    <Typography variant="h5" component="h2"> Discount : {booking.invoice.discount}</Typography>
    <Typography variant="h5" component="h2"> Pret : {booking.invoice.price}</Typography>
    <Typography variant="h5" component="h2"> Platit :{booking.invoice.paid === true ? 'yes' : 'no'}</Typography>
    {booking.reservedRooms.map(room => {return  <><Typography variant="h5" component="h2"> Id: {room.id}</Typography>
    <Typography variant="h5" component="h2"> {room.capacity}</Typography>
    <Typography variant="h5" component="h2">Panorama: {room.hasView === true ? 'yes' : 'no'}</Typography>
    <Typography variant="h5" component="h2">Pret Camera : {room.price}</Typography><br/></>})}
    <Typography variant="h5" component="h2"> CheckInDate : {booking.checkInDate}</Typography>
    <Typography variant="h5" component="h2"> CheckOutDate : {booking.checkOutDate}</Typography>

    </CardContent>
    </Card>
  );
}

export default Booking;