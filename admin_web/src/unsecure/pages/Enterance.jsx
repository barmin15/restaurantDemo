import { SignUp } from '../components/SignUp';
import { SignIn } from '../components/SignIn';
import { useState } from 'react';
import "../css/register.css"

export default function Enterance() {
    const [isRegister, setIsregister] = useState(false);


    return (
        <div id="body">
            <div id="container" className={`container${isRegister ? " right-panel-active" : ""}`}>

                <SignUp />
                <SignIn /> 

                <div className="overlay-container">
                    <div className="overlay">
                        <div className="overlay-panel overlay-left">
                            <h1>Hello, Friend!</h1>
                            <p>Enter your personal details and start journey with us</p>
                            <button className="ghost" id="signIn" onClick={(e) => setIsregister(false)}>Sign In</button>
                        </div>
                        <div className="overlay-panel overlay-right">
                            <h1>Welcome Back!</h1>
                            <p>To keep connected with us please login with your personal info</p>

                            <button className="ghost" id="signUp" onClick={(e) => setIsregister(true)}>Sign Up</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>)
}