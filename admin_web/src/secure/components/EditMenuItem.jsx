import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import "../css/editMenuItem.css";
import noImagePic from "../../images/scenery.png";

export default function EditMenuItem() {
  const [menuItem, setMenuItem] = useState({});

  const handleChange = (e) => {
    e.preventDefault();
    setMenuItem({ ...menuItem, [e.target.name]: e.target.value });
  };

  // image     "https://i0.wp.com/cdn-prod.medicalnewstoday.com/content/images/articles/278/278858/mushrooms-in-a-bowel-on-a-dark-table.jpg?w=1575"

  const location = useLocation();
  let itemId = getProductId(location.pathname);

  function getProductId(url) {
    let result = "";
    let i = url.lastIndexOf("/") + 1;

    for (i; i < url.length; i++) {
      result += url[i];
    }
    return result;
  }

  useEffect(() => {
    if (itemId !== "add") {
      setMenuItem({
        name: "Toltott Kaposzta",
        price: 5000,
        allergies: [
          { name: "mushroom", pubId: "335" },
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

  return (
    <>
      {Object.keys(menuItem).length !== 0 && (
        <form className="editMenuForm">
          <label htmlFor="name">Name:</label>
          <input
            name="name"
            id="name"
            value={menuItem.name}
            onChange={handleChange}
          />
          <label htmlFor="">Price:</label>
          <input
            name="price"
            id="price"
            value={menuItem.price}
            onChange={handleChange}
          />
          <label htmlFor="allergies">Choose allergies:</label>

          <select name="allergies" id="allergies">
          {menuItem.allergies.length !== 0 && menuItem.allergies.map((item) => (
            <option key={item.pubId} value={item.name}>
              {item.name}
            </option>
          ))}
        </select>

          <label htmlFor="">Description:</label>
          <textarea
            name="description"
            value={menuItem.description}
            onChange={handleChange}
          />
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
        </form>
      )}
    </>
  );
}
