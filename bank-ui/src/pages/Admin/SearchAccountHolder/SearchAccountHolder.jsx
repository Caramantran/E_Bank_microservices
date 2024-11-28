import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './SearchAccountHolder.css';

const SearchAccountHolder = () => {
  const [searchInput, setSearchInput] = useState('');
  const [accounts, setAccounts] = useState([]);
  const [originalAccounts, setOriginalAccounts] = useState([]);

  // Function to fetch accounts from the backend
  const fetchAccounts = async () => {
    try {
      const response = await axios.get('/api/v1/accounts');
      if (response.status === 200) {
        const data = response.data; 
        setAccounts(data);
        setOriginalAccounts(data); // Saving a copy for searching
      } else {
        console.error('Failed to fetch accounts');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };
  

  useEffect(() => {
    fetchAccounts();
  }, []);

  const handleSearchInputChange = (event) => {
    setSearchInput(event.target.value);
  }

  const handleSearch = () => {
    // Filter accounts based on the search input
    const filteredAccounts = originalAccounts.filter(account =>
      account.id.toString().includes(searchInput)
    );
    setAccounts(filteredAccounts);
  }

  return (
    <div className="container mt-5 my-5">
      <div className="row">
        <div className="col-md-12 text-center">
          <h1>Search Account Holder</h1>
          <div className="input-group mb-3">
            <input
              type="text"
              className="form-control"
              placeholder="Search by Account Holder ID"
              value={searchInput}
              onChange={handleSearchInputChange}
            />
            <div className="input-group-append">
              <button id='searchBtn' className="btn btn-primary" onClick={handleSearch}>Search</button>
            </div>
          </div>
        </div>
      </div>
      <div className="row">
        <div className="col-md-12">
          <table className="table shadow table-bg">
            <thead>
              <tr>
                <th>#</th>
                <th>Username</th>
                <th>Email</th>
                <th>Address</th>
                <th>Roles</th>
              </tr>
            </thead>
            <tbody className='table-group-divider'>
              {accounts.map(account => (
                <tr key={account.id}>
                  <td>{account.id}</td>
                  <td>{account.username}</td>
                  <td>{account.email}</td>
                  <td>{account.address}</td>
                  <td>{account.roles}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default SearchAccountHolder;
