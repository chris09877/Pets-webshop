import React, { useState } from 'react';
import axios from 'axios';

const Form = () => {
  // const storedUserId = localStorage.getItem('userId');

  const [formData, setFormData] = useState({
    date: '',
    content: '',
    userInfo: '',
    user_id: '',
    
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Custom validations
    if (!formData.name.match(/^[a-zA-Z\s]*$/)) {
      alert('Name cannot contain numbers');
      return;
    }



    if (isNaN(formData.phone)) {
      alert('Phone numbers accept only numbers');
      return;
    }

    try {
      const response = await axios.patch(`${config.apiUrl}/orders`, formData);
      console.log('Order updated:', response.data);
      // console.log(`${config.apiUrl}/orders/checkout/${storedUserId}`);
      alert('Order proceed');
      //localStorage.setItem('userId', null);
      localStorage.clear();
      window.location.href = '/';
    } catch (error) {
      console.error('Error updating order:', error);
      // Handle error cases
    }
  };

  return (
    <form onSubmit={handleSubmit} className="my-form max-w-md mx-auto p-4 bg-white shadow-md rounded-lg">

      <div >
        <label htmlFor="order_date" >Order Date:</label>
        <input
          type="datetime-local"
          id="order_date"
          name="order_date"
          required
          value={formData.order_date}
          onChange={handleChange}
          
        />
      </div>
      <div >
        <label htmlFor="name" >Name:</label>
        <input
          type="text"
          id="name"
          name="name"
          required
          value={formData.name}
          onChange={handleChange}
          
        />
      </div>
      <div >
        <label htmlFor="phone" >Phone:</label>
        <input
          type="text"
          id="phone"
          name="phone"
          required
          value={formData.phone}
          onChange={handleChange}
          
        />
      </div>
      <div >
        <label htmlFor="address" >Address:</label>
        <input
          type="text"
          id="address"
          name="address"
          required
          value={formData.address}
          onChange={handleChange}
          
        />
      </div>
      <input type="hidden" name='user' value={""} />
      <button type="submit">Submit</button>
    </form>
  );
};

export default Form;