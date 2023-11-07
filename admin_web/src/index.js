import React from "react";
import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import reportWebVitals from "./test/reportWebVitals";

//pages
import Enterance from "./unsecure/pages/Enterance";

const Router = createBrowserRouter([
  {
    path: "/",
    element: <Enterance />
  },


]);


const root = ReactDOM.createRoot(document.getElementById("root"));

root.render(
  <React.StrictMode>
    <RouterProvider router={Router} />
  </React.StrictMode>
);

reportWebVitals();