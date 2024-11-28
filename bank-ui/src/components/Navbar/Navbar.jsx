import React from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css';

const Navbar = ({ user }) => {
    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-dark">
                <div className="container-fluid">
                    <Link className="navbar-brand" to="#">
                        E-BANK
                    </Link>
                    <button
                        className="navbar-toggler"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                    >
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                            <li className="nav-item">
                                <Link className="nav-link active" to="/">
                                    Home
                                </Link>
                            </li>
                            {user && user.role === 'admin' && (
                                <>
                                    <li className="nav-item">
                                        <Link className="nav-link" to="/addAccHolder">
                                            Ajouter un Client
                                        </Link>
                                    </li>
                                    <li className="nav-item">
                                        <Link className="nav-link" to="/addAccount">
                                            Nouveau Compte
                                        </Link>
                                    </li>
                                    <li className="nav-item">
                                        <Link className="nav-link" to="/delAccHolder">
                                            Delete
                                        </Link>
                                    </li>
                                    <li className="nav-item">
                                        <Link className="nav-link" to="/updAccHolder">
                                            Update
                                        </Link>
                                    </li>
                                    <li className="nav-item">
                                        <Link className="nav-link" to="/searchAccHolder">
                                            Search
                                        </Link>
                                    </li>
                                </>
                            )}
                            {/* Move this part within a list item */}
                            {user && user.role === 'user' && (
                                <>
                                    <li className="nav-item">
                                        <Link className="nav-link" to="/transMoney">
                                            Transfer Money
                                        </Link>
                                    </li>
                                    <li className="nav-item">
                                        <Link className="nav-link" to="/transHistory">
                                            Transaction History
                                        </Link>
                                    </li>

                                </>
                            )}
                        </ul>

                        {/* <li className="nav-item"> */}
                        {/* <Link className='nav-link customBtns' to="/login" id='login'>Login</Link> */}
                        <a className="nav-link customBtns" href="/lgout " id='logout'>
                            Logout
                        </a>
                        {/* </li> */}
                    </div>
                </div>
            </nav>
        </div>
    );
};

export default Navbar;

