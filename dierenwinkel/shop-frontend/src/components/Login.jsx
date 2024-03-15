import React, { useState } from 'react';
import { useAuth } from './AuthContext.jsx';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import config from "../config";
import Cookies from 'js-cookie';

const Login = () => {
  const { setAuthInfo } = useAuth();
  const [credentials, setCredentials] = useState({ username: '', password: '' });
//   const navigate = useNavigate();

//   const redirect = () => {

//     navigate('/admin panel');
//   }
  const handleLogin = async () => {
    try {
      console.log(`${config.apiUrl}}/users/login`);
      console.log(credentials);
      const response = await axios.post(`http://localhost:8080/user/login`,
        {
          headers: {
            credentials,
          },
          credentials: "include",
        }
      );

      const token = response.data.token;
      setAuthInfo({ token: { token }, isAuthenticated: true });
      Cookies.set("token", token, { expires: 1 })
      //window.location = '/admin panel';



    } catch (error) {
      console.log(error);
    }

  };

  const handleChange = (e) => {
    setCredentials({ ...credentials, [e.target.name]: e.target.value });
  };

  return (

    <div >
      <h2 >Login</h2>
      <form
        onSubmit={(e) => {
          e.preventDefault();
          handleLogin();
        }}
        
      >
        <input
          type="text"
          placeholder="mail"
          name="mail"
          value={credentials.mail}
          onChange={handleChange}
          
        />
        <input
          type="password"
          placeholder="Password"
          name="password"
          value={credentials.password}
          onChange={handleChange}
          
        />
        <button
          type="submit"
          
        >
          Login
        </button>
      </form>
    </div>
  );
};

export default Login;
