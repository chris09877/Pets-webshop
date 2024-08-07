import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Filter = (props) => {
  const [category, setCategory] = useState('all');
  const [products, setProducts] = useState([]);
  const [isLoading, setIsLoading] = useState(false);

  const categories = ['all', 'fish', 'dog', 'cat'];

  const handleChange = (event) => {
    const newCategory = event.target.value;
    setCategory(newCategory);
    fetchProducts(newCategory); 

  };

  const fetchProducts = async (currentCategory) => {
    setIsLoading(true); // Set loading state to true

    try {
        console.log(currentCategory);
        let response;
        if(currentCategory !== "all")
        {
       response = await axios.get(`http://localhost:8080/api/product/filter/${currentCategory}`);
        }
        else
        {
            response = await axios.get('http://localhost:8080/api/product/all');
        }
      setProducts(response.data);
      props.onProductChange(response.data); // Call prop function with updated products
    } catch (error) {
      console.error(error); // Handle errors appropriately
    } finally {
      setIsLoading(false); // Set loading state to false after request completes
    }

  };

 

  return (
    <div>
      <select value={category} onChange={handleChange}>
        {categories.map((option) => (
          <option key={option} value={option}>
            {option}
          </option>
        ))}
      </select>
    </div>
  );
};

export default Filter;
