import Navbar from "../components/NavBar";
import axios from 'axios';
import { Link } from "react-router-dom";


const Product =  () =>  {
    const [pizzas, setPizzas] = useState([]);
    const [showBtn, setShowBtn] = useState(true);
    const [orderDetails, setOrderDetails] = useState([]);

    const handleOrderClick = async () => {
        // console.log("inside handle on click");
        // console.log(localStorage.getItem('userId'));
        // setShowBtn(!showBtn); // Toggles the showBtn state when the button is clicked
        // if (localStorage.getItem('userId') === null) {
        //     try {
        //         console.log(`${config.apiUrl}}/orders/create`);
        //         const response = await axios.post(`${config.apiUrl}/orders/create`);
        //         const data = response.data;
        //         setUserId(data.user);
        //         console.log(`user id: ${userId}`);
        //         console.log(`data id: ${data._id}`);
        //         console.log(`user id from data: ${data.user}`);
        //         localStorage.setItem('userId', data.user);
        //         console.log(`second ${localStorage.getItem('userId')}`);

        //     } catch (error) {
        //         console.log(error);
        //     }
        // }
    };


    const handleSubmit = async (e) => {
        e.preventDefault();

        // // Collect form data
        // const formData = new FormData(e.target);
        // const data = {};
        // formData.forEach((value, key) => {
        //     data[key] = value;
        // });
        // data['user'] = localStorage.getItem('userId');
        // console.log(`data in handlesubmit: ${data}`);
        // try {
        //     console.log(`${config.apiUrl}/orders/update/${localStorage.getItem('userId')}`);
        //     const response = await axios.patch(`${config.apiUrl}/orders/update/${localStorage.getItem('userId')}`, data);

        //     //console.log(response.data); // Handle the response here (success message or other actions)

        //     //   setOrderDetails(await response.data);
        //     //   console.log(` data send to component: ${orderDetails}`);


        // } catch (error) {
        //     console.error('Error:', error); // Handle errors here
        // }
    };

    useEffect(() => {
        const fetchProducts = async () => {
            try {
                console.log(`${config.apiUrl}/products`);
                const response = await axios.get(`${config.apiUrl}/pizzas`);
                const data = response.data;

                if (Array.isArray(data)) {
                    setProducts(data);
                } else {
                    console.log('Error: products is not an array');
                    console.log(data);
                }
            } catch (error) {
                console.log(error);
            }
        };

        fetchProducts();
    }, []);




    return (


        <div>
            <div className="flex justify-center items-center">
                <Navbar />
            </div>
            <div className="flex justify-start">
                <button onClick={handleOrderClick}>START ORDERING</button>
            </div>
            <div>
                <OrderDetails

                    title="Your Order"

                />

            </div>
            <h1>MENU</h1>
            {Array.isArray(products) &&

                <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
                    {products.map((product) => (
                        <form key={product._id} onSubmit={(e) => handleSubmit(e)}>
                            <div className="bg-white p-4 shadow-md">
                                <Link to={`/products/${product._id}`} className="block">
                                    <h2 className="text-lg font-semibold mb-2">{product.name}</h2>
                                    <img src={`/${product.name}.jpg`} alt={product.name} className="w-full h-40 object-cover mb-2" />
                                    <p className="text-gray-600 mb-2">{product.price}</p>
                                    <p className="text-sm text-gray-500 mb-2">{product.description}</p>

                                </Link>
                                <label className="block mb-2">
                                    Quantity:
                                    <input
                                        type="number"
                                        name="quantity"
                                        defaultValue={1}
                                        min={1}
                                        className="block w-full border border-gray-300 rounded px-2 py-1"
                                    />
                                </label>
                                <button hidden={showBtn} type="submit" className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
                                    Add
                                </button>
                                <input type="hidden" name="name" value={product.name} />
                                <input type="hidden" name="price" value={product.price} />
                                <input type="hidden" name="user" value={userId} />
                                <input type="hidden" name="product_id" value={product._id} />
                            </div>
                        </form>
                    ))}
                </div>

            }
        </div>
    );
}
export default Product;