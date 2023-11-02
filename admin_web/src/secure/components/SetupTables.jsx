import { useState } from "react";
import DrawTables from "./DrawTables";

const SetupTables = () => {

    const [numberOfTables, setNumberOfTables] = useState(0);

    const handleSubmit = (e) => {
        e.preventDefault();
        const input = e.target.querySelector('#numberOfTables');
        setNumberOfTables(input.value);
    }

    return ( 
   numberOfTables === 0 ? (<>
        <form onSubmit={handleSubmit}>
            <label htmlFor="numberOfTables">How many tables do you have?</label>
            <input type="number" id="numberOfTables" name="numberOfTables"/>
            <button type="submit">Generate tables</button>
        </form>
    </>) : <DrawTables numberOfTables={numberOfTables}/> );
}
 
export default SetupTables;