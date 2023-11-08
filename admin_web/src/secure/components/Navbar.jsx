import "../css/navbar.css";
import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import { getRequest } from "../../fetch/fetch";
import { getUserLogin } from "../../storage/localStorage";
import { Outlet } from "react-router-dom";

export default function Navbar() {
  const navigate = useNavigate();
  const [name, setName] = useState(null);
  const [imageUrl, setImageUrl] = useState(
    "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/1200px-No_image_available.svg.png"
  );

  useEffect(() => {
    getRequest(`api/restaurant/${getUserLogin()}`)
      .then((response) => {
        const restaurant = response.data;
        setName(restaurant.name);
        restaurant.imageUrl !== null && setImageUrl(restaurant.imageUrl);
      })
      .catch((error) => console.log(error))
  })

  return (<>
    <div class="navbar">
      <a className="restaurant">{name}</a>
      <img className="logo" src={imageUrl} alt="logo" />
      <a className="logout" onClick={() => navigate("/")}>
        Log out
      </a>
      <a className="tables" onClick={() => navigate("/app/tables")}>
      Tables
    </a>
      <div class="dropdown">
        <button class="dropbtn">Menu
          <i class="fa fa-caret-down"></i>
        </button>
        <div class="dropdown-content">
          <a>Alcoholic drinks</a>
          <a>Non alcoholic drinks</a>
          <a>Starters</a>
          <a>Soups</a>
          <a>Main courses</a>
          <a>Desserts</a>
        </div>
      </div>
    </div>
    <Outlet />
  </>)
}
