import env from "react-dotenv";
import { request } from "../../../utils/RequestHelpper";


export const Transfer = (account) => 
{
    return transferMoneyServiceRequest("/transaction/transfer", "POST", account);
}
  const transferMoneyServiceRequest = (path, method = "GET", data) =>
   request({
    url: `${"http://localhost:9999"}${path}`,
    method,
    data,
});

export const LoadAccounts = (cin) => 
{
    return loadAccountsServiceRequest(`/account/list/${cin}`, "GET");
}
  const loadAccountsServiceRequest = (path, method = "GET") =>
   request({
    url: `${"http://localhost:9999"}${path}`,
    method,
});