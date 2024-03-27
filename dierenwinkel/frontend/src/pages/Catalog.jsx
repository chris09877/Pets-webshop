// import axios from "axios";
// import { useEffect, useState } from "react";
// import { Link } from "react-router-dom";
// import OrderDetails from '../components/OrderDetails';
// import Navbar from "../components/NavBar";


// const Catalog = () => {
//     const [products, setProducts] = useState([]);
//     const [showBtn, setShowBtn] = useState(true);

//     useEffect(() => {
//         async function getCsrfToken() {
//             let cookieArray = document.cookie.split(';');
//             let csrfToken = null;
            
//             cookieArray.forEach(cookie => {
//                 let trimmedCookie = cookie.trim();
//                 if (trimmedCookie.startsWith("_xsrf=")) {
//                     csrfToken = trimmedCookie.substring("_xsrf=".length);
//                 }
//             });
            
//             return csrfToken;
//         }
    
//         async function fetchProducts() {
//             const token = await getCsrfToken();
//             console.log(token); 
            
//             try {
//                 const response = await axios.get('http://localhost:8080/api/product/all', {
//                     // headers: {
//                     //     'X-CSRF-Token': token
//                     // },
//                 });
//                 return response.data; 
//             } catch (error) {
//                 console.error('Error fetching products:', error);
//                 return []; 
//             }
//         }
    
//         async function loadProducts() {
//             const products = await fetchProducts();
//             console.log(products); 
//             setProducts(products); 
//         }
    
//         loadProducts();


        
//     }, []); 
    
//     const handleOrderClick = async () => {
        
//     };

//     const handleSubmit = async (e) => {
//         e.preventDefault();

//         // // Collect form data
//         // const formData = new FormData(e.target);
//         // const data = {};
//         // formData.forEach((value, key) => {
//         //     data[key] = value;
//         // });
//         // data['user'] = localStorage.getItem('userId');
//         // console.log(`data in handlesubmit: ${data}`);
//         // try {
//         //     console.log(`${config.apiUrl}/orders/update/${localStorage.getItem('userId')}`);
//         //     const response = await axios.patch(`${config.apiUrl}/orders/update/${localStorage.getItem('userId')}`, data);

//         //     //console.log(response.data); // Handle the response here (success message or other actions)

//         //     //   setOrderDetails(await response.data);
//         //     //   console.log(` data send to component: ${orderDetails}`);


//         // } catch (error) {
//         //     console.error('Error:', error); // Handle errors here
//         // }
//     };

//     return (
//         <>
//         {/* <h1>product</h1>
//         <ul>
//                 {products.map(product => (
//                     <a href={`http://localhost:5173/${product.id}`}>
//                     <li key={product.id}>
//                         <h2>{product.name}</h2>
//                         <p>Description: {product.description}</p>
//                         <p>Price: {product.price}</p>
//                         <p>Quantity: {product.quantity}</p>
//                     </li>
//                     </a>
//                 ))}
//             </ul> */}

// <div className="bg-gray-100 min-h-screen">
//       <header className="bg-blue-500 text-white p-4">
//         <h1 className="text-2xl font-bold">Welcome to My Website</h1>
//         <div className="flex justify-center items-center">
//                 <Navbar />
//             </div>
//             <div className="flex justify-start">
//                 <button onClick={handleOrderClick}>START ORDERING</button>
//             </div>

//       </header>

//       <main className="p-4">
//       <div>
//                 <OrderDetails

//                     title="Your Order"

//                 />
//        </div>

//        <h1>MENU</h1>
//             {Array.isArray(products) &&

//                 <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
//                     {products.map((product) => (
//                         <form key={product._id} onSubmit={(e) => handleSubmit(e)}>
//                             <div className="bg-white p-4 shadow-md">
//                                 <Link to={`/products/${product._id}`} className="block">
//                                     <h2 className="text-lg font-semibold mb-2">{product.name}</h2>
//                                     <img src={`/${product.name}.jpg`} alt={product.name} className="w-full h-40 object-cover mb-2" />
//                                     <p className="text-gray-600 mb-2">{product.price}</p>
//                                     <p className="text-sm text-gray-500 mb-2">{product.description}</p>

//                                 </Link>
//                                 <label className="block mb-2">
//                                     Quantity:
//                                     <input
//                                         type="number"
//                                         name="quantity"
//                                         defaultValue={1}
//                                         min={1}
//                                         className="block w-full border border-gray-300 rounded px-2 py-1"
//                                     />
//                                 </label>
//                                 <button hidden={showBtn} type="submit" className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
//                                     Add
//                                 </button>
//                                 <input type="hidden" name="name" value={product.name} />
//                                 <input type="hidden" name="price" value={product.price} />
//                                 <input type="hidden" name="user" value={userId} />
//                                 <input type="hidden" name="product_id" value={product._id} />
//                             </div>
//                         </form>
//                     ))}
//                 </div>

//             }
        
//       </main>

//       <footer className="bg-gray-500 text-white p-4">
//         <p>&copy; 2024 My Awesome Website</p>
//       </footer>
//     </div>
//         </>
//     );

// }





// export default Catalog;


import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import OrderDetails from '../components/OrderDetails';
import Navbar from '../components/NavBar';

const Catalog = () => {
    const [products, setProducts] = useState([]);
    const [showBtn, setShowBtn] = useState(true);

    useEffect(() => {
        async function fetchProducts() {
            try {
                const response = await axios.get('http://localhost:8080/api/product/all');
                setProducts(response.data);
            } catch (error) {
                console.error('Error fetching products:', error);
                setProducts([]);
            }
        }
        fetchProducts();
    }, []);

    const handleOrderClick = async () => {
        // Implement your logic for handling order click here
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        // Implement your form submission logic here
    };

    return (
        <div className="bg-gray-100 min-h-screen">
            <header className="bg-blue-500 text-white p-4">
                <h1 className="text-2xl font-bold">Welcome to My Website</h1>
                <div className="flex justify-center items-center">
                    <Navbar />
                </div>
                <div className="flex justify-start">
                    <button onClick={handleOrderClick}>START ORDERING</button>
                </div>
            </header>

            <main className="p-4">
                <div>
                    <OrderDetails title="Your Order" />
                </div>

                <h1>MENU</h1>
                <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
                    {products.map((product) => (
                        <form key={product._id} onSubmit={(e) => handleSubmit(e)}>
                            <div className="bg-white p-4 shadow-md">
                                <Link to={`/products/${product._id}`} className="block">
                                    <h2 className="text-lg font-semibold mb-2">{product.name}</h2>
                                    <img src={`/${product.name}.jpg`} alt={product.name} className="w-full h-40 object-cover mb-2" />
                                    <p className="text-gray-600 mb-2">{product.price}</p>
                                    <p className="text-sm text-gray-500 mb-2">{product.description}</p>
                                </Link>
                                <label className="block mb-2">
                                    Quantity:
                                    <input
                                        type="number"
                                        name="quantity"
                                        defaultValue={1}
                                        min={1}
                                        className="block w-full border border-gray-300 rounded px-2 py-1"
                                    />
                                </label>
                                <button hidden={showBtn} type="submit" className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
                                    Add
                                </button>
                                <input type="hidden" name="name" value={product.name} />
                                <input type="hidden" name="price" value={product.price} />
                                {/* You can add more hidden fields if needed */}
                            </div>
                        </form>
                    ))}
                </div>
            </main>

            <footer className="bg-gray-500 text-white p-4">
                <p>&copy; 2024 My Awesome Website</p>
            </footer>
        </div>
    );
};

export default Catalog;
