import { useState, useRef, useEffect } from "react";

const Table = ({ table, index, handleChangeTableName, handleTableDelete}) => {

    //console.log(table.tableName);

    const [tableName, setTableName] = useState(table.tableName);

    const [isMouseOver, setIsMouseOver] = useState(false);

    const inputElement = useRef();

    useEffect(() => {
        isMouseOver && inputElement.current.focus();  
    }, [isMouseOver])

    const handleMouseOver = () => {
        setIsMouseOver(true);
    }
    
    const handleMouseOut = () => {
        setIsMouseOver(false);
    }
    
    return ( 
        <div className="table">
            {isMouseOver ? (<input ref={inputElement}
                            type="text" value={tableName} 
                            onChange={e => {
                                //table.tableName = e.target.value;
                                setTableName(e.target.value);
                                handleChangeTableName(index, tableName);
                                // setTableState(tableState => ({
                                    //     ...tableState,
                                    //     tableName: e.target.value,
                                    // }));
                            }} 
                            className="tableNumber"/>) : (
                            <input type="text"
                            className="tableNumber"
                            value={tableName} 
                            onChange={e => {
                                setTableName(e.target.value);
                                handleChangeTableName(index, tableName);
                                //table.tableName = e.target.value;
                                // setTableState(tableState => ({
                                //     ...tableState,
                                //     tableName: e.target.value,
                                // }));
                            }} 
                            disabled/>)}
            <img className="filter-green" 
                onMouseOver={handleMouseOver} 
                onMouseOut={handleMouseOut} 
                src="images/table-svgrepo-com.svg" 
                alt="table" 
                width="40%"/>
            <img className="filter-green delete-table-button" 
                src="images/delete-button-svgrepo-com.svg" 
                alt="table" 
                width="7%"
                onClick={() => 
                    handleTableDelete(index)
                }
                />
        </div> );
}

export default Table;
        
