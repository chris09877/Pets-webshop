import React, { useState } from 'react';
import axios from 'axios';

const OrderDetails = ({ title }) => {

  const storedUserId = localStorage.getItem('userId');
  console.log(`user id from component: ${storedUserId}`);
  const [isExpanded, setIsExpanded] = useState(false);
  const [products, setProducts] = useState([]);
  const toggleExpansion = () => {
    setIsExpanded(!isExpanded);
    getOrderDetails();
  };
  const handleOrder = () => {
    window.location.href = '/checkout'; // Redirect to checkout page on button click
  };
  const getOrderDetails = async () => {
    // try {
    //   console.log(`fetch from component order etails: ${config.apiUrl}/orders/user/${storedUserId}`);
    //   const response = await axios.get(`${config.apiUrl}/orders/user/${storedUserId}`);
    //   const data = response.data;
    //   setProducts(data.products);


    // }
    // catch (error) {
    //   console.log(error);
    // }
  };


  return (
    <div className="bg-gray-100 p-4 shadow-md">
      <header className="cursor-pointer" onClick={toggleExpansion}>
        <h3 className="text-xl font-bold mb-2">Your Order</h3>
      </header>
      {isExpanded && (
        <div className="mt-4">
          <p className="text-lg font-semibold">Order Details:</p>

          {products ? (
            <div className="mt-2">
              {products.map((product, index) => (
                <div key={index} className="mb-2 p-2 border border-gray-300 rounded">
                  <p className="text-lg font-medium">Name: {product.name}</p>
                  <p className="text-sm">Quantity: {product.quantity}</p>
                </div>
              ))}
            </div>

          ) : (
            <p className="mt-2 text-lg">Let's order a tasty product!</p>
          )}
          <div className="mt-4">
            <button className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600" onClick={handleOrder}>
              Order
            </button>
          </div>
        </div>
      )}

    </div>

  );
}

export default OrderDetails;
