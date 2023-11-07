import React from "react";
import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import reportWebVitals from "./test/reportWebVitals";

//pages
import Enterance from "./unsecure/pages/Enterance";
import Navbar from "./secure/components/Navbar";
import Menu from "./secure/pages/Menu";

const Router = createBrowserRouter([
  {
    path: "/",
    element: <Enterance />
  },
  {
    path: "/app",
    element: <Navbar />,
    children: [
      {
        path: "/app/menu",
        element: <Menu />
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