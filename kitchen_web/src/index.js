import React from "react";
import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import reportWebVitals from "./reportWebVitals";

//pages
import Login from "./unsecure/pages/Login";
import Orders from "./secure/pages/Orders";

const Router = createBrowserRouter([
  {
    path: "/kitchen",
    element: <Login />
  },
  {
    path: "/kitchen/orders",
    element: <Orders />
  }
]);

const root = ReactDOM.createRoot(document.getElementById("root"));



root.render(
  <React.StrictMode>
    <RouterProvider router={Router} />
  </React.StrictMode>
);

reportWebVitals();
