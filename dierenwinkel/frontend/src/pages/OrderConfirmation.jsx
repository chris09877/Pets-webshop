import Form from "../components/Form";
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import OrderDetails from '../components/OrderDetails';
import NavBar from "../components/NavBar";
const OrderConfirmation = () => {

  return (

    <>
      <header className="bg-custom-gray text-white p-4 flex items-center justify-between">
        <h1 className="text-2xl font-bold">Pets Pals</h1>
        <div className="flex-grow flex justify-center">
          <img src="path/to/your/logo.png" alt="Logo" className="w-20 h-20" /> {/* Adjust the w-20 h-20 to fit your logo size */}
        </div>
        <div className="flex justify-end">
          <NavBar />
        </div>
      </header>

      <main className="flex-grow flex flex-col items-center justify-center px-4 py-8">
        <h1 className="text-3xl font-semibold mb-4">Validate Order</h1>
        <div className="max-w-md w-full">
          <Form />
        </div>
      </main>
      <div className="flex flex-col min-h-screen">
        <footer className="bg-custom-gray text-white p-4 mt-auto">
          <p>&copy; 2024 My Awesome Website</p>
        </footer>
      </div>
    </>
  )
}

export default OrderConfirmation;