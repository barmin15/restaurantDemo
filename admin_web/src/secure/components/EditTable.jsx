import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "../css/editMenuItem.css";
import { request, getRequest } from "../../fetch/fetch";
import {  getProductId, getSourcePath } from "../../logic/urlLogic";
import Loading from "../../unsecure/pages/Loading.jsx"
import { Buffer } from "buffer";

export default function EditTable() {
    const [table, setTable] = useState(null);
    const [loading, setLoading] = useState(true);
    const [url, setUrl] = useState(null);

    const navigate = useNavigate();
    const location = useLocation();

    let tableId = getProductId(location.pathname);

    const handleChange = (e) => {
        e.preventDefault();
        setTable({ ...table, [e.target.name]: e.target.value });
    };

    useEffect(() => {
        getRequest(`/api/table/${tableId}`)
            .then(res => setTable(res.data))
            .catch(err => navigate("/"));

    }, []);

    useEffect(() => {
        if (table) {
            const buffer = Buffer.from(table.qrCodeBlob, "base64");
            const blob = new Blob([buffer], { type: "image/jpeg" })

            setUrl(blob);
        }
    }, [table])

    useEffect(() => {
        table !== null && setLoading(false);
    }, [table])

    const handleSubmit = (e) => {
        e.preventDefault();

        request("PUT", `/api/table/update/${table.publicId}`, table)
            .then((res) => navigate(getSourcePath(location.pathname)))
            .catch(error => navigate("/"));

    };


    return (loading ? <Loading /> :
        <div className="cont">
            <form onChange={handleChange}>
                <div className="row">
                    <div className="col-25">
                        <label htmlFor="fname">Name</label>
                    </div>
                    <div className="col-75">
                        <input
                            type="text"
                            id="fname"
                            name="tableName"
                            value={table.tableName}
                        />
                    </div>
                </div>
                <div className="row">
                    <div className="col-25">
                        <label htmlFor="img">Select image:</label>
                    </div>
                    <div className="col-75">
                        <input
                        style={{pointerEvents: "none"}}
                            className="img"
                            type="file"
                            id="img"
                            name="img"
                            accept="image/*"
                        />
                    </div>
                    <img src={url && URL.createObjectURL(url)} alt="" className="inputedImage" style={{height: "82px"}}/>
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