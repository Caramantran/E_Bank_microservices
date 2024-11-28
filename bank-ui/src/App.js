import React, { useState, useEffect } from 'react';
import './lib/css/bootstrap.min.css';
import AdminPanel from './pages/Admin/AdminPanel/AdminPanel';
import UserPanel from './pages/User/UserPanel/UserPanel';
import Navbar from './components/Navbar/Navbar';
import Footer from './components/Footer/Footer';
import { BrowserRouter as Router } from 'react-router-dom';
import Login from './pages/Login/Login';
import { Routes, Route } from 'react-router-dom';
import './App.css';

function App() {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const fetchUserRoles = async () => {
      try {
        //const response = await axios.get('/api/v1/accounts/getRoles');

        if (true) {
          //const roles = response.data;
          // Determine the user's role based on the fetched roles
          //setUser({ role: roles.includes('ADMIN') ? 'admin' : 'user' });
          setUser({ role: 'user' });
        } else {
          console.error('Failed to fetch user roles');
        }
      } catch (error) {
        console.error('Error:', error);
      }
    };

    fetchUserRoles();
  }, []);

  return (
    <Router>
      <div id="root">
        <Navbar user={user} />
        <div className="main-content">
          {user && user.role === 'admin' && <AdminPanel />}
          <Routes>
            <Route path="/login" element={<Login />} />
          </Routes>
          {user && user.role === 'user' && <UserPanel />}
        </div>
        <Footer />
      </div>
    </Router>
  );
}

export default App;
