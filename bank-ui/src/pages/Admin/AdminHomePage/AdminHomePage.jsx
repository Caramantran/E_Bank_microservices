
import React, { useEffect, useState } from 'react';
import axios from 'axios';

import { toast } from 'react-toastify';
import Card from 'react-bootstrap/Card';
import Carousel from 'react-bootstrap/Carousel';
import CarouselItem from 'react-bootstrap/CarouselItem';
import creditCardsImage from '../../images/credit_cards.png';
import confidenceImage from '../../images/fallout_mascot.png';

const AdminHomePage = () => {
  const [username, setUsername] = useState(null);

  const handleWelcome = () => {
    toast.success(`Welcome, @_${username}! to the Admin dashboard`, {
      position: toast.POSITION.TOP_CENTER,
      });
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const usernameResponse = await axios.get(`/api/v1/accounts/getUsername`);

        if (usernameResponse.status === 200) {
          const username = usernameResponse.data; 
          setUsername(username);
        } else {
          console.error('Failed to fetch username');
        }
      } catch (error) {
        console.error('Error:', error);
      }
    };

    // Call the fetchData function when the component mounts
    fetchData();
  }, []);
  return (
    <React.Fragment>
      <Card>
        <Carousel className="bg-dark pt-2">
          <CarouselItem>
            <img className="d-block mb-5" src={creditCardsImage} alt="goodCreditRates" height="300px" />
            <Carousel.Caption>
              <h3>Good credit rates</h3>
              <p>We have the fairest credit rates.</p>
            </Carousel.Caption>
          </CarouselItem>
          <CarouselItem>
            <img className="d-block mb-5" src={confidenceImage} alt="weSupportYou" height="300px" />
            <Carousel.Caption>
              <h3>We support you!</h3>
              <p>We are there for you wherever and whenever!</p>
            </Carousel.Caption>
          </CarouselItem>
        </Carousel>
      </Card>
    </React.Fragment>
    //<div className="container mt-5">
    //  <div className="row">
    //    <div className="col-md-6 offset-md-3 text-center">
    //      <h1>Welcome, @_{username}!</h1>
    //      <p>This is the Admin dashboard of the Bank Application.</p>
    //      <p>Here, you can manage manage user accounts and more.</p>
    //      <button className="btn btn-primary" onClick={handleWelcome} style={{border: "1px solid #008080"}}>Welcome!</button>
    //    </div>
    //  </div>
    //</div>
  );
};

export default AdminHomePage;
