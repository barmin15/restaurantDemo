import Loading from "../../unsecure/pages/Loading";
import { useState, useEffect } from "react";
import { getUserLogin } from "../../storage/localStorage";
import { getRequest } from "../../fetch/fetch";
import MenuMapper from "../components/MenuMapper";
import { useNavigate } from "react-router-dom";
import "../css/itemMapper.css";
import "../css/addItem.css";

export default function MainCourses() {
    const navigate = useNavigate();
    const [loading, setLoading] = useState(true);
    const [data, setData] = useState([]);

    useEffect(() => {
        getRequest(`/api/food/main-courses/${getUserLogin()}`)
            .then((response) => setData(response.data))
            .catch((error) => navigate("/"));

           if(data.length > 0) setLoading(false)

    }, [data.length, navigate]);

    useEffect(()=> {
        if(data.length > 0) setLoading(false);
    },[data])

    return loading ?
        <div>
            <Loading />
            <button type="button" class="button" onClick={(e) => navigate("/app/menu/main-courses/create")}>
            <span className="button__text">Add Item</span>
            <span className="button__icon"><svg xmlns="http://www.w3.org/2000/svg" width="24" viewBox="0 0 24 24" stroke-width="2" stroke-linejoin="round" stroke-linecap="round" stroke="currentColor" height="24" fill="none" class="svg"><line y2="19" y1="5" x2="12" x1="12"></line><line y2="12" y1="12" x2="19" x1="5"></line></svg></span>
        </button>
        </div>
        :
        <>
            <button type="button" className="butt" onClick={(e) => navigate("/app/menu/main-courses/create")}>
                <span className="butt__text">Add Item</span>
                <span className="butt__icon"><svg xmlns="http://www.w3.org/2000/svg" width="24" viewBox="0 0 24 24" stroke-width="2" stroke-linejoin="round" stroke-linecap="round" stroke="currentColor" height="24" fill="none" class="svg"><line y2="19" y1="5" x2="12" x1="12"></line><line y2="12" y1="12" x2="19" x1="5"></line></svg></span>
            </button>
            <MenuMapper data={data} />
        </>

}