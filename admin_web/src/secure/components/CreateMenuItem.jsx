import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import "../css/createMenuItem.css";
import noImagePic from "../../images/scenery.png";
import { request, getRequest } from "../../fetch/fetch";
import { getUserLogin } from "../../storage/localStorage";
import { getFoodCategory, getProductId } from "../../logic/urlLogic";
import Allergies from "./Allergies";
import { all } from "axios";

export default function CreateMenuItem() {
  const location = useLocation();
  const [menuItem, setMenuItem] = useState({});
  const [name, setName] = useState(null);
  const [price, setPrice] = useState(null);
  const [allergies, setAllergies] = useState([]);
  const [description, setDescription] = useState(null);
  const [imgUrl, setImgUrl] = useState(null);

  console.log(allergies);

  const handleSubmit = (e) => {
    e.preventDefault();
    const food = {
      name,
      price,
      allergies,
      description,
      imgUrl,
    };

    console.log(food);

    let foodCategory = getFoodCategory(location.pathname);

    request("POST", `/api/food/${foodCategory}/${getUserLogin()}`, food)
      .then((res) => console.log(res))
      .catch((error) => console.error(error));
  };

  const handleCheck = (e) => {
    e.preventDefault();
    if (!allergies.map((e) => e.publicId).includes(e.target.id)) {
      setAllergies([...allergies, { publicId: e.target.id }]);
    } else {
      setAllergies([...allergies.filter((a) => a.publicId !== e.target.id)]);
    }
  };

  return (
    <div className="cont">
      <form>
        <div className="row">
          <div className="col-25">
            <label htmlFor="fname">Name</label>
          </div>
          <div className="col-75">
            <input
              onChange={(e) => setName(e.target.value)}
              type="text"
              id="fname"
              name="name"
              placeholder="name..."
            />
          </div>
        </div>
        <div className="row">
          <div className="col-25">
            <label htmlFor="price">Price</label>
          </div>
          <div className="col-75">
            <input
              onChange={(e) => setPrice(e.target.value)}
              type="text"
              id="lname"
              name="price"
              placeholder="price..."
            />
          </div>
        </div>
        <div className="row">
          <div className="col-25">
            <label htmlFor="allergies">Allergies</label>
          </div>
          <div className="col-75">
            <Allergies
              handleCheck={handleCheck}
              allergiesData={allergies.map((allergy) => allergy.publicId)}
            />
          </div>
        </div>
        <div className="row">
          <div className="col-25">
            <label htmlFor="img">Select image:</label>
          </div>
          <div className="col-75">
            <input
              className="img"
              type="file"
              id="img"
              name="img"
              accept="image/*"
            />
          </div>
        </div>
        <div className="row">
          <div className="col-25">
            <label htmlFor="desc">Description</label>
          </div>
          <div className="col-75">
            <textarea
              onChange={(e) => setDescription(e.target.value)}
              id="subject"
              name="desc"
              placeholder="Ingredientes, backstory, etc.."
              style={{ height: "200px" }}
            ></textarea>
          </div>
        </div>
        <div className="row">
          <input
            onClick={(e) => handleSubmit(e)}
            type="submit"
            value="Submit"
          />
        </div>
      </form>
    </div>
  );
}
