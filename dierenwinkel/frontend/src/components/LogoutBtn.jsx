import React from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';
const LogoutBtn = () => {

  const handleLogout = async () => {

    try {
      const response = await axios.post('http://localhost:8080/signout');
      console.log('Logout successful:', response.data);
      window.location.href = '/';
      console.log("seession id" + Cookies.get('session_id'));
      Cookies.remove("session_id");
      Cookies.remove("userId");
      console.log("seession id after delete" + Cookies.get('session_id'));

    } catch (error) {
      console.error('Logout failed:', error);
      alert('Logout failed. Please try again.'); // Display an alert on failure
    }
  }

  return (
    <button onClick={handleLogout}>
      Logout
    </button>
  );
};

export default LogoutBtn;