
import React, {useState, useEffect} from 'react';
import './App.css';
import RoomList from './components/RoomList';
import PersonList from './components/PersonList';
import BookingList from './components/BookingList';
import LoginComponent from './components/LoginComponent';
import InsertRoomForm from './components/InsertRoomForm';
import InsertPersonForm from './components/InsertPersonForm';
import AuthenticatedRoute from './components/AuthenticatedRoute';
import AuthenticationService from './service/AuthenticationService';
import AddBookingForm from './components/AddBookingForm';
import {
  Route,
  NavLink,
  HashRouter,
} from "react-router-dom";

const App = props => {
    const isUserLoggedIn = AuthenticationService.isUserLoggedIn();
    function handleLogout(e) {
      console.log("Logout operation performed");
      AuthenticationService.logout();
      window.location.reload(false);
    };
    return (
      <HashRouter>
      <h1>Booking system Demo</h1>
      <ul className="header">
      {isUserLoggedIn &&<li><NavLink to="/rooms">Show Rooms</NavLink></li>}
        {isUserLoggedIn && <li><NavLink to="/addRoom">Add Room</NavLink></li>}
        {isUserLoggedIn && <li><NavLink to="/persons">Persons</NavLink></li>}
        {isUserLoggedIn && <li><NavLink to="/addPerson">Add Person</NavLink></li>}
        {isUserLoggedIn && <li><NavLink to="/addBooking">Add Booking</NavLink></li>}  
        {isUserLoggedIn && <li><NavLink to="/bookings">Show Bookings</NavLink></li>}            
        {!isUserLoggedIn && <li><NavLink to="/login">Login</NavLink></li>}
        {isUserLoggedIn && <li> <NavLink onClick={handleLogout} to="/login">Logout</NavLink></li>}
      </ul>
    <div className="content">
    <Route path="/" exact component={LoginComponent} />
    <Route path="/login" exact component={LoginComponent} />
    <AuthenticatedRoute path="/rooms" exact component={RoomList} />
    <AuthenticatedRoute path="/addRoom" exact component={InsertRoomForm} />
    <AuthenticatedRoute path="/persons" exact component={PersonList} />
    <AuthenticatedRoute path="/addPerson" exact component={InsertPersonForm} />
    <AuthenticatedRoute path="/addBooking" exact component={AddBookingForm} />
    <AuthenticatedRoute path="/bookings" exact component={BookingList} />
    </div>
    </HashRouter>
  );
  
}

export default App;
