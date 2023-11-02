import { useState, useRef, useEffect } from "react";

const Table = ({ tableName, index, handleTableNamesChange }) => {

    const [tableNameState, setTableNameState] = useState(tableName);
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
                            type="text" value={tableNameState} 
                            onChange={e => {
                                setTableNameState(e.target.value);
                                // handleTableNamesChange(tableNameState, index);
                            }} 
                            className="tableNumber"/>) : (
                            <input type="text"
                            className="tableNumber"
                            value={tableNameState} 
                            onChange={e => {
                                setTableNameState(e.target.value);
                                // handleTableNamesChange(tableNameState, index);
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
                width="7%"/>
        </div> );
}

export default Table;
        
