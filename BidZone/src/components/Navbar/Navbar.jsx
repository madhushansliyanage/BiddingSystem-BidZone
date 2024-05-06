import React from "react";

export default function Navbar() {
  return (
    <div>
      <nav class="navbar navbar-dark bg-primary fixed-top">
        <a class="navbar-brand" href="/overview">
          BidZone
        </a>
        <a class="navbar-brand" href="#">
          Item
        </a>
        <a class="navbar-brand" href="#">
          BidZone
        </a>
        <a class="navbar-brand" href="/employees">
          User
        </a>
        <a class="navbar-brand" href="#">
          Profile
        </a>  
      </nav>
    </div>
  );
}
