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
  async function getCsrfToken() {
    let token = document.querySelector("meta[name='_csrf']").getAttribute("content");
    console.log(`The token in the meta tag ${token}`);
    return token;
  }
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

      const response = await axios.post(
        'http://localhost:8080/register',
        formData,
        // {
        //   withCredentials: false,
        //   headers: {
        //     'X-CSRF-Token': getCsrfToken()
        //   },
        // }
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
        <h2 className="text-xl font-semibold">Register User</h2>
        <form onSubmit={handleSubmit} className="space-y-4">
          <div className="flex flex-col space-y-1">
            <label className="text-gray-600">First Name:</label>
            <input type="text" name="firstname" value={formData.firstname} onChange={handleChange}
              className="p-2 border rounded-md" />
          </div>

          <div className="flex flex-col space-y-1">
            <label className="text-gray-600">Last Name:</label>
            <input type="text" name="lastname" value={formData.lastname} onChange={handleChange}
              className="p-2 border rounded-md" />
          </div>

          <div className="flex flex-col space-y-1">
            <label className="text-gray-600">Address:</label>
            <input type="text" name="address" value={formData.address} onChange={handleChange}
              className="p-2 border rounded-md" />
          </div>

          <div className="flex flex-col space-y-1">
            <label className="text-gray-600">Postcode:</label>
            <input type="text" name="postcode" value={formData.postcode} onChange={handleChange}
              className="p-2 border rounded-md" />
          </div>

          <div className="flex flex-col space-y-1">
            <label className="text-gray-600">Number:</label>
            <input type="text" name="number" value={formData.number} onChange={handleChange}
              className="p-2 border rounded-md" />
          </div>

          <div className="flex flex-col space-y-1">
            <label className="text-gray-600">Birthdate:</label>
            <input type="date" name="birthdate" value={formData.birthdate} onChange={handleChange}
              className="p-2 border rounded-md" />
          </div>

          <div className="flex flex-col space-y-1">
            <label className="text-gray-600">Phone:</label>
            <input type="text" name="phone" value={formData.phone} onChange={handleChange}
              className="p-2 border rounded-md" />
          </div>

          <div className="flex flex-col space-y-1">
            <label className="text-gray-600">Mail:</label>
            <input type="email" name="mail" value={formData.mail} onChange={handleChange}
              className="p-2 border rounded-md" />
          </div>

          <div className="flex flex-col space-y-1">
            <label className="text-gray-600">Password:</label>
            <input type="password" name="password" value={formData.password} onChange={handleChange}
              className="p-2 border rounded-md" />
          </div>

          <button type="submit"
            className="py-2 px-4 bg-blue-500 text-white font-semibold rounded-lg shadow-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-400 focus:ring-opacity-75">
            Register
          </button>
        </form>
     
     </div> 
    
  );
};

export default RegisterUserForm;