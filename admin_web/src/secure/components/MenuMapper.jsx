import { useEffect, useState } from "react";
import "../css/menuMapper.css"
import { useNavigate, useLocation } from "react-router";

export default function MenuMapper({ data }) {

    const navigate = useNavigate();
    const location = useLocation();

    const [head, setHead] = useState(null);
    const [body, setBody] = useState(null);

    const convertRatings = (number) => "★".repeat(number);

    useEffect(() => {
        const tableHeader = Object.keys(data[0]).map((key, i) => {
            return (key !== "publicId" && <th key={i}>{key.toUpperCase()}</th>)
        })
        const tableBody = [];

        data.forEach((e, i) => {
            tableBody.push(
                <tr key={i}>
                    {Object.keys(e).map(key => {
                        if (key === "rating") {
                            return <td>{convertRatings(e[key])}</td>
                        } else if (key !== "publicId") {
                            return <td>{e[key]}</td>
                        }
                    })}
                    <td id={e.publicId} onClick={(e) => navigate(`${location.pathname}/${e.target.id}`)}>edit</td>
                    <td id={e.publicId}>remove</td>
                </tr>
            )
        });

        setHead(tableHeader);
        setBody(tableBody);
    }, [data])

    return (
        <div className="tableFixHead">
            <table className="table">
                <thead>
                    <tr>
                        {head}
                        <th>EDIT</th>
                        <th>REMOVE</th>
                    </tr>
                </thead>
                <tbody>
                    {body}
                </tbody>
            </table>
        </div>
    )
}