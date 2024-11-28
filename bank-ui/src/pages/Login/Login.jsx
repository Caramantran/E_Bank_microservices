import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './Login.css';

function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        // Send a POST request to the Spring Boot backend with username and password
        const response = axios.post('/login');
        if (response.ok) {
            console.log("Successful login")
            navigate("/");
        } else {
            // Handle login failure
        }
    };

    return (
        <div className="container-fluid">
            <div className="row main-content bg-success text-center">
                <div className="col-md-4 text-center company__info">
                    <span className="company__logo"><h2><span className="fa fa-bank"></span></h2></span>
                    <h4 className="company_title">E-BANK</h4>
                </div>
                <div className="col-md-8 col-xs-12 col-sm-12 login_form ">
                    <div className="container-fluid">
                        <div className="row">
                            <h2>Log In</h2>
                        </div>
                        <div className="row">
                            <form control="" className="form-group" onSubmit={handleSubmit} >
                                <div className="row">
                                    <input
                                        type="text"
                                        placeholder="Username"
                                        value={username}
                                        onChange={(e) => setUsername(e.target.value)}
                                        id = "username"
                                        className="form__input"
                                    />
                                </div>
                                <div className="row">
                                    <input
                                        type="password"
                                        placeholder="Password"
                                        value={password}
                                        onChange={(e) => setPassword(e.target.value)}
                                        id="password"
                                        className="form__input"
                                    />
                                </div>
                                <div className="row">
                                    <input type="submit" value="Login" className="buttn btn" />
                                </div>
                            </form>
                        </div>
                        
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Login;
