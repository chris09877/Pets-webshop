import axios from "axios";
import { useState } from "react";

const Home = () => {

// const [csrfToken, setCsrfToken] = useState([]);
// console.log(csrfToken);
const storedToken = localStorage.getItem('csrfToken');

// if (!storedToken) {
//     axios.get('http://localhost:8080/api/csrf-token')
//         .then(response => {
//             if (response.status !== 200) {
//                 throw new Error('Failed to fetch CSRF token');
//             }
//             const csrfToken = response.data.csrfToken;

//                 // Store the CSRF token in localStorage
//                 localStorage.setItem('csrfToken', csrfToken);

//                 // Set a timeout to remove the token after 5 hours
//                 setTimeout(() => {
//                     localStorage.removeItem('csrfToken');
//                 }, 5 * 60 * 60 * 1000); // 5 hours in milliseconds
//             })
//         .catch(error => {
//             console.error('Error fetching CSRF token:', error);
//         });
// } else {
//     console.log(`the token exist already: ${storedToken}`);
// }

    return (
        <h1>HELLO WORLD</h1>
    )

}

export default Home;