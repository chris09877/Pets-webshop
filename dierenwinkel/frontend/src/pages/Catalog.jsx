import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import OrderDetails from '../components/OrderDetails';
import NavBar from '../components/NavBar';
import Filter from '../components/Filter';
import Cookies from 'js-cookie';
// const Catalog = () => {
//     const [products, setProducts] = useState([]);
//     const [showBtn, setShowBtn] = useState(true);
//     const [productData, setProductData] = useState([]);


//     useEffect(() => {
//         async function fetchProducts() {
//             try {
//                 const response = await axios.get('http://localhost:8080/api/product/all');
//                 setProducts(response.data);
//             } catch (error) {
//                 console.error('Error fetching products:', error);
//                 setProducts([]);
//             }
//         }
//         fetchProducts();
//     }, []);


//     const handleProductChange = (updatedProducts) => {
//         setProducts(updatedProducts);
//     };


//     const handleSubmit = async (e) => {
//         e.preventDefault();
//         try {
//             const response = await axios.get(`http://localhost:8080/orders/exist`, Cookies.get('session_id'));

//             if (response.status === 200) {
               
//                 const patchResponse = await axios.patch(`http://localhost:8080/orders/update`, {
                    
//                         "product": productData.values()
                    
//                 });
//                 console.log('Patch successful:', patchResponse.data);
//             }
//              else if (response.status === 404) {
//                 const currentDate = new Date();
//                 const formattedDate = currentDate.toISOString().split('T')[0];
//                 const anotherResponse = await axios.post(`http://localhost:8080/orders/create`,
//                     {
//                         "userId": Cookies.get(userId), // Replace with the actual user ID
//                         "products": productData.values(), // Replace with the actual product IDs
//                         "content": "",
//                         "date": formattedDate, // Replace with the actual date in yyyy-MM-dd format
//                         "userInfo": Cookies.get('session_id')
//                     }
//                 );
//                 console.log('Handled 404, new data:', anotherResponse.data);
//             }
//         } 
//         catch (error) {
//             console.error('Error in initial request:', error);
//             // Handle errors (e.g., display error message)
//         }

//         console.log('Product updated successfully:', response.data);
//         // Handle successful update (e.g., display confirmation message, reset form)
//     } 
    
// };

// return (
//     <>
//         <header className="bg-custom-gray text-white p-4 flex items-center justify-between">
//             <h1 className="text-2xl font-bold">Pets Pals</h1>
//             <div className="flex-grow flex justify-center">
//                 <img src="path/to/your/logo.png" alt="Logo" className="w-20 h-20" /> {/* Adjust the w-20 h-20 to fit your logo size */}
//             </div>
//             <div className="flex justify-end">
//                 <NavBar />
//             </div>
//         </header>

//         <main className="p-4">
//             <div className="flex justify-start">
//                 {/* <button onClick={handleOrderClick}>START ORDERING</button> */}
//                 <Filter onProductChange={handleProductChange} />
//             </div>
//             <div>
//                 <OrderDetails title="Your Order" />
//             </div>

//             <h1>MENU</h1>
//             <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
//                 {products.map((product) => (
//                     <form key={product.id} onSubmit={(e) => handleSubmit(e)}>
//                         <div className="bg-white p-4 shadow-md">
//                             <Link to={`/products/${product.id}`} className="block">
//                                 <h2 className="text-lg font-semibold mb-2">{product.name}</h2>
//                                 <img src={`/${product.name}.jpg`} alt={product.name} className="w-full h-40 object-cover mb-2" />
//                                 <p className="text-gray-600 mb-2">{product.price}</p>
//                                 <p className="text-gray-600 mb-2">{product.categories}</p>
//                                 <p className="text-sm text-gray-500 mb-2">{product.description}</p>
//                             </Link>
//                             <label className="block mb-2">
//                                 Quantity:
//                                 <input
//                                     type="number"
//                                     name="quantity"
//                                     defaultValue={1}
//                                     min={1}
//                                     className="block w-full border border-gray-300 rounded px-2 py-1"
//                                 />
//                             </label>
//                             <button type="submit" className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600" onClick={() => {
//                                 if (!Cookies.get('session_id')) {
//                                     alert("You must be logged in before starting your shopping journey (;")
//                                 }
//                                 else {
//                                     setProductData(product.id)
//                                 }
//                             }}>
//                                 Add
//                             </button>
//                             <input type="hidden" name="name" value={product.name} />
//                             <input type="hidden" name="price" value={product.price} />
//                             {/* You can add more hidden fields if needed */}
//                         </div>
//                     </form>
//                 ))}
//             </div>
//         </main>

//         <div className="flex flex-col min-h-screen">
//             <footer className="bg-custom-gray text-white p-4 mt-auto">
//                 <p>&copy; 2024 My Awesome Website</p>
//             </footer>
//         </div>

//     </>
// );
//                         ;

// export default Catalog;
const Catalog = () => {
    const [products, setProducts] = useState([]);
    const [showBtn, setShowBtn] = useState(true);
    const [productData, setProductData] = useState({});

    useEffect(() => {
        async function fetchProducts() {
            try {
                const response = await axios.get('http://localhost:8080/api/product/all');
                setProducts(response.data);
                console.log(`product contain: ${products}`);
                console.log(products);
            } catch (error) {
                console.error('Error fetching products:', error);
                setProducts([]);
            }
        }
        fetchProducts();
    }, []);

    const handleProductChange = (updatedProducts) => {
        setProducts(updatedProducts);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        // try {
        //     const response = await axios.get(`http://localhost:8080/orders/exist`, {
        //         params: { sessionId: Cookies.get('session_id') },
        //         withCredentials: true 
        //     });
        //     console.log(response.status);
        //     if (response.status === 200) {
        //         const patchResponse = await axios.patch(`http://localhost:8080/orders/update`, {
        //             "product": productData.values(),
        //             withCredentials: true 

        //         });
        //         console.log('Patch successful:', patchResponse.data);
        //     } else if (response.status === 404) {
        //         const currentDate = new Date();
        //         const formattedDate = currentDate.toISOString().split('T')[0];
        //         const anotherResponse = await axios.post(`http://localhost:8080/orders/create`, {
        //             "userId": Cookies.get(userId), // Replace with the actual user ID
        //             "products": productData.values(), // Replace with the actual product IDs
        //             "content": "",
        //             "date": formattedDate, // Replace with the actual date in yyyy-MM-dd format
        //             "userInfo": Cookies.get('session_id'),
        //             withCredentials: true 

        //         });
        //         console.log('Handled 404, new data:', anotherResponse.data);
        //     }
        // } catch (error) {
        //     console.error('Error in initial request:', error);
        // }
        // try {
        //     const response = await axios.get(`http://localhost:8080/orders/exist`, {
        //         params: { sessionId: Cookies.get('session_id') },
        //         withCredentials: true 
        //     });
        //     console.log(response.status);
        //     if (response.status === 200) {
        //         const patchResponse = await axios.patch(`http://localhost:8080/orders/update`, {
        //             "product": productData.values(),
        //             withCredentials: true 
        //         });
        //         console.log('Patch successful:', patchResponse.data);
        //     }
        // } catch (error) {
        //     if (error.response && error.response.status === 404) {
        //         console.log("inside if for 404 error");
        //         console.log(`This is the product data: ${productData})}`);
            
        //         const currentDate = new Date();
        //         const formattedDate = currentDate.toISOString().split('T')[0];
        //         const anotherResponse = await axios.post(`http://localhost:8080/orders/create`, {
        //             "userId": Cookies.get('userId'), // Ensure 'userId' is correctly defined or fetched
        //             "products": productData, // Ensure this method correctly extracts product IDs
        //             "content": "",
        //             "date": formattedDate,
        //             "userInfo": Cookies.get('session_id'),
        //             withCredentials: true 
        //         });
        //         console.log('Handled 404, new data:', anotherResponse.data);
        //     } else {
        //         console.error('Error in request:', error);
        //     }
        // }
        const productsArray = [productData]; // Ensure this is an array of product objects
    try {
        const sessionId = Cookies.get('session_id');
        if (!sessionId) {
            alert("Please login to continue.");
            return;
        }

        // Simulate checking for an existing order
        const response = await axios.get(`http://localhost:8080/orders/exist`, {
            params: { sessionId: sessionId },
            headers: {
                "Content-Type": "application/json;charset=UTF-8",
                "Cookie": "JESSIONID=" + Cookies.get("session_id")
        
        },
            withCredentials: true
        });

        if (response.status === 200) {
            const patchResponse = await axios.patch(`http://localhost:8080/orders/update`, {
                products: productsArray, // Send as array
                withCredentials: true,
                headers: {Cookie: "JESSIONID=" + Cookies.get("session_id")},
            });
            console.log('Patch successful:', patchResponse.data);
        }
    } catch (error) {
        const sessionId = Cookies.get('session_id');
        console.log(Cookies.get('session_id'));

        if (error.response && error.response.status === 404) {
            const currentDate = new Date();
            const formattedDate = currentDate.toISOString().split('T')[0];
            const anotherResponse = await axios.post(`http://localhost:8080/orders/create`, {
                headers: {
                    "Content-Type": "application/json;charset=UTF-8",
                    "Cookie": "JESSIONID=" + Cookies.get("session_id")
                },

                userId: Cookies.get('userId'), // Ensure 'userId' is correctly defined or fetched
                products: productsArray.map((product) => ({
                    id: product.id,
                    price: product.price,
                    description: product.description,
                    quantity: product.quantity,
                    name: product.name,
                    categories: product.categories
                })), 
                content: "",
                date: formattedDate,
                userInfo: sessionId,
                withCredentials: true 
            });
            console.log('Handled 404, new data:', anotherResponse.data);
        } else {
            console.error('Error in request:', error);
        }
    }
    };

    return (
        <>
            <header className="bg-custom-gray text-white p-4 flex items-center justify-between">
                <h1 className="text-2xl font-bold">Pets Pals</h1>
                <div className="flex-grow flex justify-center">
                    <img src="path/to/your/logo.png" alt="Logo" className="w-20 h-20" />
                </div>
                <div className="flex justify-end">
                    <NavBar />
                </div>
            </header>

            <main className="p-4">
                <div className="flex justify-start">
                    <Filter onProductChange={handleProductChange} />
                </div>
                <div>
                    <OrderDetails title="Your Order" />
                </div>

                <h1>MENU</h1>
                <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
                    {products.map((product) => (
                        <form key={product.id} onSubmit={(e) => handleSubmit(e)}>
                            <div className="bg-white p-4 shadow-md">
                                <Link to={`/products/${product.id}`} className="block">
                                    <h2 className="text-lg font-semibold mb-2">{product.name}</h2>
                                    <img src={`/${product.name}.jpg`} alt={product.name} className="w-full h-40 object-cover mb-2" />
                                    <p className="text-gray-600 mb-2">{product.price}</p>
                                    <p className="text-gray-600 mb-2">{product.categories}</p>
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
                                <button type="submit" className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600" onClick={() => {
                                    if (!Cookies.get('session_id')) {
                                        alert("You must be logged in before starting your shopping journey (;")
                                    } else {
                                        setProductData(product)
                                    }
                                }}>
                                    Add
                                </button>
                                <input type="hidden" name="name" value={product.name} />
                                <input type="hidden" name="price" value={product.price} />
                            </div>
                        </form>
                    ))}
                </div>
            </main>

            <div className="flex flex-col min-h-screen">
                <footer className="bg-custom-gray text-white p-4 mt-auto">
                    <p>&copy; 2024 My Awesome Website</p>
                </footer>
            </div>
        </>
    );
};

export default Catalog;