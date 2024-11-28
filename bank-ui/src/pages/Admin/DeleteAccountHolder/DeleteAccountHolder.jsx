import React, { useState } from 'react';
import axios from 'axios';
import './DeleteAccountHolder.css';
import {toast} from 'react-toastify';

const DeleteAccountHolder = () => {
  const [username, setUsername] = useState('');

  const handleChange = (e) => {
    setUsername(e.target.value);
  };

  const handleDelete = async (e) => {
    // e.preventDefault();
    try {
      // Send a DELETE request to delete the account holder by username
      const response = await axios.delete(`/api/v1/accounts/${username}`);

      if (response.status === 200) {
        toast.success(`Account holder @_${username} deleted successfully`);
      } else {
        toast.error('Something went wrong');
      }
    } catch (error) {
      toast.error(`Account holder with username @_${username} does not exist!`);
    }
  };

  return (
    <div className='container mt-5 my-5 delAccHoldContainer'>
      <div className="row">
        <div className="col-md-12 text-center">
          <h1>Delete Account Holder</h1>
        </div>
      </div>
      <div className="row">
        <div className="col-md-7 mx-auto">

          <form onSubmit={handleDelete}>
            <div className="mb-3">
              <label htmlFor="username" className="form-label">Account Holder Username</label>
              <input type="text" className="form-control" id="username" value={username}
                onChange={handleChange} />
            </div>
            <button id='delBtn' type="submit" className="btn btn-danger">Delete</button>
          </form>

        </div>
      </div>
    </div>
  )
}

export default DeleteAccountHolder
