import React, {useState, useEffect, setState} from 'react';
import axios from 'axios';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';

const useStyles = makeStyles({
    root: {
      minWidth: 275
    }
  });

const InsertPersonForm = (props,context) => {
    const [name,setName] = useState('');
    const [surname,setSurname] = useState('');
    const [identityCardIdentifier,setIdentityCardIdentifier] = useState('');
    const [address,setAddress] = useState('');
    const [email,setEmail] = useState('');
    const handleSubmit = async (event) => {
        event.preventDefault();
        const resp = await axios.post('/api/person/',
                    {
                        "name":name,
                        "surname":surname,
                        "identityCardIdentifier":identityCardIdentifier,
                        "address":address,
                        "email":email
                    });
        console.log(resp);
        setName('');
        setSurname('');
        setIdentityCardIdentifier('');     
        setAddress('');
        setEmail('');

        props.history.push('/persons');

    };
    const classes = useStyles();
    return (
        <Grid item xs={12} sm={6} md={3} >
        <form onSubmit={handleSubmit} style={{marginTop : 0.5 +'cm'}}>
        <Card className={classes.root}>
        <CardContent>
            <TextField 
            type="string"
            value={name}
            onChange={event => setName(event.target.value)}
            placeholder="Person Name" 
            required >
            </TextField>
            <br/>
            <TextField 
            type="string"
            value={surname}
            onChange={event => setSurname(event.target.value)}
            placeholder="Person surname" 
            required >
            </TextField>
            <br/>
            <TextField 
            type="string"
            value={identityCardIdentifier}
            onChange={event => setIdentityCardIdentifier(event.target.value)}
            placeholder="Person Identity Card Identifier" 
            required >
            </TextField>
            <br/>
            <TextField 
            type="string"
            value={address}
            onChange={event => setAddress(event.target.value)}
            placeholder="Person Address" 
            required >
            </TextField>
            <br/>
            <TextField 
            type="string"
            value={email}
            onChange={event => setEmail(event.target.value)}
            placeholder="Person Email" 
            required >
            </TextField>
            <br/>
           
            <Button type="submit" variant="contained" >Add Person</Button>
        </CardContent>
        </Card>
        </form>
        </Grid>
    );
  }

  export default InsertPersonForm;