import axios from "axios";
import { useState } from "react";
import NavBar from "../components/NavBar";
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


        // <div className="flex flex-col min-h-screen">
        //     <div className="flex justify-center">
        //         <NavBar />
        //     </div>
        //     <main>
        //         <h1>HELLO WORLD</h1>
        //     </main>

        // </div>

        <div className="bg-gray-100 min-h-screen">
      <header className="bg-blue-500 text-white p-4">
        {/* Your header content */}
        <h1 className="text-2xl font-bold">Welcome to My Website</h1>
        <div className="flex justify-center">
                 <NavBar />
             </div>
      </header>

      <main className="p-4">
            <h1>HELLO WORLD</h1>

      </main>

      <footer className="bg-gray-500 text-white p-4">
        <p>&copy; 2024 My Awesome Website</p>
      </footer>
    </div>

    )

}

export default Home;