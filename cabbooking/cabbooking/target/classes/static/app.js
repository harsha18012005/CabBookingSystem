
//driver
const driverForm = document.getElementById('driverForm');

driverForm.addEventListener('submit', async (e) => {
  e.preventDefault();
  const data = {
    fullName: driverForm.fullName.value,
    email: driverForm.email.value,
    password: driverForm.password.value,
    phone: driverForm.phone.value,
    vehicleNumber: driverForm.vehicleNumber.value,
    vehicleModel: driverForm.vehicleModel.value,
    aadhaarNumber: driverForm.aadhaarNumber.value,
    licenseNumber: driverForm.licenseNumber.value
  };

  try {
    const res = await fetch('http://localhost:8080/api/drivers', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data)
    });

    if(res.ok) {
      alert('Driver registered successfully!');
      driverForm.reset();
    } else {
      alert('Registration failed!');
    }
  } catch(err) {
    console.error(err);
    alert('Error connecting to backend');
  }
});
//ride
const rideForm = document.getElementById('rideForm');

rideForm.addEventListener('submit', async (e) => {
  e.preventDefault();

  const data = {
    userId: parseInt(rideForm.userId.value),
    pickupLocation: rideForm.pickupLocation.value,
    dropLocation: rideForm.dropLocation.value,
    distance: parseFloat(rideForm.distance.value),
    duration: parseFloat(rideForm.duration.value),
    status: rideForm.status.value // if you added a status input
  };

  try {
    const res = await fetch('http://localhost:8080/api/rides', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data)
    });

    if(res.ok) {
      const ride = await res.json();
      alert(`Ride requested successfully! Fare ID: ${ride.fare.id}`);
      rideForm.reset();

      // Optionally, add the ride to the rides table
      const ridesTableBody = document.querySelector('#ridesTable tbody');
      const renderRide = r => `
        <tr>
          <td>${r.id}</td>
          <td>${r.userId}</td>
          <td>${r.pickupLocation}</td>
          <td>${r.dropLocation}</td>
          <td>${r.distance}</td>
          <td>${r.duration}</td>
          <td>${r.status || "Pending"}</td>
          <td>${r.fare ? r.fare.id : ""}</td>
        </tr>`;
      ridesTableBody.insertAdjacentHTML('beforeend', renderRide(ride));

    } else {
      const errMsg = await res.text();
      alert('Ride request failed: ' + errMsg);
    }
  } catch(err) {
    console.error(err);
    alert('Error connecting to backend');
  }
});

//fare
const fareForm = document.getElementById('fareForm');
const resultDiv = document.getElementById('fareResult');

fareForm.addEventListener('submit', async (e) => {
  e.preventDefault();
  const rideId = parseInt(fareForm.rideId.value);

  try {
    const res = await fetch(`http://localhost:8080/api/fares/${rideId}`);
    if(res.ok) {
      const data = await res.json();
      resultDiv.innerHTML = `
        <p>Ride ID: ${data.rideId}</p>
        <p>Total Fare: ${data.totalFare}</p>
        <p>Base Fare: ${data.baseFare}</p>
        <p>Distance Fare: ${data.distanceFare}</p>
        <p>Time Fare: ${data.timeFare}</p>
      `;
    } else {
      resultDiv.innerHTML = `<p>Fare not found for Ride ID ${rideId}</p>`;
    }
  } catch(err) {
    console.error(err);
  }
});
// ========== ADMIN LOGIN ==========
/*const adminLoginForm = document.getElementById("admin-loginForm");

if (adminLoginForm) {
  adminLoginForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    const email = adminLoginForm.email.value;
    const password = adminLoginForm.password.value;

    try {
        const res = await fetch("http://localhost:8080/api/admins/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, password }),
        });

        if (res.ok) {
            const adminData = await res.json(); // now correctly parse JSON
            localStorage.setItem("adminLoggedIn", "true");
            localStorage.setItem("adminEmail", adminData.email); // optional
            window.location.href = "admin-dashboard.html"; // redirect to dashboard
        } else {
            alert("Invalid admin credentials!");
        }
    } catch(err) {
        console.error(err);
        alert("Error connecting to backend");
    }
  });
}*/

// ========== LOAD DASHBOARD ==========
/*document.addEventListener("DOMContentLoaded", () => {
 if (localStorage.getItem("adminLoggedIn") !== "true") {
        alert("Please log in first!");
        window.location.href = "admin-login.html";
        return;
    }
}
  loadUsers();
  loadDrivers();
  loadRides();
});

// ===== Load Users =====
async function loadUsers() {
    try {
        const res = await fetch("http://localhost:8080/api/users");
        if (!res.ok) throw new Error("Failed to fetch users");
        let users = await res.json();
        users.sort((a, b) => a.fullName.localeCompare(b.fullName)); // sort by name

        const tbody = document.querySelector("#usersTable tbody");
        tbody.innerHTML = users
            .map(u => `<tr><td>${u.id}</td><td>${u.fullName}</td><td>${u.email}</td></tr>`)
            .join("");
    } catch (err) {
        console.error(err);
    }
}

// ===== Load Drivers =====
async function loadDrivers() {
    try {
        const res = await fetch("http://localhost:8080/api/drivers");
        if (!res.ok) throw new Error("Failed to fetch drivers");
        let drivers = await res.json();
        drivers.sort((a, b) => a.fullName.localeCompare(b.fullName)); // sort by name

        const tbody = document.querySelector("#driversTable tbody");
        tbody.innerHTML = drivers
            .map(d => `<tr><td>${d.id}</td><td>${d.fullName}</td><td>${d.email}</td><td>${d.phone}</td></tr>`)
            .join("");
    } catch (err) {
        console.error(err);
    }
}

// ===== Load Rides =====
async function loadRides() {
    try {
        const res = await fetch("http://localhost:8080/api/rides");
        if (!res.ok) throw new Error("Failed to fetch rides");
        let rides = await res.json();
        rides.sort((a, b) => a.id - b.id); // sort by ID

        const tbody = document.querySelector("#ridesTable tbody");
        tbody.innerHTML = rides
            .map(r => `<tr><td>${r.id}</td><td>${r.userId}</td><td>${r.pickupLocation}</td><td>${r.dropLocation}</td><td>${r.status || 'Pending'}</td></tr>`)
            .join("");
    } catch (err) {
        console.error(err);
    }
}*/


//index
document.addEventListener('DOMContentLoaded', () => {
  console.log('Cab Booking System Dashboard Loaded');

});



