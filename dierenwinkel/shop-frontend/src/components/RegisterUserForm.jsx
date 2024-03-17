import React, { useState } from 'react';
import axios from 'axios';

const RegisterUserForm = () => {
  
  const [formData, setFormData] = useState({
    firstname: '',
    lastname: '',
    address: '',
    postcode: '',
    number: '',
    birthdate: '',
    phone: '',
    mail: '',
    password: ''
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log(formData);
     // Custom validations
     if (!formData.firstname.match(/^[a-zA-Z\s]*$/)) {
      alert('First name cannot contain numbers');
      return;
    }



    if (isNaN(formData.phone)) {
      alert('Phone numbers accept only numbers');
      return;
    }

    try {
      let g = localStorage.getItem('csrfToken');
      console.log(g);
      console.log(localStorage.getItem('csrfToken'));
      //const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");
      const response = await axios.post(
        'http://localhost:8080/user/add',
        formData,
        {
          withCredentials: false,
          headers: {
            'X-CSRF-Token': localStorage.getItem('csrfToken'), // Include the CSRF token in the request headers
          },
        }
      );
      console.log('User added:', response.data);
      //console.log(`${config.apiUrl}/user/add/${storedUserId}`);
      alert('user registerred');
      //localStorage.setItem('userId', null);
      // localStorage.clear();
      // window.location.href = '/';
    } catch (error) {
      console.error('Error creating user:', error);
    }
  };

  return (
    <div>
      <h2>Register User</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>First Name:</label>
          <input type="text" name="firstname" value={formData.firstname} onChange={handleChange} />
        </div>
        <div>
          <label>Last Name:</label>
          <input type="text" name="lastname" value={formData.lastname} onChange={handleChange} />
        </div>
        <div>
          <label>Address:</label>
          <input type="text" name="address" value={formData.address} onChange={handleChange} />
        </div>
        <div>
          <label>Postcode:</label>
          <input type="text" name="postcode" value={formData.postcode} onChange={handleChange} />
        </div>
        <div>
          <label>Number:</label>
          <input type="text" name="number" value={formData.number} onChange={handleChange} />
        </div>
        <div>
          <label>Birthdate:</label>
          <input type="date" name="birthdate" value={formData.birthdate} onChange={handleChange} />
        </div>
        <div>
          <label>Phone:</label>
          <input type="text" name="phone" value={formData.phone} onChange={handleChange} />
        </div>
        <div>
          <label>Mail:</label>
          <input type="email" name="mail" value={formData.mail} onChange={handleChange} />
        </div>
        <div>
          <label>Password:</label>
          <input type="password" name="password" value={formData.password} onChange={handleChange} />
        </div>
        <button type="submit">Register</button>
      </form>
    </div>
  );
};

export default RegisterUserForm;
