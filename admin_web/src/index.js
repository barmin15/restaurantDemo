import React from "react";
import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import reportWebVitals from "./test/reportWebVitals";

//pages
import Enterance from "./unsecure/pages/Enterance";
import Menu from "./secure/pages/Menu";
import Tables from "./secure/pages/Tables";
import Navbar from "./secure/components/Navbar";
import EditMenuItem from "./secure/components/EditMenuItem";
import TablesSetup from "./secure/pages/TablesSetub";


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
        path: "/app/menu/alcoholic-drinks",
        element: <Menu />,
      },
      {
        path: "/app/menu/alcoholic-drinks/add",
        element: <EditMenuItem />,
      },
      {
        path: "/app/menu/alcoholic-drinks/:drinkId",
        element: <EditMenuItem />,
      },
      {
        path: "/app/menu/non-alcoholic-drinks",
        element: <Menu />,
      },
      {
        path: "/app/menu/non-alcoholic-drinks/add",
        element: <EditMenuItem />,
      },
      {
        path: "/app/menu/non-alcoholic-drinks/:drinkId",
        element: <EditMenuItem />,
      },
      {
        path: "/app/menu/starters",
        element: <Menu />, 
      },
      {
        path: "/app/menu/starters/add",
        element: <EditMenuItem />,
      },
      {
        path: "/app/menu/starters/:foodId",
        element: <EditMenuItem />,
      },
      {
        path: "/app/menu/soups",
        element: <Menu />,
      },
      {
        path: "/app/menu/soups/add",
        element: <EditMenuItem />,
      },
      {
        path: "/app/menu/soups/:foodId",
        element: <EditMenuItem />,
      },
      {
        path: "/app/menu/main-courses",
        element: <Menu />,
      },
      {
        path: "/app/menu/main-courses/add",
        element: <EditMenuItem />,
      },
      {
        path: "/app/menu/main-courses/:foodId",
        element: <EditMenuItem />,
      },
      {
        path: "/app/menu/desserts",
        element: <Menu />,
      },
      {
        path: "/app/menu/desserts/add",
        element: <EditMenuItem />,
      },
      {
        path: "/app/menu/desserts/:foodId",
        element: <EditMenuItem />,
      },
      {
        path: "/app/tables",
        element: <Tables />
      }
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