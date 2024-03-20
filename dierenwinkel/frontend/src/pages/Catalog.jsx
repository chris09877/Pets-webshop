import axios from "axios";
import { useEffect, useState } from "react";


const Catalog = () => {
    const [products, setProducts] = useState([]);
    useEffect(() => {
        async function getCsrfToken() {
            let cookieArray = document.cookie.split(';');
            let csrfToken = null;
            
            cookieArray.forEach(cookie => {
                let trimmedCookie = cookie.trim();
                if (trimmedCookie.startsWith("_xsrf=")) {
                    csrfToken = trimmedCookie.substring("_xsrf=".length);
                }
            });
            
            return csrfToken;
        }
    
        async function fetchProducts() {
            const token = await getCsrfToken();
            console.log(token); // Log the CSRF token
            
            try {
                const response = await axios.get('http://localhost:8080/api/product/all', {
                    // headers: {
                    //     'X-CSRF-Token': token
                    // },
                });
                return response.data; // Return the fetched products data
            } catch (error) {
                console.error('Error fetching products:', error);
                return []; // Return an empty array in case of error
            }
        }
    
        async function loadProducts() {
            const products = await fetchProducts();
            console.log(products); // Log the fetched products
            setProducts(products); // Update the products state
        }
    
        loadProducts();
    }, []); // Empty dependency array to run the effect only once
    
    
    // async function getCsrfToken(){
    //     let cookieArray = document.cookie.split(';');
    //     let csrfToken = null;
    
    //     cookieArray.forEach(cookie => {
    //         if (cookie.startsWith("_xsrf=")) {
    //             csrfToken = cookie.substring("_xsrf=".length, cookie.length);
    //         }
    //     });
    
    //     return csrfToken;
    // }
    

    // async function getProducts() {
        
    //     const token = await getCsrfToken();
    //     console.log(token);
    //     try {
    //         const response = await axios.get('http://localhost:8080/api/product/all',
    //         {
    //             headers: {
    //               'X-CSRF-Token': token
    //             },
    //           }
    //         );
    //         let data = response.data;
    //         console.log(data);
    //     }
    //     catch (error) {
    //         console.error('Error creating user:', error);
    //     }
    // };

    // getProducts();



    return (
        <>
        <h1>product</h1>
        <p>{products}</p>
        </>
    );

}





export default Catalog;