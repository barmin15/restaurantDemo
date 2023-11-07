import { useState } from "react";
import Table from "./Table";

const DrawTables = ({ numberOfTables, tables, handleTableDelete, handleChangeTableName }) => {

    

    console.log(tables);

    // const buildTablesWithStarterNames = (number) => {
    //     const tables = [];
    //     for (let i = 0; i < number; i++) {
    //         tables.push(<Table tableName={`${i+1}.Table`} key={i} index={i} handleTableNamesChange={handleTableNamesChange}/>);
    //     }
    //     return tables;
    // }
   
    
    const buildTablesWithNames = () => {


        return tables.map((table, index) => (<Table table={table} key={index} index={index} handleTableDelete={handleTableDelete} handleChangeTableName={handleChangeTableName}
            //index={index} handleTableNamesChange={handleTableNamesChange}
            />))
    } 

    return ( 
    <div className="tables">
        {/* {tableNames !== undefined ? buildTablesWithNames(tableNames) : buildTablesWithStarterNames(numberOfTables)} */}
        {buildTablesWithNames()}
    </div> );
}
 
export default DrawTables;