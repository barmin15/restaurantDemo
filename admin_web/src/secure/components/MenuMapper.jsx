import { useEffect, useState } from "react";
import "../css/menuMapper.css";
import { useNavigate, useLocation } from "react-router";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPen, faTrashCan } from "@fortawesome/free-solid-svg-icons";
import {getSourcePath} from "../../logic/urlLogic";
import {request} from "../../fetch/fetch";

export default function MenuMapper({ data }) {
  const navigate = useNavigate();
  const location = useLocation();

  console.log(data)

  const [head, setHead] = useState(null);
  const [body, setBody] = useState(null);

  const convertRatings = (number) => "★".repeat(number);

  useEffect(() => {
    const tableHeader = Object.keys(data[0]).map((key, i) => {
      if (key !== "publicId" && key !== "allergies")
        return <th key={i}>{key.toUpperCase()}</th>;
    });
    const tableBody = [];

    data.forEach(elem => elem.edit = <FontAwesomeIcon className="invisible" icon={faPen}/>)

    data.forEach(elem => elem.delete = <FontAwesomeIcon className="invisible" icon={faTrashCan} />)

    data.forEach((e, i) => {
      tableBody.push(
        <tr key={i}>
          {Object.keys(e).map((key) => {
            if (key === "rating") {
              return <td>{convertRatings(e[key])}</td>;
            } else if (key === "description") {
              if (e[key] && e[key].length > 10) {
                return <td className="val">{e[key].split(" ")[0] + "..."}</td>;
              } else {
                return <td className="val">{e[key]}</td>;
              }
            } else if (key === "edit") {
              return <td className="val" id={e.publicId} onClick={(event) =>{
                navigate(location.pathname + "/" + event.target.id)
              }
              } >{e[key]}</td>
            } else if (key === "delete") {
              return <td className="val" id={e.publicId} onClick={(event) => handleDeleteItem(event)}>{e[key]}</td>
            } else if (key !== "publicId" && key !== "allergies") {
              return <td className="val" id={e.publicId}>{e[key]}</td>;
            }
          })}
        </tr>
      );
    });

    setHead(tableHeader);
    setBody(tableBody);
  }, [data]);

  function handleDeleteItem(e){
    e.preventDefault();
    request("DELETE", `/api/food/delete/${e.target.id}`, {})
    .then((res) => console.log(res))
    .catch((error) => navigate("/"));
  }

  return (
    <div className="tableFixHead">
      <table className="table">
        <thead>
          <tr>
            {head}
            <th className="val">EDIT</th>
            <th className="val">REMOVE</th>
          </tr>
        </thead>
        <tbody>{body}</tbody>
      </table>
    </div>
  );
}
