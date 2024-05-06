import React from "react";
import "./Footer.css"; // Import CSS file for custom styles

const Footer = () => {
  return (
    <div className="footer-container">
      <footer className="text-center text-white fixed-bottom" style={{ backgroundColor: "#0a4275" }}>
        <div className="container p-4 pb-0">
          <section className="">
            <p className="d-flex justify-content-center align-items-center">
              <span className="me-3">Register for free</span>
              <button
                data-mdb-ripple-init
                type="button"
                className="btn btn-outline-light btn-rounded"
              >
                Sign up!
              </button>
            </p>
          </section>
        </div>

        <div className="text-center p-3" style={{ backgroundColor: "rgba(0, 0, 0, 0.2)" }}>
          BidZone © {new Date().getFullYear()} Copyright:
          <a className="text-white" href="/">
            BidZone.com
          </a>
        </div>
      </footer>
    </div>
  );
};

export default Footer;
