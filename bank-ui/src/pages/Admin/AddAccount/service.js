import env from "react-dotenv";
import { request } from "../../../utils/RequestHelpper";


export const AddAccount = (account) => 
{
    return addAccountServiceRequest("/account/save/current", "POST", account);
}
  const addAccountServiceRequest = (path, method = "POST", data) =>
   request({
    url: `${"http://localhost:9999"}${path}`,
    method,
    data,
});