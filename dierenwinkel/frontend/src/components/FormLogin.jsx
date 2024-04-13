import React, { useState, useEffect } from 'react';
// import { useAuth } from './AuthContext.jsx';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import Cookies from 'js-cookie';
//import config from "../config";

const FormLogin = () => {
  //const { setAuthInfo } = useAuth();
  const [credentials, setCredentials] = useState({ mail: '', pwd: '' });
  const [allCookies, setAllCookies] = useState({ mail: '', pwd: '' });

  const handleLogin = async () => {
    try {
      const response = await axios.post('http://localhost:8080/login', credentials, {
        withCredentials: true
      });
      console.log('Login successful:', response.data);
      const cookies = response.headers['set-cookie'];
      // const cookies = document.cookie;
      console.log(response.headers.get('set-cookie'));
      console.log(response.headers.get('Set-Cookie'));
      console.log(response.headers.get('Set-Cookies'));

      console.log(response.headers);
      console.log(response);

      if (cookies) {
        const xsrfToken = cookies.find(cookie => cookie.startsWith('xsrf-token='));
        if (xsrfToken) {
          const tokenValue = xsrfToken.split(';')[0].split('=')[1];
          console.log('XSRF Token:', tokenValue);
          Cookies.set('csrf-token', tokenValue, { expires: 4 / 24 }); // Expires in 4 hours
        }
  
        const session_id = cookies.find(cookie => cookie.startsWith('jsessionid='));
        if (session_id) {
          const sessionValue = session_id.split(';')[0].split('=')[1];
          console.log('jsessionid:', sessionValue);
          Cookies.set('session_id', sessionValue, { expires: 4 / 24 }); // Expires in 4 hours
        }
  
        const userId = cookies.find(cookie => cookie.startsWith('userID='));
        if (userId) {
          const idValue = userId.split(';')[0].split('=')[1];
          console.log('User ID:', idValue);
          Cookies.set('userId', idValue, { expires: 4 / 24 }); // Expires in 4 hours
        }
      }
    } 
    catch (error) {
      console.error('Login failed:', error.response ? error.response.data : error.message);
    }
  };

    

  

  const handleChange = (e) => {
    setCredentials({ ...credentials, [e.target.name]: e.target.value });
  };

  return (

    <div className="min-h-screen py-6 flex flex-col justify-center sm:py-12">
      <div className="relative py-3 sm:max-w-xl sm:mx-auto">
        <div
          className="absolute inset-0 bg-gradient-to-r from-blue-200 to-blue-500 shadow-lg transform -skew-y-6 sm:skew-y-0 sm:-rotate-6 sm:rounded-3xl">
        </div>
        <div className="relative px-4 py-10 bg-gradient-to-r from-gray-300 to-gray-500 shadow-lg sm:rounded-3xl sm:p-20">
          <div className="max-w-md mx-auto">
            <div>
              <h1 className="text-2xl font-semibold text-center">Login Form </h1>
            </div>
            <form onSubmit={(e) => {
              e.preventDefault();
              handleLogin();
            }} className="divide-y divide-gray-200">
              <div className="py-8 text-base leading-6 space-y-4 text-white sm:text-lg sm:leading-7">
                <div className="relative">
                  <input
                    type="text"
                    placeholder="email@example.com"
                    name="mail"
                    value={credentials.mail}
                    onChange={handleChange}
                    className="peer h-10 w-full border-b-2 border-gray-300 text-white focus:outline-none focus:borer-rose-600"
                  />
                  {/* <label for="email" className="absolute left-0 -top-3.5 text-gray-600 text-sm peer-placeholder-shown:text-base peer-placeholder-shown:text-gray-440 peer-placeholder-shown:top-2 transition-all peer-focus:-top-3.5 peer-focus:text-gray-600 peer-focus:text-sm">Email Address</label> */}
                </div>
                <div className="relative">
                  <input
                    type="password"
                    autoComplete="off"
                    placeholder="Password"
                    name="pwd"
                    value={credentials.pwd}
                    onChange={handleChange}
                    className="peer h-10 w-full border-b-2 border-gray-300 text-white focus:outline-none focus:borer-rose-600"
                  />
                  {/* <label for="password" className="absolute left-0 -top-3.5 text-gray-600 text-sm peer-placeholder-shown:text-base peer-placeholder-shown:text-gray-440 peer-placeholder-shown:top-2 transition-all peer-focus:-top-3.5 peer-focus:text-gray-600 peer-focus:text-sm">Password</label> */}
                </div>
                <div className="relative">
                  <button className="bg-blue-500 text-white rounded-md px-2 py-1">Login</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default FormLogin;