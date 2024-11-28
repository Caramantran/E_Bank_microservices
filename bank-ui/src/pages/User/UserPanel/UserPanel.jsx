import React from 'react'
import { Routes, Route } from 'react-router-dom'
import TransferMoney from '../TransferMoney/TransferMoney';
import TransactionHistory from '../TransactionHistory/TransactionHistory';
import UserHomePage from '../UserHomePage/UserHomePage';
import Logout from '../../../components/Logout/Logout';
//import Login from '../../Login/Login';

const UserPanel = () => {
    return (
        <Routes>
            <Route path="/" element={<UserHomePage />} />
            <Route path="/transMoney" element={<TransferMoney />} />
            <Route path="/transHistory" element={<TransactionHistory />} />
            <Route path = "/lgout" element = {<Logout />} />
        </Routes>
    )
}

export default UserPanel
