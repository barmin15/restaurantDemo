import "../css/navbar.css";
import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import { getRequest } from "../../fetch/fetch";
import { getUserLogin } from "../../storage/localStorage";
import { Outlet, NavLink } from "react-router-dom";

export default function Navbar() {
  const navigate = useNavigate();
  const [name, setName] = useState(null);
  const [imageUrl, setImageUrl] = useState(
    "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/1200px-No_image_available.svg.png"
  );

  useEffect(() => {
    getRequest(`/api/restaurant/${getUserLogin()}`)
      .then((response) => {
        const restaurant = response.data;
        setName(restaurant.name);
        restaurant.imageUrl !== null && setImageUrl(restaurant.imageUrl);
      })
      .catch((error) => navigate("/"))
  })

  return (<>
    <nav class="navbar">
      <NavLink className="restaurant">{name}</NavLink>
      <img className="logo" src={imageUrl} alt="logo" />
      <NavLink className="logout" to={"/"}>
        Log out
      </NavLink>
      <NavLink className="tables" to={"/app/tables"}>
        Tables
      </NavLink>
      <div class="dropdown">
        <button class="dropbtn">Menu
          <i class="fa fa-caret-down"></i>
        </button>
        <div class="dropdown-content">
          <NavLink to={"/app/menu/alcoholic-drinks"}>Alcoholic drinks</NavLink>
          <NavLink to={"/app/menu/non-alcoholic-drinks"}>Non alcoholic drinks</NavLink>
          <NavLink to={"/app/menu/starters"}>Starters</NavLink>
          <NavLink to={"/app/menu/soups"}>Soups</NavLink>
          <NavLink to={"/app/menu/main-courses"}>Main courses</NavLink>
          <NavLink to={"/app/menu/desserts"}>Desserts</NavLink>
        </div>
      </div>
    </nav>
    <Outlet />
  </>)
}
