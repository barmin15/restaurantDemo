import "../css/navbar.css"
import { useNavigate } from "react-router-dom";
import { useState, useEffect, Children } from "react";
import { Outlet } from "react-router-dom";
import { getRequest } from "../../fetch/fetch";
import { getUserLogin } from "../../storage/localStorage"

export default function Navbar() {
    const navigate = useNavigate();
    const [name, setName] = useState(null);
    const [imageUrl, setImageUrl] = useState("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/1200px-No_image_available.svg.png")

    useEffect(() => {
        getRequest(`api/restaurant/${getUserLogin()}`)
            .then((response) => {
                setName(response.data.name);
                setImageUrl(response.data.imageUrl);
            })
            .catch((error) => console.log(error))
    })


    return <div><nav className="topnav">
        <a className="restaurant">{name}</a>
        <img className="logo" src={imageUrl} alt="logo" />
        <a className="logout" onClick={() => navigate("/")}>Log out</a>
        <a className="menu" onClick={() => navigate("/app/menu")}>Menu</a>
        <a className="tables" onClick={() => navigate("/app/tables")}>Tables</a>
    </nav>
    <Outlet />
    </div>
}