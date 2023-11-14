import React from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { registerChecker } from "../../logic/registerLogic";
import { request } from "../../fetch/fetch";
import { setAuthToken, setUserLogin } from "../../storage/localStorage"
export function SignIn() {
    const navigate = useNavigate();
    const [login, setLogin] = useState(null);
    const [password, setPassword] = useState(null);


    function onLogin(e) {
        e.preventDefault();

        if (registerChecker(password, login)) {
            request("POST", "/api/auth/login", {
                login: login,
                password: password,
            })
                .then((response) => {
                    setUserLogin(response.data.login)
                    setAuthToken(response.data.token);
                    navigate("/app/tables")
                }).catch((error) => {
                    navigate("/");
                });
        }
    }



  return <div className="form-container sign-in-container">
                <form id="form">
                    <h1>Sign in </h1>
                    <input type="email" placeholder="Email" onChange={(e) => setLogin(e.target.value)}/>
                    <input type="password" placeholder="Password" onChange={(e) => setPassword(e.target.value)}/>
                    <a id="a">Forgot your password?</a>
                    <button onClick={(e) => onLogin(e)}>Sign In</button>
                </form>
            </div>;
}
    