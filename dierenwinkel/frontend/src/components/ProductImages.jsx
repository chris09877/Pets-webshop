import React, { useState } from 'react';

const ProductImages = () => {
  // State to manage the main image
  const [mainImage, setMainImage] = useState("/howPetPals1.png");

  // Function to handle click on smaller images
  const handleImageClick = (newImage) => {
    setMainImage(newImage);
  };

  return (
    <div>
      {/* Main image */}
      <img src={mainImage} alt="Main Product" className="max-w-full h-auto" />

      {/* Small images */}
      <div className="mt-4 flex space-x-4">
        <img
          src="/howPetPals1.png"
          alt="Cat"
          className="w-16 h-16 object-cover cursor-pointer"
          onClick={() => handleImageClick("/howPetPals1.png")}
        />
        <img
          src="/howPetPals2.png"
          alt="Dogs"
          className="w-16 h-16 object-cover cursor-pointer"
          onClick={() => handleImageClick("/howPetPals2.png")}
        />
        <img
          src="/howPetPals3.png"
          alt="Dog with sunglasses"
          className="w-16 h-16 object-cover cursor-pointer"
          onClick={() => handleImageClick("/howPetPals3.png")}
        />
      </div>
    </div>
  );
};

export default ProductImages;
