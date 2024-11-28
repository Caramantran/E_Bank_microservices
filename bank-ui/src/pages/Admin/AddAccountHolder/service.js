import env from "react-dotenv";
import { request } from "../../../utils/RequestHelpper";


export const addAccountHolder = (customer) => 
{
    return addAccountServiceRequest("/customer/save", "POST", customer);
}
  const addAccountServiceRequest = (path, method = "POST", data) =>
   request({
    url: `${"http://localhost:9999"}${path}`,
    method,
    data,
});

//const addAccountServiceRequest = (path, method = "POST", data) =>
//request({
//  url: `${env.API_URL}${path}`,
//  method,
//  data,
//});
