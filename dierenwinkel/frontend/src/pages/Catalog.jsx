import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import OrderDetails from '../components/OrderDetails';
import NavBar from '../components/NavBar';

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
        <>
            {/* <div className="bg-gray-100 min-h-screen"> */}
            {/* <header className="bg-custom-gray text-white p-4">
                <h1 className="text-2xl font-bold">Pets Pals</h1>
                <div className="flex justify-center items-center">
                    <Navbar />
                </div>
                <div className="flex justify-start">
                    <button onClick={handleOrderClick}>START ORDERING</button>
                </div>
            </header> */}

            <header className="bg-custom-gray text-white p-4 flex items-center justify-between">
                <h1 className="text-2xl font-bold">Pets Pals</h1>
                <div className="flex-grow flex justify-center">
                    <img src="path/to/your/logo.png" alt="Logo" className="w-20 h-20" /> {/* Adjust the w-20 h-20 to fit your logo size */}
                </div>
                <div className="flex justify-end">
                    <NavBar />
                </div>
            </header>

            <main className="p-4">
                <div className="flex justify-start">
                    <button onClick={handleOrderClick}>START ORDERING</button>
                </div>
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

            <div className="flex flex-col min-h-screen">
                <footer className="bg-custom-gray text-white p-4 mt-auto">
                    <p>&copy; 2024 My Awesome Website</p>
                </footer>
            </div>
            {/* </div> */}
        </>
    );
};

export default Catalog;
