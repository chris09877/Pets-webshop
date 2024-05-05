import FormLogin from "../components/FormLogin";
import NavBar from "../components/NavBar";

const Login = () => {



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
        <FormLogin />

      </main>
      <div className="flex flex-col min-h-screen">
        <footer className="bg-custom-gray text-white p-4 mt-auto">
          <p>&copy; 2024 My Awesome Website</p>
        </footer>
      </div>
    </>
  );
}




export default Login;