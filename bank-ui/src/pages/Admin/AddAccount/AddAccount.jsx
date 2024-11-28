import React, { useState } from 'react';
import {toast} from 'react-toastify';
import './AddAccount.css';
import { AddAccount } from './service';


const AddAccountForm  = () => {
  const [formData, setFormData] = useState({
    rib: null,
    cin: null,
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
      };
  
      const response = await AddAccount(dataToSend);
  
      if (response.id !== null) {
        toast.success(`Compte créé avec succès`);
      } else {
        toast.error('Opération échoué');
      }
    } catch (error) {
      toast.error("Erreur lors de la création du compte");
    }
  };
  

  return (
    <div className='container mt-5 my-5 accHoldContainer'>
      <div className="row">
        <div className="col-md-12 text-center">
          <h1>Ajouter un compte</h1>
        </div>
      </div>
      <div className="row">
        <div className="col-md-7 mx-auto">
          <form onSubmit={handleSubmit}>
            <div className="mb-3">
              <label htmlFor="rib" className="form-label">Relevé d’Identité Bancaire (RIB)</label>
              <input
                type="number"
                className="form-control"
                id="username"
                name="rib"
                value={formData.rib}
                onChange={handleChange}
                required
              />
            </div>
            <div className="mb-3">
              <label htmlFor="cin" className="form-label">Carte d'identité nationale (CIN)</label>
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
            <button id='addBtn' type="submit" className="btn btn-primary">Ajouter</button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default AddAccountForm;
