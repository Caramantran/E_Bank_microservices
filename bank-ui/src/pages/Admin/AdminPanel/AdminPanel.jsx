import React from 'react'
import { Routes, Route } from 'react-router-dom'
import AddAccountHolder from '../AddAccountHolder/AddAccountHolder';
import DeleteAccountHolder from '../DeleteAccountHolder/DeleteAccountHolder';
import UpdateAccountHolder from '../UpdateAccountHolder/UpdateAccountHolder';
import SearchAccountHolder from '../SearchAccountHolder/SearchAccountHolder';
import AdminHomePage from '../AdminHomePage/AdminHomePage';
import Logout from '../../../components/Logout/Logout';
import AddAccountForm from '../AddAccount/AddAccount';
// import Login from '../../Login/Login';
const AdminPanel = () => {
    return (
        <Routes>
            <Route path="/" element={<AdminHomePage />} />
            <Route path="/addAccHolder" element={<AddAccountHolder />} />
            <Route path="/AddAccount" element={<AddAccountForm />} />
            <Route path="/delAccHolder" element={<DeleteAccountHolder />} />
            <Route path="/updAccHolder" element={<UpdateAccountHolder />} />
            <Route exact path="/searchAccHolder" element={<SearchAccountHolder />} />
            {/* <Route path = "/login" element = {<Login />} /> */}
            <Route path = "/lgout" element = {<Logout />} />
        </Routes>
    )
}

export default AdminPanel