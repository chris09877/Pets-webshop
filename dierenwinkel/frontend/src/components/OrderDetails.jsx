// import React, { useState } from 'react';
// import axios from 'axios';

// const OrderDetails = ({ title }) => {

//   const storedUserId = localStorage.getItem('userId');
//   console.log(`user id from component: ${storedUserId}`);
//   const [isExpanded, setIsExpanded] = useState(false);
//   const [products, setProducts] = useState([]);
//   const toggleExpansion = () => {
//     setIsExpanded(!isExpanded);
//     getOrderDetails();
//   };
//   const handleOrder = () => {
//     window.location.href = '/checkout'; // Redirect to checkout page on button click
//   };
//   const getOrderDetails = async () => {
//     // try {
//     //   console.log(`fetch from component order etails: ${config.apiUrl}/orders/user/${storedUserId}`);
//     //   const response = await axios.get(`${config.apiUrl}/orders/user/${storedUserId}`);
//     //   const data = response.data;
//     //   setProducts(data.products);


//     // }
//     // catch (error) {
//     //   console.log(error);
//     // }
//   };


//   return (
//     <div className="bg-gray-100 p-4 shadow-md">
//       <header className="cursor-pointer" onClick={toggleExpansion}>
//         <h3 className="text-xl font-bold mb-2">Your Order</h3>
//       </header>
//       {isExpanded && (
//         <div className="mt-4">
//           <p className="text-lg font-semibold">Order Details:</p>

//           {products ? (
//             <div className="mt-2">
//               {products.map((product, index) => (
//                 <div key={index} className="mb-2 p-2 border border-gray-300 rounded">
//                   <p className="text-lg font-medium">Name: {product.name}</p>
//                   <p className="text-sm">Quantity: {product.quantity}</p>
//                 </div>
//               ))}
//             </div>

//           ) : (
//             <p className="mt-2 text-lg">Let's order a tasty product!</p>
//           )}
//           <div className="mt-4">
//             <button className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600" onClick={handleOrder}>
//               Order
//             </button>
//           </div>
//         </div>
//       )}

//     </div>

//   );
// }

// export default OrderDetails;
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';

// const OrderDetails = () => {
//   const [isExpanded, setIsExpanded] = useState(false);
//   const [products, setProducts] = useState([]);

//   // useEffect(() => {
//   //   if (isExpanded) {
//   //     getOrderDetails();
//   //   }
//   // }, [isExpanded]);

//   useEffect(() => {
//     let isMounted = true;  // Track mounted status
//     if (isExpanded) {
//       getOrderDetails().then(data => {
//         if (isMounted) setProducts(data);
//       });
//     }
//     return () => {
//       isMounted = false;  // Set false on cleanup
//     };
//   }, [isExpanded]);
  

//   // const toggleExpansion = () => {
//   //   setIsExpanded(!isExpanded);
//   // };

//   // const getOrderDetails = async () => {
//   //   let user_id = Cookies.get("userId");
//   //   try {
//   //     const response = await axios.get('http://localhost:8080/orders/find',{
//   //       params: {userId:user_id},
//   //       withCredentials:true
//   //     });
//   //     setProducts(response.data);
//   //     console.log(products);
//   //   } catch (error) {
//   //     console.error('Error fetching order details:', error);
//   //   }
//   // };
//   const [loading, setLoading] = useState(false);
// const [error, setError] = useState(null);

// const getOrderDetails = async () => {
//   let user_id = Cookies.get("userId");
//   setLoading(true);
//   setError(null);
//   try {
//     const response = await axios.get('http://localhost:8080/orders/find', {
//       params: { userId: user_id },
//       withCredentials: true
//     });
//     setProducts(response.data);
//   } catch (error) {
//     setError('Error fetching order details');
//     console.error('Error fetching order details:', error);
//   } finally {
//     setLoading(false);
//   }
// };


//   const handleOrder = () => {
//     // Implement logic for handling the order button click
//   };

//   return (
//     <div className="bg-gray-500 p-4 shadow-md rounded-lg inline-block max-w-full min-w-40 fixed left-3/4">
//       <header className="cursor-pointer" onClick={toggleExpansion}>
//         <h3 className="text-xl font-bold mb-2">Your Order</h3>
//       </header>
//       {isExpanded && (
//         <div className="mt-4">
//           <p className="text-lg font-semibold">Order Details:</p>
//           {products.length > 0 ? (
//             <div className="mt-2">
//               {products.map((product) => (
//                 <div key={product.id} className="mb-2 p-2 border border-gray-300 rounded-lg">
//                   <p className="text-lg font-medium">Name: {product.name}</p>
//                   <p className="text-sm">Quantity: {product.quantity}</p>
//                 </div>
//               ))}
//             </div>
//           ) : (
//             <p className="mt-2 text-lg">No order details available.</p>
//           )}
//           <div className="mt-4">
//             <button className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600" onClick={handleOrder}>
//               Order
//             </button>
//           </div>
//         </div>
//       )}
//     </div>
//   );
// };

// export default OrderDetails;


// import React, { useState, useEffect } from 'react';
// import axios from 'axios';
// import Cookies from 'js-cookie';

const OrderDetails = () => {
  const [isExpanded, setIsExpanded] = useState(false);
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    let isMounted = true;  // Track mounted status
    if (isExpanded) {
      getOrderDetails().then(data => {
        if (isMounted && data) setProducts(data);
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
      console.log(response.data);
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
                </div>
              ))}
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

