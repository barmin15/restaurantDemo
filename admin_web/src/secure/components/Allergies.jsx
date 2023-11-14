import { useState, useEffect } from "react";
import { getRequest } from "../../fetch/fetch";
import "../css/allergies.css";

export default function Allergies({ handleCheck, allergiesData }) {
  const [allergies, setAllergies] = useState([]);

  useEffect(() => {
    getRequest("/api/allergy/all")
      .then((req) => setAllergies(req.data))
      .catch((err) => console.error(err));
  }, []);

  return (
    <ul className="allergies">
      {allergies.map((allergy, index) => (
        <li className="allergy" key={allergy.publicId}>
          <input
            readOnly
            type="checkbox"
            id={`myCheckbox${index}`}
            name={allergy.name}
            key={allergy.publicId}
            checked={allergiesData.includes(allergy.publicId)}
          />
          <label className="allergy-label" htmlFor={`myCheckbox${index}`}>
            <img
              className="allergy-logo"
              src={require(`/src/images/allergies/${allergy.name}.png`)}
              alt=""
              onClick={handleCheck}
              id={allergy.publicId}
            />
          </label>
        </li>
      ))}
    </ul>
  );
}
