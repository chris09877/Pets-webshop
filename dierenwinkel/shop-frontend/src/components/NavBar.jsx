import React from 'react';
import { Routes, Route, NavLink, BrowserRouter } from 'react-router-dom';
import Pizzas from '../Pizzas';
import PizzaShow from '../PizzaShow';
import ValidateOrder from '../ValidateOrder';
import AdminPanel from '../AdminPanel';
import OrderShow from '../OrderShow';
import Login from '../Login';
import ProtectedRoute from './ProtectedRoutes';
import { AuthProvider } from './AuthContext';
import LogoutBtn from './LogoutBtn';
const Navbar = () => {
  // Check if token exists in cookies or local storage
  const tokenExists = localStorage.getItem('token');// || document.cookie.includes('token');

  return (

    <div >

      <nav >
        <ul >
          <li>
            <NavLink to="/" >Home</NavLink>
          </li>
          <li>
            <NavLink to="/pizzas" >Menu</NavLink>
          </li>
          <li>
            <NavLink to="/checkout" >Checkout</NavLink>
          </li>
          {tokenExists && (
            <>

              <li>
                <NavLink to="/admin panel" >Admin Panel</NavLink>
              </li>
              <div>
                <LogoutBtn />
              </div>
            </>
          )}
          {tokenExists === null && (
            <div >
              <button className="flex justify-end ml-10">
                <NavLink to="/login" >Login</NavLink>
              </button>
            </div>
          )}
        </ul>

      </nav>


    </div>
  );
};

export default Navbar;
