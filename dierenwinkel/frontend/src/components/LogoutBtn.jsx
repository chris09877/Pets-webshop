import React from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';
const LogoutBtn = () => {

  const handleLogout = async () => {

    try {
      const response = await axios.post('http://localhost:8080/signout');
      console.log('Logout successful:', response.data);
  
      // Delete the cookies before redirecting
      Cookies.remove("session_id", { path: '/' });
      Cookies.remove("userId", { path: '/' });
  
      // Verify that cookies are removed
      console.log("Session ID after delete: " + Cookies.get('session_id'));
      console.log("User ID after delete: " + Cookies.get('userId'));
  
      // Redirect to the homepage
      window.location.href = '/';
  }catch (error) {
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