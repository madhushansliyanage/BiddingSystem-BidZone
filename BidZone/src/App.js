import './App.css';
import ReactDOM from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Overview from './pages/Overview';
import Accounting from './pages/Accounting/Accounting';

import Employees from './pages/Employee/Employees';
import EmployeeAdd from './pages/Employee/EmployeeAdd';
import EmployeeUpdate from './pages/Employee/EmployeeUpdate';
import EmployeeDelete from './pages/Employee/EmployeeDelete';


import Login from './pages/Login/Login';
import ForgotPassword from './pages/Login/ForgotPassword';


import ReportController from './pages/Report/ReportController';
import ReportStructure from './pages/Report/ReportStructure';
import ProfitandLossReport from './pages/Report/ProfitandLossReport';


function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />}></Route>
        <Route path="/overview" element={<Overview />}></Route>
        <Route path="/accounting" element={<Accounting />}></Route>
       
        <Route path="/employees" element={<Employees />}></Route>
       

        {/* routings inside the employee section */}
        <Route path="/employee/addemployee" element={<EmployeeAdd/>}> </Route>
        <Route path="/employee/updateemployee/:Id" element={<EmployeeUpdate/>}> </Route>
        <Route path="/employee/deleteemployee" element={<EmployeeDelete/>}> </Route>

      

        <Route path="/login" element={<Login />}></Route>
        <Route path="/forgotpassword" element={<ForgotPassword />}></Route>
        

        <Route path="/report" element={<ReportController />}></Route>
        <Route path="/report/reportStructure" element={<ReportStructure />}></Route>
        <Route path="/report/ProfitandLoss" element={<ProfitandLossReport />}></Route>

      </Routes>
    </BrowserRouter>
  
  );
}


export default App;
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App />);