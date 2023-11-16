import { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "../css/createMenuItem.css";
import { request, fileImagePostRequest } from "../../fetch/fetch";
import { getUserLogin } from "../../storage/localStorage";
import { getFoodCategory, getSourcePath } from "../../logic/urlLogic";
import Allergies from "./Allergies";
import Resizer from "react-image-file-resizer";

export default function CreateMenuItem() {
  const location = useLocation();
  const [menuItem, setMenuItem] = useState({});
  const [name, setName] = useState(null);
  const [price, setPrice] = useState(null);
  const [allergies, setAllergies] = useState([]);
  const [description, setDescription] = useState(null);
  const [imgUrl, setImgUrl] = useState(null);
  const [selectedFile, setSelectedFile] = useState(null);
  const navigate = useNavigate();


  const handleSubmit = (e) => {
    e.preventDefault();
    const food = {
      name,
      price,
      allergies,
      description,
      imgUrl,
    };
    let foodCategory = getFoodCategory(location.pathname);

    request("POST", `/api/food/${foodCategory}/${getUserLogin()}`, food)
      .then((res) => {
        selectedFile !== null ?
          fileUpload(`/api/food/upload-blob/${res.data.publicId}`) :
          navigate(getSourcePath(location.pathname))
      })
      .catch((error) => navigate("/"));


  };

  const resizeFile = (file) => new Promise(resolve => {
    Resizer.imageFileResizer(file, 300, 300, 'JPEG', 100, 0,
      uri => {
        setSelectedFile(uri);
      }, 'file');
  });

  const handleCheck = (e) => {
    e.preventDefault();
    if (!allergies.map((element) => element.publicId).includes(e.target.id)) {
      setAllergies([...allergies, { publicId: e.target.id }]);
    } else {
      setAllergies([...allergies.filter((a) => a.publicId !== e.target.id)]);
    }
  };

  function fileUpload(endpoint) {
    const formData = new FormData();
    formData.append('file', selectedFile);
    fileImagePostRequest("POST", endpoint, formData)
      .then(res => {
        navigate(getSourcePath(location.pathname))
      })
      .catch((error) => navigate('/'));
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
              value={""}
              onChange={async (e) => {
                setImgUrl(URL.createObjectURL(e.target.files[0]))
                await resizeFile(e.target.files[0]);
              }}
              className="img"
              type="file"
              id="img"
              name="img"
              accept="image/*"
            />
          </div>
          <img src={imgUrl} alt="" className="inputedImage" />
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
