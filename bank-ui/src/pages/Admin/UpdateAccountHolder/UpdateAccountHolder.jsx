import React, { useState } from 'react';
import axios from 'axios';
import {toast} from 'react-toastify'
import './UpdateAccountHolder.css';

const UpdateAccountHolder = () => {
  const [username, setUsername] = useState('');
  const [accountHolder, setAccountHolder] = useState({
    id: '',
    username: '',
    password: '',
    email: '',
    roles: '',
    address: '',
  });

  const handleChange = (e) => {
    setUsername(e.target.value);
  };

  const handleSearch = async () => {
    try {
      // Send a request to search for the account holder by username
      const response = await axios.get(`/api/v1/accounts/search/${username}`);

      if (response.status === 200) {
        setAccountHolder(response.data.content);
        toast.success(`Account holder: @_${username} information retrieved successfully`)
      } else {
        // Handle account holder not found
        toast.error("Account holder not found!");
        setAccountHolder({
          id: '',
          username: '',
          password: '',
          email: '',
          roles: '',
          address: '',
        });
      }
    } catch (error) {
      toast.error("Account holder not found!");
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Exclude 'id', 'username', and 'password' from the data to be sent
      const { id, username, password, roles, ...dataToSend } = accountHolder;

      const response = await axios.put(`/api/v1/accounts/${username}`, dataToSend);

      if (response.status === 200) {
        toast.success(`Account holder: @_${username} updated successfully`)
      } else {
        toast.error('Something went wrong');
      }
    } catch (error) {
      toast.error("Something went wrong")
    }
  };

  return (
    <div className='container mt-5 my-5 updAccHoldContainer'>
      <div className="row">
        <div className="col-md-12 text-center">
          <h1>Update Account Holder</h1>
        </div>
      </div>
      <div className="row">
        <div className="col-md-7 mx-auto">
          <div className="mb-3">
            <label htmlFor="searchUsername" className="form-label">Search by Username</label>
            <input
              type="text"
              className="form-control"
              id="searchUsername"
              name="searchUsername"
              value={username}
              onChange={handleChange}
            />
            <button id='updateBtn' className="btn btn-primary" onClick={handleSearch}>Search</button>
          </div>
          <form onSubmit={handleSubmit}>
            <div className="mb-3">
              <label htmlFor="id" className="form-label">ID</label>
              <input
                type="text"
                className="form-control"
                id="id"
                name="id"
                value={accountHolder.id || ''} // Initialize with an empty string if accountHolder.id is null or undefined
                disabled
              />
            </div>
            <div className="mb-3">
              <label htmlFor="username" className="form-label">Username</label>
              <input
                type="text"
                className="form-control"
                id="username"
                name="username"
                value={accountHolder.username}
                disabled
              />
            </div>
            <div className="mb-3">
              <label htmlFor="email" className="form-label">Email address</label>
              <input
                type="email"
                className="form-control"
                id="email"
                name="email"
                value={accountHolder.email}
                onChange={(e) => setAccountHolder({ ...accountHolder, email: e.target.value })}
                required
              />
            </div>
            <div className="mb-3">
              <label htmlFor="roles" className="form-label">Roles</label>
              <input
                type="text"
                className="form-control"
                id="roles"
                name="roles"
                value={accountHolder.roles}
                disabled
              />
            </div>
            <div className="mb-3">
              <label htmlFor="address" className="form-label">Address</label>
              <textarea
                className="form-control"
                id="address"
                name="address"
                rows="3"
                value={accountHolder.address}
                onChange={(e) => setAccountHolder({ ...accountHolder, address: e.target.value })}
                required
              ></textarea>
            </div>
            <button type="submit" className="btn btn-primary btns">Update</button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default UpdateAccountHolder;
