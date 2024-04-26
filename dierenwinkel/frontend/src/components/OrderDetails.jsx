import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';


const OrderDetails = () => {
  const [isExpanded, setIsExpanded] = useState(false);
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [totalSum, setTotalSum] = useState(0); // State to store the total sum

  useEffect(() => {
    let isMounted = true;  // Track mounted status
    if (isExpanded) {
      getOrderDetails().then(data => {
        if (isMounted && data) {
          setProducts(data);
          // Calculate the total sum of all products
          const total = data.reduce((acc, product) => acc + product.total, 0);
          setTotalSum(total);
        }
      });
    }
    return () => {
      isMounted = false;  // Set false on cleanup
    };
  }, [isExpanded]);

  const toggleExpansion = () => {
    setIsExpanded(!isExpanded);
  };

  const getOrderDetails = async () => {
    let user_id = Cookies.get("userId");
    setLoading(true);
    setError(null);
    try {
      const response = await axios.get('http://localhost:8080/orders/find', {
        params: { userId: user_id },
        withCredentials: true
      });
      console.log(response.data.j);
      return response.data; // Ensure that data is returned for use in then()
    } catch (error) {
      setError('Error fetching order details');
      console.error('Error fetching order details:', error);
      return null;  // Return null in case of error to handle it in then()
    } finally {
      setLoading(false);
    }
  };

  const handleOrder = () => {
    // Implement logic for handling the order button click
  };

  return (
    <div className="bg-gray-500 p-4 shadow-md rounded-lg inline-block max-w-full min-w-40 fixed left-3/4">
      <header className="cursor-pointer" onClick={toggleExpansion}>
        <h3 className="text-xl font-bold mb-2">Your Order</h3>
      </header>
      {isExpanded && (
        <div className="mt-4">
          <p className="text-lg font-semibold">Order Details:</p>
          {loading && <p>Loading...</p>}
          {error && <p className="text-red-500">{error}</p>}
          {products.length > 0 ? (
            <div className="mt-2">
              {products.map((product) => (
                <div key={product.id} className="mb-2 p-2 border border-gray-300 rounded-lg">
                  <p className="text-lg font-medium">Name: {product.name}</p>
                  <p className="text-sm">Quantity: {product.quantity}</p>
                  <p className="text-sm">Total: {product.total}</p>
                </div>
              ))}
              <p className="text-lg font-bold">Total Sum: {totalSum}</p> {/* Display the total sum */}
            </div>
          ) : (
            <p className="mt-2 text-lg">No order details available.</p>
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
};

export default OrderDetails;
