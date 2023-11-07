import { Outlet } from "react-router-dom";
import FoodCategories from "../components/FoodCategories";
import MenuMapper from "../components/MenuMapper";

export default function Menu() {
  return (
    <>
      {<FoodCategories />}
      <MenuMapper/>
      <Outlet/>
    </>
  );
}
