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


  const [isHowItWorksExpanded, setIsHowItWorksExpanded] = useState(false);
  const [isJoinCommunityExpanded, setIsJoinCommunityExpanded] = useState(false);

  // Function to toggle the visibility of How It Works section
  const toggleHowItWorks = () => {
    setIsHowItWorksExpanded(!isHowItWorksExpanded);
  };

  // Function to toggle the visibility of Join Community section
  const toggleJoinCommunity = () => {
    setIsJoinCommunityExpanded(!isJoinCommunityExpanded);
  };

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

      <main className="p-4">
        <div className=" flex justify-center text-white p-5">
          <div className="flex items-center">
            <img src="path-to-your-image" alt="Icon" className="h-12 w-12 mr-4" />
            <h1 className="text-xl font-bold">Discover how to take care of your dear pet friend</h1>
          </div>
          <button className="bg-gray-800 text-white px-4 py-2 mt-4 rounded hover:bg-gray-700">search pets options</button>
        </div>

        <div className=" text-white p-8">
          <div className=" text-white p-8">
            <h2 className="text-3xl mb-4 cursor-pointer border-t border-b border-white py-4 px-4" onClick={toggleHowItWorks}>
              How PetPals Works
            </h2>
            {isHowItWorksExpanded && (
              <div className="grid grid-cols-1 md:grid-cols-3 gap-8 mb-12">
                {/* Step 1 */}
                {/* ... */}
                {/* Step 2 */}
                {/* ... */}
                {/* Step 3 */}
                {/* ... */}
              </div>
            )}

            <h2 className="text-3xl mb-4 cursor-pointer border-t border-b border-white py-4 px-4" onClick={toggleJoinCommunity}>
              Join the PetPals Community
            </h2>
            {isJoinCommunityExpanded && (
              <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
                {/* As a Pet Sitter */}
                {/* ... */}
                {/* As a Supplier */}
                {/* ... */}
                {/* As a Team Member */}
                {/* ... */}
              </div>
            )}
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

};

export default Home;