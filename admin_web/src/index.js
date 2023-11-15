import React from "react";
import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import reportWebVitals from "./test/reportWebVitals";

//pages
import Enterance from "./unsecure/pages/Enterance";
import Tables from "./secure/pages/Tables";
import Navbar from "./secure/components/Navbar";
import EditMenuItem from "./secure/components/EditMenuItem";
import TablesSetup from "./secure/pages/TablesSetub";
import Starterts from "./secure/pages/Starters";
import Soups from "./secure/pages/Soups";
import Starters from "./secure/pages/Starters";
import Desserts from "./secure/pages/Desserts";
import AlcoholicDrinks from "./secure/pages/AlcoholicDrinks";
import NonAlcoholicDrinks from "./secure/pages/NonAlcoholicDrinks";
import MainCourses from "./secure/pages/MainCourse";
import CreateMenuItem from "./secure/components/CreateMenuItem";
import EditTable from "./secure/components/EditTable";

const Router = createBrowserRouter([
  {
    path: "/",
    element: <Enterance />
  },
  {
    path: "/tableSetup",
    element: <TablesSetup />
  },
  {
    path: "/app",
    element: <Navbar />,
    children: [
      {
        path: "/app/menu/starters",
        element: <Starterts />
      },
      {
        path:"/app/menu/soups",
        element: <Soups/>
      },
      {
        path:"/app/menu/soups",
        element: <Soups/>
      },
      {
        path:"/app/menu/starters",
        element: <Starters/>
      },
      {
        path:"/app/menu/main-courses",
        element: <MainCourses/>
      },
      {
        path:"/app/menu/desserts",
        element: <Desserts/>
      },
      {
        path: "/app/menu/alcoholic-drinks",
        element: <AlcoholicDrinks />,
      },
      {
        path: "/app/menu/non-alcoholic-drinks",
        element: <NonAlcoholicDrinks />,
      },
      {
        path: "/app/menu/alcoholic-drinks/create",
        element: <CreateMenuItem />,
      },
      {
        path: "/app/menu/alcoholic-drinks/:drinkId",
        element: <EditMenuItem />,
      },
      {
        path: "/app/menu/non-alcoholic-drinks/create",
        element: <CreateMenuItem />,
      },
      {
        path: "/app/menu/non-alcoholic-drinks/:drinkId",
        element: <EditMenuItem />,
      },
      {
        path: "/app/menu/starters/create",
        element: <CreateMenuItem />,
      },
      {
        path: "/app/menu/starters/:foodId",
        element: <EditMenuItem />,
      },
      {
        path: "/app/menu/soups/create",
        element: <CreateMenuItem />,
      },
      {
        path: "/app/menu/soups/:foodId",
        element: <EditMenuItem />,
      },
      {
        path: "/app/menu/main-courses/create",
        element: <CreateMenuItem />,
      },
      {
        path: "/app/menu/main-courses/:foodId",
        element: <EditMenuItem />,
      },
      {
        path: "/app/menu/desserts/create",
        element: <CreateMenuItem />,
      },
      {
        path: "/app/menu/desserts/:foodId",
        element: <EditMenuItem />,
      },
      {
        path: "/app/tables",
        element: <Tables />
      },
      {
        path: "/app/tables/:foodId",
        element: <EditTable />,
      },
    ]
  }
]);


const root = ReactDOM.createRoot(document.getElementById("root"));

root.render(
  <React.StrictMode>
    <RouterProvider router={Router} />
  </React.StrictMode>
);

reportWebVitals();