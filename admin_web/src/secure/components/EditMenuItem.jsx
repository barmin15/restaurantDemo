import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import "../css/editMenuItem.css";
import noImagePic from "../../images/scenery.png";
import { request } from "../../fetch/fetch";
import { getUserLogin } from "../../storage/localStorage";
import { getFoodCategory, getProductId } from "../../logic/urlLogic";
import Allergies from "./Allergies.jsx";

export default function EditMenuItem() {
  const [menuItem, setMenuItem] = useState({});

  const location = useLocation();
  let itemId = getProductId(location.pathname);
  let foodCategory = getFoodCategory(location.pathname);

  const handleChange = (e) => {
    e.preventDefault();
    setMenuItem({ ...menuItem, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    let body = {};
    body.name = menuItem.name;
    body.description = menuItem.description;
    body.allergies = [];
    body.price = menuItem.price;
    body.pictureUrl = menuItem.pictureUrl;

    request("POST", `/api/food/${foodCategory}/${getUserLogin()}`, body)
      .then((res) => console.log(res))
      .catch(error => console.error(error));
    };

  // image     "https://i0.wp.com/cdn-prod.medicalnewstoday.com/content/images/articles/278/278858/mushrooms-in-a-bowel-on-a-dark-table.jpg?w=1575"

  useEffect(() => {
    if (itemId !== "create") {
      setMenuItem({
        name: "Toltott Kaposzta",
        price: 5000,
        allergies: [
          { name: "GlutenFree", pubId: "335" },
          { name: "tomato", pubId: "3ered5" },
          { name: "pancake", pubId: "fdfgs" },
          { name: "cheese", pubId: "dsfsts" },
          { name: "milk", pubId: "335ewf" },
        ],
        description:
          "m has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets con",
        imgUrl:
          "https://i0.wp.com/cdn-prod.medicalnewstoday.com/content/images/articles/278/278858/mushrooms-in-a-bowel-on-a-dark-table.jpg?w=1575",
        pubId: "4543gdfsddwatet",
      });
    } else {
      setMenuItem({
        name: "",
        price: 0,
        allergies: [],
        description: "",
        imgUrl: "",
        pubId: "",
      });
    }
  }, []);


  const handleCheck = (e) => {
    e.preventDefault();

    if (!menuItem.allergies.includes(e.target.id)) {
      setMenuItem({...menuItem, allergies: [...menuItem.allergies, e.target.id]});
    } else {
      setMenuItem({...menuItem, allergies: menuItem.allergies.filter(allergy => allergy !== e.target.id)})
    }
  }

  console.log(menuItem);

  return (<>
      {Object.keys(menuItem).length !== 0 && (

       
        <form
          className="editMenuForm"
          onChange={handleChange}
          onSubmit={handleSubmit}
        >
          <label htmlFor="name">Name:</label>
          <input name="name" id="name" value={menuItem.name} />
          <label htmlFor="">Price:</label>
          <input name="price" id="price" value={menuItem.price} />

          {/* <label htmlFor="allergies">Choose allergies:</label> */}
          {/* <select name="allergies" id="allergies">
            {menuItem.allergies.length !== 0 &&
              menuItem.allergies.map((item) => (
                <option key={item.pubId} value={item.name}>
                  {item.name}
                </option>
              ))}
          </select> */}

          <label htmlFor="">Description:</label>

          <textarea
            name="description"
            value={menuItem.description}
          />

          <Allergies handleCheck={handleCheck} allergiesData={menuItem.allergies}/>


          <br />
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
      )}
    </>

  );
}
