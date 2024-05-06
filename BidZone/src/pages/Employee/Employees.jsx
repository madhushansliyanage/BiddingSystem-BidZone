import React from "react";
import Box from "@mui/material/Box";
import Navbar from "../../components/Navbar/Navbar";
import Sidebar from "../../components/Sidebar";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import EmployeeTable4 from "../../components/EmployeeComponent/EmployeeTable4";
import Footer from "../../components/Footer/Footer";

const gradientStyle = {
  background: "linear-gradient(to right, #2980B9, #6DD5FA)", // Gradient colors
};

export default function Employees() {
  return (
    <>
      <Navbar />
        <div
          className="content"
          style={{
            overflowY: "auto",
            maxHeight: "calc(100vh - 64px)",
            marginTop: 50,
          }}
        >
          <h1>Employees</h1>

          <EmployeeTable4 />
        </div>
      <Footer />
    </>
  );
}
