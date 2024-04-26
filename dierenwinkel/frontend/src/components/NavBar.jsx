import React from 'react';
import { Routes, Route, NavLink, BrowserRouter, useLocation } from 'react-router-dom';
import Home from '../pages/Home.jsx';
import OrderConfirmation from '../pages/OrderConfirmation.jsx';
import Product from '../pages/Product.jsx';
import Register from '../pages/Register.jsx';
import Catalog from '../pages/Catalog.jsx';
import Login from '../pages/Login.jsx';
import Cookies from 'js-cookie';
// import { AuthProvider } from './AuthContext';
import LogoutBtn from './LogoutBtn.jsx';
// const Navbar = () => {
//   const session_id = Cookies.get('session_id');

//   return (
//     <div className="flex justify-end">
//       <nav className="w-full">
//         <ul className="flex justify-end space-x-4">
//           <li>
//             <NavLink to="/" >Home</NavLink>
//           </li>
//           <li>
//             <NavLink to="/catalog" >Catalog</NavLink>
//           </li>
//           <li>
//             <NavLink to="/checkout" >Checkout</NavLink>
//           </li>
//           {session_id && (
//             <>
//               <div>
//                 <LogoutBtn />
//               </div>
//             </>
//           )}
//           {!session_id  && (
//             <div>
//               <button className="flex justify-end ml-10">
//                 <NavLink to="/login" >Login</NavLink>
//               </button>
//               <button className="flex justify-end ml-10">
//                 <NavLink to="/refister" >Register</NavLink>
//               </button>
//             </div>
//           )}
//         </ul>
//       </nav>
//     </div>
//   );
// };

const Navbar = () => {
  const session_id = Cookies.get('session_id');
  const location = useLocation();
  const currentPath = location.pathname;

  return (
    <div className="flex justify-end">
      <nav className="w-full">
        <ul className="flex justify-end space-x-4">
          <li>
            <NavLink to="/" >Home</NavLink>
          </li>
          <li>
            <NavLink to="/catalog" >Catalog</NavLink>
          </li>
          <li>
            <NavLink to="/checkout" >Checkout</NavLink>
          </li>
          {session_id && (
            <div>
              <LogoutBtn />
            </div>
          )}
          {!session_id && (
            <div>
              {currentPath === "/login" ? (
                <button className="flex justify-end ml-10">
                  <NavLink to="/register" >Register</NavLink>
                </button>
              ) : (
                <button className="flex justify-end ml-10">
                  <NavLink to="/login" >Login</NavLink>
                </button>
              )}
            </div>
          )}
        </ul>
      </nav>
    </div>
  );
};
export default Navbar;