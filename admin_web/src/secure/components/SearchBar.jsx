import { useState } from "react";
import "../css/searchBar.css";

export default function SearchBar({ manageSearchFn }) {

    const [searchInput, setSearchInput] = useState("");

    const handleChange = (e) => {
        e.preventDefault();

        if (e.target.value.length > 0) {
            setSearchInput(e.target.value);
        } else {
            setSearchInput(null);
        }
    }

    const handleSearch = (e) => {
        e.preventDefault();
        manageSearchFn(searchInput);
    }
    
  return (
    <>
      <input type="search" value={searchInput} onChange={handleChange}/>
      <button onClick={handleSearch}>search</button>
    </>
  );
}
