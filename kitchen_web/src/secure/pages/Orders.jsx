import { useState, useEffect } from "react"
import Order from "../components/Order"
import { getRequest } from "../../fetch/fetch";
import { useNavigate } from "react-router-dom";
import { Box } from "@mui/material";

export default function Orders() {
    const [orders, setOrders] = useState(null);
    const [isDetailed, setIsDetailed] = useState(false);
    const [detailed, setDetailed] = useState(null);
    const navigate = useNavigate()

    useEffect(() => {
        getRequest(`/api/test/order`)
            .then((res) => setOrders(res.data.reverse()))
            .catch((err) => navigate("/kitchen"));
    }, [navigate])

    function showBigcard(order) {
        setDetailed(order);
        setIsDetailed(true)
    }


    return (<>
        <Box sx={{ display: 'inline-flex', flexDirextion: "row" }}>
            {orders?.map((order, i) => (<Order showBigcard={showBigcard} order={order} index={orders.length - (i + 1)} />))}
        </Box>
        {isDetailed ? <div>hehe</div> : null}
    </>)
}