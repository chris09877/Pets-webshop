import React from 'react';
import axios from 'axios';

const LogoutBtn = () => {
  //const { logout } = useAuth();

  const handleLogout = async () => {
    try {
      const response = await axios.post('http://localhost:8080/login?logout');
      console.log('Logout successful:', response.data);
      window.location.href = 'http://localhost:5713/';
    } catch (error) {
      console.error('Logout failed:', error);
    }    window.location = '/';

  };

  return (
    <button onClick={handleLogout}>
      Logout
    </button>
  );
};

export default LogoutBtn;