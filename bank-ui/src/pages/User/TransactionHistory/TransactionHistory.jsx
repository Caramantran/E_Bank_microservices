import React, { useEffect, useState } from 'react';
import axios from 'axios';

const TransactionHistory = () => {
  const [transactionHistory, setTransactionHistory] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`/api/v1/transactions/history`);

        if (response.status === 200) {
          // Process and format the date
          const formattedData = response.data.map((entry) => ({
            ...entry,
            formattedDate: formatDate(entry.date), // Format the date here
          }));
          setTransactionHistory(formattedData);
        } else {
          console.error('Failed to fetch transaction history');
        }
      } catch (error) {
        console.error('Error:', error);
      }
    };

    fetchData();
  }, []);

  // Function to format the timestamp
  const formatDate = (dateString) => {
    try {
      const [year, month, day, hour, minute, second] = dateString.split(/\D/);
      const date = new Date(year, month - 1, day, hour, minute, second);
  
      if (isNaN(date.getTime())) {
        return 'Invalid Date';
      }

      const options = {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit',
        hour12: true, // Use 24-hour format
      };
  
      const formattedDate = date.toLocaleString(undefined, options);
  
      return formattedDate;
    } catch (error) {
      console.error('Error parsing date:', error);
      return 'Invalid Date';
    }
  };
  

  return (
    <div className="container mt-5 my-3 text-center">
      <div className="row">
        <div className="col-md-12">
          <h1>Transaction History</h1>
        </div>
      </div>
      <table className="table shadow">
        <thead>
          <tr>
            <th>#</th>
            <th>Amount ($)</th>
            <th>DB/CR</th>
            <th>Date</th>
            <th>Description</th>
          </tr>
        </thead>
        <tbody className='table-group-divider'>
          {transactionHistory.map((entry, index) => (
            <tr key={index}>
              <td>{index + 1}</td>
              <td>{entry.amount}</td>
              <td>{entry.db_cr_indicator}</td>
              <td>{entry.formattedDate}</td>
              <td>{entry.description}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default TransactionHistory;



