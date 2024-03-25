import React from 'react';
import { Routes, Route, NavLink, BrowserRouter } from 'react-router-dom';
import Home from '../pages/Home.jsx';
import OrderConfirmation from '../pages/OrderConfirmation.jsx';
import Product from '../pages/Product.jsx';
import Register from '../pages/Register.jsx';
import Catalog from '../pages/Catalog.jsx';
import Login from '../pages/Login.jsx';
// import { AuthProvider } from './AuthContext';
import LogoutBtn from './LogoutBtn.jsx';
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
            <NavLink to="/catalog" >Catalog</NavLink>
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