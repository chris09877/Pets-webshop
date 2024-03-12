import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import axios from 'axios';


function App() {
  const [count, setCount] = useState(0)
  async function product(){
    try {
      // Fetch data from the specified URL
      let response = await axios.get("http://localhost:8080/product");

      // Check if the response is successful
      if (response.ok) {
          // Parse the response JSON
          let data = await response.json();
          
          console.log(data);
          return data;
      } else {
          // If response is not successful, throw an error
          throw new Error('Failed to fetch data');
      }
  } catch (error) {
      // Handle any errors that occur during the fetch operation
      console.error('Error fetching data:', error.message);
      return null; // Return null if an error occurs
  }
  
  }

  return (
    <>
      <div>
        <a href="https://vitejs.dev" target="_blank">
          <img src={viteLogo} className="logo" alt="Vite logo" />
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>
      <h1>Vite + React</h1>
      <div className="card">
        <button onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </button>
        <p>
          Edit <code>src/App.jsx</code> and save to test HMR
        </p>
      </div>
      <p className="read-the-docs">
       {/* {product()} */}
      </p>
    </>
  )
}

export default App
