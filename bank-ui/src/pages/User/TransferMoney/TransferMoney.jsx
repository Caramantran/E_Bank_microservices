import React, { useState, useEffect } from 'react';
import 'react-toastify/dist/ReactToastify.css';
import { toast } from 'react-toastify';
import './TransferMoney.css';
import { Transfer, LoadAccounts } from './service';

const TransferMoney = () => {
  const [formData, setFormData] = useState({
    accountSource: '',
    accountDestination: '',
    amount: '',
    motive: '',
  });

  const [accounts, setAccounts] = useState([]);

  useEffect(() => {
    const fetchAccounts = async () => {
      try {
        const cin = '3c38ff0b-b2f0-48b8-b094-0cd15c7aaa70	';
        const response = await LoadAccounts(cin);
        if (response) {
          setAccounts(response);
        }
      } catch (error) {
        toast.error('Une erreur sest produite lors du chargement des comptes!');
      }
    };

    fetchAccounts();
  }, []);

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
      const response = await Transfer(dataToSend);

      if (response.accountSource !== null) {
        toast.success(`Transféré avec succès ${formData.amount} MAD vers ${formData.accountDestination}`);
      } else {
        toast.error('Opération échoué');
      }
    } catch (error) {
      toast.error('Erreur lors de transfert');
    }
  };

  return (
    <div className="container mt-3">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card accContainer">
            <div className="card-body">
              <h2 className="card-title">Nouveau virement</h2>
              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <label htmlFor="accountSource" className="form-label">
                    Compte source
                  </label>
                  <select
                    className="form-control"
                    id="accountSource"
                    name="accountSource"
                    value={formData.accountSource}
                    onChange={handleChange}
                    required
                  >
                    <option value="" disabled>
                      Sélectionner le compte source
                    </option>
                    {accounts.map((account) => (
                      <option key={account.id} value={account.id}>
                        {account.id} - {account.balance} MAD
                      </option>
                    ))}
                  </select>
                </div>
                <div className="mb-3">
                  <label htmlFor="amount" className="form-label">
                    Montant
                  </label>
                  <input
                    type="number"
                    className="form-control"
                    id="amount"
                    name="amount"
                    placeholder="Entrer le montant"
                    value={formData.amount}
                    onChange={handleChange}
                    required
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="accountDestination" className="form-label">
                    Compte de destination
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    id="accountDestination"
                    name="accountDestination"
                    placeholder="Bénéficiaire"
                    value={formData.accountDestination}
                    onChange={handleChange}
                    required
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="motive" className="form-label">
                    Motif
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    id="motive"
                    name="motive"
                    placeholder="Motif"
                    value={formData.motive}
                    onChange={handleChange}
                    required
                  />
                </div>
                <button type="submit" className="btn btn-primary">
                  Envoyer
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default TransferMoney;
