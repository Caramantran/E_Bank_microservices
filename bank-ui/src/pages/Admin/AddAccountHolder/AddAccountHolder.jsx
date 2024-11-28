import React, { useState } from 'react';
import {toast} from 'react-toastify';
import './AddAccountHolder.css';
import { addAccountHolder } from './service';


const AddAccountHolder = () => {
  const [formData, setFormData] = useState({
    firstname: null,
    name: null,
    placeOfBirth: null,
    dateOfBirth: null,
    nationality: null,
    cin: null,
    email: null,
    phone: null
  });


  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    try {
      const dataToSend = {
        ...formData,
        //roles: formData.roles.toUpperCase(),
      };
  
      const response = await addAccountHolder(dataToSend);
      console.log("🚀 ~ handleSubmit ~ response:", response)
  
      if (response.id !== null) {
        toast.success(`Customer  ${formData.name} ajouter avec succes`);
      } else {
        toast.error('Opération échoué');
      }
    } catch (error) {
      toast.error("Erreur lors de la création du Customer");
    }
  };
  

  return (
    <div className='container mt-5 my-5 accHoldContainer'>
      <div className="row">
        <div className="col-md-12 text-center">
          <h1>Ajouter nouveau client</h1>
        </div>
      </div>
      <div className="row">
        <div className="col-md-7 mx-auto">
          <form onSubmit={handleSubmit}>
            <div className="mb-3">
              <label htmlFor="name" className="form-label">Nom</label>
              <input
                type="text"
                className="form-control"
                id="username"
                name="name"
                value={formData.name}
                onChange={handleChange}
                required
              />
            </div>
            <div className="mb-3">
              <label htmlFor="firstname" className="form-label">Prénom </label>
              <input
                type="text"
                className="form-control"
                id="username"
                name="firstname"
                value={formData.firstname}
                onChange={handleChange}
                required
              />
            </div>
            <div className="mb-3">
              <label htmlFor="nationality" className="form-label">Nationalité </label>
              <input
                type="text"
                className="form-control"
                id="username"
                name="nationality"
                value={formData.nationality}
                onChange={handleChange}
                required
              />
            </div>
            <div className="mb-3">
              <label htmlFor="email" className="form-label">Email address</label>
              <input
                type="email"
                className="form-control"
                id="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                required
              />
            </div>
            <div className="mb-3">
              <label htmlFor="placeOfBirth" className="form-label">Lieu de naissance</label>
              <textarea
                className="form-control"
                id="address"
                name="placeOfBirth"
                rows="3"
                value={formData.placeOfBirth}
                onChange={handleChange}
                required
              ></textarea>
            </div>
            <div className="mb-3">
              <label htmlFor="dateOfBirth" className="form-label">Date de naissance</label>
              <input
                type="date"
                className="form-control"
                id="email"
                name="dateOfBirth"
                value={formData.dateOfBirth}
                onChange={handleChange}
                required
              />
            </div>
            <div className="mb-3">
              <label htmlFor="cin" className="form-label">CIN</label>
              <input
                type="text"
                className="form-control"
                id="username"
                name="cin"
                value={formData.cin}
                onChange={handleChange}
                required
              />
            </div>
            <div className="mb-3">
              <label htmlFor="phone" className="form-label">Téléphone</label>
              <input
                type="text"
                className="form-control"
                id="username"
                name="phone"
                value={formData.phone}
                onChange={handleChange}
                required
              />
            </div>
            <button id='addBtn' type="submit" className="btn btn-primary">Ajouter</button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default AddAccountHolder;
