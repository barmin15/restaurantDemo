import Table from "./Table";

const DrawTables = ({ numberOfTables, tableNames, handleTableNamesChange }) => {

    const buildTablesWithStarterNames = (number) => {
        const tables = [];
        for (let i = 0; i < number; i++) {
            tables.push(<Table tableName={`${i+1}.Table`} key={i} index={i} handleTableNamesChange={handleTableNamesChange}/>);
        }
        return tables;
    }
    
    const buildTablesWithNames = (tableNames) => {
        return tableNames.map((name, index) => (<Table tableName={name} key={index} index={index} handleTableNamesChange={handleTableNamesChange}/>))
    } 

    return ( 
    <div className="tables">
        {tableNames !== undefined ? buildTablesWithNames(tableNames) : buildTablesWithStarterNames(numberOfTables)}
    </div> );
}
 
export default DrawTables;