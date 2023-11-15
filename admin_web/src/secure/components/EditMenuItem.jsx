import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "../css/editMenuItem.css";
import { request, getRequest } from "../../fetch/fetch";
import { getUserLogin } from "../../storage/localStorage";
import { getFoodCategory, getProductId, getSourcePath } from "../../logic/urlLogic";
import Allergies from "./Allergies.jsx";
import Loading from "../../unsecure/pages/Loading.jsx"
import { Buffer } from "buffer";

export default function EditMenuItem() {

  const [menuItem, setMenuItem] = useState(null);
  const [loading, setLoading] = useState(true);
  const [url, setUrl] = useState(null);

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
    if (menuItem) {
     
      const buffer = Buffer.from(menuItem.pictureBlob, "base64");

      const blob = new Blob([buffer], { type: "image/jpeg" })

      console.log(blob)
      setUrl(blob);
    }
  }, [menuItem])

  useEffect(() => {
    menuItem !== null && setLoading(false);
  }, [menuItem])

  const handleSubmit = (e) => {
    e.preventDefault();

    request("PUT", `/api/food/update/${menuItem.publicId}`, menuItem)
      .then((res) => navigate(getSourcePath(location.pathname)))
      .catch(error => console.error(error));

  };

  const handleCheck = (e) => {
    e.preventDefault();

    if (!menuItem.allergies.map((element) => element.publicId).includes(e.target.id)) {
      setMenuItem({ ...menuItem, allergies: [...menuItem.allergies, { publicId: e.target.id }] });
    } else {
      setMenuItem({ ...menuItem, allergies: menuItem.allergies.filter(a => a.publicId !== e.target.id) })
    }
  }

  return (loading ? <Loading /> :
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
          <div className="col-75">
            <Allergies
              handleCheck={handleCheck}
              allergiesData={menuItem.allergies.map((allergy) => allergy.publicId)}
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
          <img src={url && URL.createObjectURL(url)} alt="" className="inputedImage" />
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