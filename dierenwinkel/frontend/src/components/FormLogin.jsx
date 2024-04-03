import React, { useState } from 'react';
// import { useAuth } from './AuthContext.jsx';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
//import config from "../config";

const FormLogin = () => {
  //const { setAuthInfo } = useAuth();
  const [credentials, setCredentials] = useState({ username: '', password: '' });
  //   const navigate = useNavigate();

  //   const redirect = () => {

  //     navigate('/admin panel');
  //   }
  const handleLogin = async () => {
    // try {
    //   //console.log(`${config.apiUrl}}/users/login`);
    //   console.log(credentials);
    //   const response = await axios.post(`http://localhost:8080/user/login`,
    //     {
    //       headers: {
    //         credentials,
    //       },
    //       credentials: "include",
    //     }
    //   );

    //   const token = response.data.token;
    //   setAuthInfo({ token: { token }, isAuthenticated: true });
    //   Cookies.set("token", token, { expires: 1 })
    //   //window.location = '/admin panel';



    // } catch (error) {
    //   console.log(error);
    // }

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
                    name="password"
                    value={credentials.password}
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