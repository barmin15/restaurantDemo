import React from "react";
import { useState } from "react";
import "../css/tableSetup.css"
import MenuMapper from "../components/MenuMapper";
import { request } from "../../fetch/fetch";
import { getUserLogin } from "../../storage/localStorage";
import { useNavigate } from "react-router-dom";

export default function TablesSetup() {
  const navigate = useNavigate();
  const [amount, setAmount] = useState(null);
  const [tables, setTables] = useState();
  const [isInput, setIsInput] = useState(true);

  function onSubmit(e) {
    e.preventDefault();
    setTables(createTables(amount));
    setIsInput(false);
  }

  function createTables(num) {
    const createdTables = [];

    for (let i = 1; i <= num; i++) {
      createdTables.push({ tableName: "table " + i, qrCode: "not set" });
    }
    return createdTables;
  }

  function onSubmitTables(e) {
    e.preventDefault();
    request("POST", `/api/table/register/${getUserLogin()}`, tables)
      .then((response) => navigate("/app/tables"))
      .catch((error) => navigate("/"));
  }

  return (isInput ?
    <form className="input-group" onSubmit={(e) => onSubmit(e)}>
      <div className="tablesEnter">Enter the amount of tables your restaurant has</div>
      <input required="" type="number" name="numberOfTables" autoComplete="off" className="input" onChange={(e) => setAmount(e.target.value)} />
      <input type="submit" hidden />
    </form >
    :
    <div className="tables">
      <MenuMapper data={tables} />
      <button className="submitTables" onClick={(e) => onSubmitTables(e)}>submit</button>
    </div>)
}