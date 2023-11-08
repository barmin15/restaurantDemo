import React from "react";
import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import reportWebVitals from "./test/reportWebVitals";

//pages
import Enterance from "./unsecure/pages/Enterance";
import Menu from "./secure/pages/Menu";
import Tables from "./secure/pages/Tables";
import Navbar from "./secure/components/Navbar";
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
        path: "/app/menu",
        element: <Menu />,
        children: [
          {
            path: "/app/menu/alcoholic-drinks",
            element: "",
          },
          {
            path: "/app/menu/non-alcoholic-drinks",
            element: "",
          },
          {
            path: "/app/menu/starters",
            element: "",
          },
          {
            path: "/app/menu/soups",
            element: "",
          },
          {
            path: "/app/menu/main-courses",
            element: "",
          },
          {
            path: "/app/menu/desserts",
            element: "",
          }
        ]
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