import Form from "../components/Form";


const OrderConfirmation = () => {

    return (

        <div className="flex flex-col min-h-screen">
          {/* <Navbar /> */}
    
          <main className="flex-grow flex flex-col items-center justify-center px-4 py-8">
            <h1 className="text-3xl font-semibold mb-4">Validate Order</h1>
            <div className="max-w-md w-full">
              <Form />
            </div>
          </main>
          {/* <div>
            <BackButton />
          </div> */}
        </div>
      )
}

export default OrderConfirmation;