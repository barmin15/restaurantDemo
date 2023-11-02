import './App.css';
import DrawTables from './secure/components/DrawTables';
import SetupTables from './secure/components/SetupTables';
import { useEffect, useState } from 'react';

function App() {

  const [tableNames, setTableNames] = useState(null);
  // const [hasNames, setHasNames] = useState(false);


  // const [newTableNames, setNewTableNames] = useState([...tableNames]);

  // const handleTableNamesChange = (newName, index) => {
  //   setNewTableNames([...newTableNames].map((name, i) => {
  //     return i !== index ? name : newName;
  //   }))
  // }

  console.log(tableNames);

  useEffect(() => {
    //fetch table names;
    // tableNames && setHasNames(true);
    
  })

  return (
    <div className="App">
        {tableNames !== null ? (<DrawTables tableNames={tableNames} 
                                            /*handleTableNamesChange={handleTableNamesChange}*//>) : 
                               (<SetupTables /*handleTableNamesChange={handleTableNamesChange}*//>)}    
    </div>
  );
}

export default App;
