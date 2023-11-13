import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import "../css/createMenuItem.css";
import noImagePic from "../../images/scenery.png";
import { request } from "../../fetch/fetch";
import { getUserLogin } from "../../storage/localStorage";
import { getFoodCategory, getProductId } from "../../logic/urlLogic";

export default function CreateMenuItem() {
    const [menuItem, setMenuItem] = useState({});
    const location = useLocation();
    let foodCategory = getFoodCategory(location.pathname).slice(0, -1);


    const handleSubmit = (e) => {
        e.preventDefault();

        request("POST", `/api/food/${foodCategory}/${getUserLogin()}`, {})
            .then((res) => console.log(res))
            .catch(error => console.error(error));
    };

    return (<div className="container">
        <form action="/action_page.php">
            <div className="row">
                <div className="col-25">
                    <label for="fname">Name</label>
                </div>
                <div className="col-75">
                    <input type="text" id="fname" name="name" placeholder="name..." />
                </div>
            </div>
            <div className="row">
                <div className="col-25">
                    <label for="price">Price</label>
                </div>
                <div className="col-75">
                    <input type="text" id="lname" name="price" placeholder="price..." />
                </div>
            </div>
            <div className="row">
                <div className="col-25">
                    <label for="allergies">Allergies</label>
                </div>
                <div className="col-75">
                    <select id="country" name="allergies">
                    </select>
                </div>
            </div>
            <div className="row">
                <div className="col-25">
                    <label for="img">Select image:</label>
                </div>
                <div className="col-75">
                    <input className="img" type="file" id="img" name="img" accept="image/*" />
                </div>
            </div>
            <div className="row">
                <div className="col-25">
                    <label for="desc">Description</label>
                </div>
                <div className="col-75">
                    <textarea id="subject" name="desc" placeholder="Ingredientes, backstory, etc.." style={{ height: "200px" }}></textarea>
                </div>
            </div>
            <div className="row">
                <input type="submit" value="Submit" />
            </div>
        </form>
    </div>
    )
}
