import './App.css';
import DrawTables from './secure/components/DrawTables';
import SetupTables from './secure/components/SetupTables';
import { useEffect, useState } from 'react';

function App() {

  // const [tables, setTables] = useState(null);

  const [tables, setTables] = useState([
    {
      tableName: "A1",
      login: "pelda@restaurant.com",
      qrCode: "qrCode",
      guests: []
    },
    {
      tableName: "A2",
      login: "pelda@restaurant.com",
      qrCode: "qrCode",
      guests: []
    },
    {
      tableName: "A3",
      login: "pelda@restaurant.com",
      qrCode: "qrCode",
      guests: []
    },
    {
      tableName: "B1",
      login: "pelda@restaurant.com",
      qrCode: "qrCode",
      guests: []
    },
    {
      tableName: "B2",
      login: "pelda@restaurant.com",
      qrCode: "qrCode",
      guests: []
    },
    {
      tableName: "B3",
      login: "pelda@restaurant.com",
      qrCode: "qrCode",
      guests: []
    }
  ]);

  console.log(tables);

  const handleTableDelete = index => {
    setTables(tables => [...tables].filter((table, i) => index !== i))
  }

  const handleChangeTableName = (index, newName) => {
    //console.log(newName);
    

    setTables(tables => { 
      [...tables].forEach((table, i) => {
        if (index === i) {
          table.tableName = newName;
        } 
      })

      //return [...tables, tables[index].tableName = newName]
    })
  }

  console.log(tables);

  // const [hasNames, setHasNames] = useState(false);
  // const [newTableNames, setNewTableNames] = useState([...tableNames]);

  // const handleTableNamesChange = (newName, index) => {
  //   setNewTableNames([...newTableNames].map((name, i) => {
  //     return i !== index ? name : newName;
  //   }))
  // }

  //console.log(tables);

  useEffect(() => {
    //fetch tables;
    // tableNames && setHasNames(true);
    
  })

  return (
    <div className="App">
        {console.log(tables)}
        {tables !== null && <DrawTables tables={tables} handleTableDelete={handleTableDelete} handleChangeTableName={handleChangeTableName}/>}

    </div>
  )
}

export default App;

          //  /*handleTableNamesChange={handleTableNamesChange}*//>) : 
          //  (<SetupTables /*handleTableNamesChange={handleTableNamesChange}*//>)}   