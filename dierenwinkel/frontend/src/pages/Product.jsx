import NavBar from "../components/NavBar";
import axios from 'axios';
import { Link } from "react-router-dom";
import ProductImages from "../components/ProductImages";

const Product = () => {


    return (
        <>
            <header className="bg-custom-gray text-white p-4 flex items-center justify-between">
                <h1 className="text-2xl font-bold">Pets Pals</h1>
                <div className="flex-grow flex justify-center">
                    <img src="path/to/your/logo.png" alt="Logo" className="w-20 h-20" /> {/* Adjust the w-20 h-20 to fit logo size */}
                </div>
                <div className="flex justify-end">
                    <NavBar />
                </div>
            </header>

            <main className="p-4">
  <div className="grid grid-cols-2 gap-4">
    
    <ProductImages />
    <div className="container max-w-lg mx-auto bg-white rounded-lg shadow-md p-8">
      <h2 className="text-xl font-bold">Colorful Bird Cage</h2>
      <p className="mt-4">
        Discover our vibrant bird cage, designed to provide a comfortable and stylish home for your feathered friend. Made with durable materials and featuring a spacious interior, this cage ensures a safe and happy environment for your pet bird. The colorful design adds a touch of fun to any room, making it a perfect addition to your home decor.
      </p>
      <div className="mt-4">
        <button className="bg-blue-500 text-white px-4 py-2 rounded-md mr-2">Pet Accessories</button>
        <button className="bg-blue-500 text-white px-4 py-2 rounded-md mr-2">Pet Toys</button>
        <button className="bg-blue-500 text-white px-4 py-2 rounded-md">Shop</button>
      </div>
    </div>
  </div>
</main>

            <div className="flex flex-col min-h-screen">
                <footer className="bg-custom-gray text-white p-4 mt-auto">
                    <p>&copy; 2024 My Awesome Website</p>
                </footer>
            </div>
        </>

    );
}
export default Product;