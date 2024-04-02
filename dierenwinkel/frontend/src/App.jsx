import { useState } from 'react'

import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from './pages/Home';
import Catalog from './pages/Catalog';
import Login from './pages/Login';
import Register from './pages/Register';
import Product from './pages/Product';
import OrderConfirmation from './pages/OrderConfirmation';


function App() {
  

  return (
    <div>
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Home/>}></Route>

        <Route path='/login' element={<Login/>}></Route>

        <Route path='/register' element={<Register/>}></Route>

        <Route path='/catalog' element={<Catalog/>}>  </Route>

        <Route path='/product' element={<Product/>}></Route>

       
        <Route path='/checkout' element={<OrderConfirmation/>}>o

        </Route>
      </Routes>
      </BrowserRouter>
        
    </div>
  )
}

export default App