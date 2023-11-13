import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "../css/editMenuItem.css";
import noImagePic from "../../images/scenery.png";
import { request, getRequest } from "../../fetch/fetch";
import { getUserLogin } from "../../storage/localStorage";
import { getFoodCategory, getProductId } from "../../logic/urlLogic";
import Allergies from "./Allergies.jsx";
import Loading from "../../unsecure/pages/Loading.jsx"


export default function EditMenuItem() {

  const [menuItem, setMenuItem] = useState(null);
  const [loading, setLoading] = useState(true);

  const navigate = useNavigate();
  const location = useLocation();

  let itemId = getProductId(location.pathname);

  const handleChange = (e) => {
    e.preventDefault();
    setMenuItem({ ...menuItem, [e.target.name]: e.target.value });
  };

  useEffect(() => {
    getRequest(`/api/food/${itemId}`)
      .then(res => setMenuItem(res.data))
      .catch(err => navigate("/"));
  }, []);

  useEffect(() => {
    menuItem !== null && setLoading(false);
  },[menuItem])

  const handleSubmit = (e) => {
    e.preventDefault();


    console.log(menuItem);

    request("PUT", `/api/food/update/${menuItem.publicId}`, menuItem)
      .then((res) => console.log(res))
      .catch(error => console.error(error));
    };

  const handleCheck = (e) => {
    e.preventDefault();

    if (!menuItem.allergies.includes(e.target.id)) {
      setMenuItem({...menuItem, allergies: [...menuItem.allergies, e.target.id]});
    } else {
      setMenuItem({...menuItem, allergies: menuItem.allergies.filter(allergy => allergy !== e.target.id)})
    }
  }

  return ( loading ? <Loading /> :
    <div className="cont">
      <form onChange={handleChange}>
        <div className="row">
          <div className="col-25">
            <label htmlFor="fname">Name</label>
          </div>
          <div className="col-75">
            <input
              type="text"
              id="fname"
              name="name"
              value={menuItem.name}
            />
          </div>
        </div>
        <div className="row">
          <div className="col-25">
            <label htmlFor="price">Price</label>
          </div>
          <div className="col-75">
            <input
              type="text"
              id="lname"
              name="price"
              value={menuItem.price}
            />
          </div>
        </div>
        <div className="row">
          <div className="col-25">
            <label htmlFor="allergies">Allergies</label>
          </div>
          {/* <div className="col-75">
            <Allergies
              handleCheck={handleCheck}
              allergiesData={menuItem.allergies.length > 0 && menuItem.allergies.map((allergy) => allergy.publicId)}
            />
          </div> */}
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
              id="subject"
              name="description"
              style={{ height: "200px" }}
              value={menuItem.description}
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


{/* {Object.keys(menuItem).length !== 0 && (


        <form
          className="editMenuForm"
          onChange={handleChange}
          onSubmit={handleSubmit}
        >
          <label htmlFor="name">Name:</label>
          <input name="name" id="name" value={menuItem.name} />
          <label htmlFor="">Price:</label>
          <input name="price" id="price" value={menuItem.price} /> */}

          {/* <label htmlFor="allergies">Choose allergies:</label> */}
          {/* <select name="allergies" id="allergies">
            {menuItem.allergies.length !== 0 &&
              menuItem.allergies.map((item) => (
                <option key={item.pubId} value={item.name}>
                  {item.name}
                </option>
              ))}
          </select> */}

          {/* <label htmlFor="">Description:</label>

          <textarea
            name="description"
            value={menuItem.description}
          /> */}

          {/* //<Allergies handleCheck={handleCheck} allergiesData={menuItem.allergies}/> */}


          {/* <br />
          {menuItem.imgUrl !== "" && (
            <input
              type="image"
              src={menuItem.imgUrl}
              alt="Submit"
              width="48"
              height="48"
            ></input>
          )}
          {menuItem.imgUrl === "" && <input type="file"></input>}
          <button>Submit</button>
        </form>
      )} */}