import React from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { passwordChecker, registerChecker, emailValidator } from "../../logic/registerLogic";
import { request } from "../../fetch/fetch";
import { setAuthToken, setUserLogin } from "../../storage/localStorage"

export function SignUp() {
    const navigate = useNavigate();
    const [password, setPassword] = useState(null);
    const [passwordRepeat, setRepeatPassword] = useState(null);
    const [login, setLogin] = useState(null);
    const [restaurantName, setRestaurantName] = useState(null);
    const [isWorgPassword, setIsWrongPassword] = useState(false);
    const [isInvalidEmail, setIsInvalidEmail] = useState(false);

    function onRegister(e) {
        e.preventDefault();
        if (registerChecker(password, passwordRepeat, login)) {
            if (emailValidator(login)) {
                if (passwordChecker(password, passwordRepeat)) {
                    setIsWrongPassword(false);
                    setIsInvalidEmail(false);

                    request("POST", "/api/auth/register", {
                        login: login,
                        restaurantName: restaurantName,
                        password: password,
                    })
                        .then((response) => {
                            setUserLogin(response.data.login)
                            setAuthToken(response.data.token);
                            navigate("/tableSetup")
                        }).catch((error) => {
                            navigate("/error");
                        });
                } else {
                    setIsWrongPassword(true);
                    setIsInvalidEmail(false);
                }
            } else {
                setIsInvalidEmail(true);
                setIsWrongPassword(false);
            }
        }

    }

    return <div class="form-container sign-up-container">
        <div id="form">
            <h1>Create Account</h1>
            <input type="text" placeholder="Restaurant Name" onChange={(e) => setRestaurantName(e.target.value)} />
            <input type="email" placeholder="Email" onChange={(e) => setLogin(e.target.value)} style={isInvalidEmail ? { color: '#B22222' } : {}} />
            <input type="password" placeholder="Password" onChange={(e) => setPassword(e.target.value)} style={isWorgPassword ? { color: '#B22222' } : {}} />
            <input type="password" placeholder="Repeat Password" onChange={(e) => setRepeatPassword(e.target.value)} style={isWorgPassword ? { color: '#B22222' } : {}} />
            <button onClick={(e) => onRegister(e)}>Sign Up</button>
        </div>
    </div>;
}
